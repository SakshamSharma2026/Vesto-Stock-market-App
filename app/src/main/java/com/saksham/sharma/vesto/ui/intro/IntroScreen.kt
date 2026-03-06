package com.saksham.sharma.vesto.ui.intro

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.saksham.sharma.vesto.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.feature.stock.ui.model.Company
import com.feature.stock.ui.components.SystemBarStyle
import com.feature.stock.ui.screen.ui.theme.BorderLightGray
import com.feature.stock.ui.screen.ui.theme.ButtonDark
import com.feature.stock.ui.components.companyList1
import com.feature.stock.ui.components.companyList2
import com.feature.stock.ui.components.companyList3


@Preview(showBackground = true)
@Composable
fun IntroScreenPreview() {
    IntroScreen { }
}


@Composable
fun IntroScreen(modifier: Modifier = Modifier, onPrimaryBtnClicked: () -> Unit) {
    SystemBarStyle(
        darkIcons = true,
        statusBarColor = Color.White
    )
    Surface(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp, bottom = 30.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Marquee Top Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
            ) {
                MarqueeRow(items = companyList1, durationMillis = 15000, isReverse = false)
                Spacer(modifier = Modifier.height(24.dp))
                MarqueeRow(items = companyList2, durationMillis = 18000, isReverse = true)
                Spacer(modifier = Modifier.height(24.dp))
                MarqueeRow(items = companyList3, durationMillis = 16000, isReverse = false)
                Spacer(modifier = Modifier.height(30.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 40.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.intro_top_app_investment),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black.copy(alpha = 0.6f),
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(id = R.string.intro_invest_your_money),
                        fontSize = 68.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 72.sp,
                        color = Color.Black,
                        letterSpacing = (-1).sp
                    )
                }
            }

            // Bottom Texts
            ElevatedButton(
                onClick = { onPrimaryBtnClicked() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ButtonDark
                ),
                shape = RoundedCornerShape(32.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.intro_get_started),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}

@Composable
fun MarqueeRow(
    items: List<Company>,
    modifier: Modifier = Modifier,
    durationMillis: Int = 10000,
    isReverse: Boolean = false
) {
    val infiniteTransition = rememberInfiniteTransition(label = "marquee")
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "progress"
    )

    Layout(
        content = {
            // Row 1
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                items.forEach { CompanyCard(it) }
            }
            // Row 2
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                items.forEach { CompanyCard(it) }
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .clipToBounds()
    ) { measurables, constraints ->
        val placeable = measurables.map {
            it.measure(constraints.copy(minWidth = 0, maxWidth = Constraints.Infinity))
        }

        // Add space between the two rows
        val spacing = 16.dp.roundToPx()
        val rowWidth = placeable[0].width + spacing
        val height = placeable.maxOf { it.height }

        layout(constraints.maxWidth, height) {
            // The calculated offset mapped to proper direction based on progress
            val finalOffset = if (isReverse) {
                -rowWidth + (progress * rowWidth)
            } else {
                -(progress * rowWidth)
            }

            // Using pure translation inside layerBlock effectively avoids recomposition 
            // and relayout phases entirely, handling it super efficiently at drawing phase!
            placeable[0].placeWithLayer(x = 0, y = 0) {
                translationX = finalOffset
            }
            placeable[1].placeWithLayer(x = rowWidth, y = 0) {
                translationX = finalOffset
            }
        }
    }
}

@Composable
fun CompanyCard(company: Company) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(Color.Transparent, CircleShape)
                .border(1.dp, BorderLightGray, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(company.iconPath),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = company.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = company.ticker,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
        }
    }
}