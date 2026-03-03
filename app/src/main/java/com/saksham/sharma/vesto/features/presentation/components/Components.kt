package com.saksham.sharma.vesto.features.presentation.components


import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.Article
import androidx.compose.material.icons.outlined.Atm
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.CompareArrows
import androidx.compose.material.icons.outlined.Forward
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.saksham.sharma.vesto.R
import com.saksham.sharma.vesto.features.domain.model.StoryItemInfo
import com.saksham.sharma.vesto.ui.theme.accentGreen
import com.saksham.sharma.vesto.ui.theme.bgColor
import com.saksham.sharma.vesto.ui.theme.greyColor
import com.saksham.sharma.vesto.ui.theme.primaryColor

@Composable
fun TopBarSection(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.google), // Replace with actual profile picture
                contentDescription = "Profile Photo",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(12.dp))
            Column {
                Text(
                    text = "Hello Saksham \uD83D\uDC4B",
                    fontSize = 13.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "Welcome Back!",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            IconButtonWithBg(icon = Icons.Outlined.QrCodeScanner)
            IconButtonWithBg(icon = Icons.Outlined.Search)
        }
    }
}

@Composable
fun IconButtonWithBg(icon: ImageVector) {
    Box(
        modifier = Modifier
            .size(44.dp)
            .background(Color.White, CircleShape)
            .border(1.dp, greyColor.copy(alpha = 0.5f), CircleShape)
            .clickable { },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview
@Composable
fun StoriesSection(modifier: Modifier = Modifier) {
    val storyList = listOf(
        StoryItemInfo(
            label = "Meta",
            iconRes = R.drawable.meta,
            ringColors = listOf(Color(0xFF1E88E5), Color(0xFF64B5F6)),
            bg = Color.White
        ),
        StoryItemInfo(
            label = "Apple",
            iconRes = R.drawable.apple,
            ringColors = listOf(Color.DarkGray, Color.Gray),
            bg = Color.White
        ),
        StoryItemInfo(
            label = "Google",
            iconRes = R.drawable.google,
            ringColors = listOf(Color.Red, Color.Blue),
            bg = Color.White
        ),
        StoryItemInfo(
            label = "Pinterest",
            iconRes = R.drawable.pinterest,
            ringColors = listOf(Color.Red, Color.Red),
            bg = Color.White
        ),
        StoryItemInfo(
            label = "Alibaba",
            iconRes = R.drawable.alibaba,
            ringColors = listOf(Color(0xFFFFA500), Color.Yellow),
            bg = Color.White
        ),
        StoryItemInfo(
            label = "Netflix",
            iconRes = R.drawable.netflix,
            ringColors = listOf(Color.Red, Color.Red),
            bg = Color.White
        ),
        StoryItemInfo(
            label = "Paypal",
            iconRes = R.drawable.paypal,
            ringColors = listOf(Color(0xFF64B5F6), Color(0xFF1E88E5)),
            bg = Color.White
        ),
        StoryItemInfo(
            label = "Spotify",
            iconRes = R.drawable.spotify,
            ringColors = listOf(Color.Green, Color.Green),
            bg = Color.White
        ),

        )

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(storyList) { it ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(bgColor, CircleShape)
                        .border(
                            2.dp,
                            Brush.linearGradient(it.ringColors),
                            CircleShape
                        )
                        .padding(5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(it.bg, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = it.iconRes),
                            contentDescription = it.label,
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = it.label,
                    color = Color.Black,
                    fontSize = 12.sp
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BalanceCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 24.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color(0xFFF5F5F5), RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.AccountBalanceWallet,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Available Balance",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(accentGreen, shape = CircleShape)
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Forward,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "$5,738.25",
                    color = Color.Black,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "ML: 300.00%",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
            }

            Spacer(Modifier.height(8.dp))
            Row {
                Text("Profit: ", color = Color.Gray, fontSize = 14.sp)
                Text(
                    "+$295.83",
                    color = Color(0xFF1E8E42),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview
@Composable
fun ActionButtonsSection(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ActionButton(
            modifier = Modifier.weight(1f),
            title = "Add Money",
            icon = Icons.Outlined.AddCircleOutline,
            onClick = { }
        )
        Spacer(modifier = Modifier.width(12.dp))
        ActionButton(
            modifier = Modifier.weight(1f),
            title = "Tread Move",
            icon = Icons.Outlined.CompareArrows,
            onClick = { }
        )
        Spacer(modifier = Modifier.width(12.dp))
        ActionButton(
            modifier = Modifier.weight(1f),
            title = "Withdraw",
            icon = Icons.Outlined.Atm,
            onClick = { }
        )
    }
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(88.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 1.dp,
            color = greyColor.copy(alpha = 0.5f)
        ),
        colors = CardDefaults.cardColors(containerColor = bgColor),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                title,
                fontSize = 12.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview
@Composable
fun AiInsightsCard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(primaryColor, Color(0xFF8CF4AF), accentGreen)
                )
            )
            .padding(vertical = 16.dp, horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color.White, RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = Color(0xFF1E8E42),
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "AI Insights",
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Analyse Your Spending Activity",
                        color = Color(0xFF333333),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.ArrowOutward,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}


@Composable
fun MarketTabs(
    modifier: Modifier = Modifier,
    tabs: List<String> = listOf("Favourites", "Gainers", "Losers"),
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(tabs.size) { index ->
            val isSelected = selectedTabIndex == index
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { onTabSelected(index) }
            ) {
                Text(
                    text = tabs[index],
                    fontSize = 16.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                    color = if (isSelected) Color.Black else Color.Gray,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .height(3.dp)
                            .width(20.dp)
                            .clip(RoundedCornerShape(1.5.dp))
                            .background(Color.Black)
                    )
                } else {
                    Spacer(modifier = Modifier.height(3.dp))
                }
            }
        }
    }
}

@Composable
fun StockItemCard(
    index: Int,
    companyName: String,
    ticker: String,
    percentChange: String,
    isGainer: Boolean,
    priceCurrent: String,
    minPrice: String,
    maxPrice: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFFF5F5F5)),
            contentAlignment = Alignment.Center
        ) {
            Text(index.toString())
        }
        Spacer(modifier = Modifier.width(16.dp))

        // Info
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = companyName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFFF0F0F0), RoundedCornerShape(6.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = ticker,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.DarkGray
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "${if (isGainer) "+" else ""}$percentChange%",
                    fontSize = 12.sp,
                    color = if (isGainer) Color(0xFF1E8E42) else Color(0xFFEA4335),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // Prices
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = priceCurrent,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Min $minPrice • Max $maxPrice",
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun FloatingBottomBar(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .height(72.dp),
        shape = RoundedCornerShape(36.dp),
        color = Color.Black, // Dark bar
        shadowElevation = 16.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Home indicator
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFF222222), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    Color(0xFF8CF4AF),
                                    Color(0xFFE2FBE9)
                                )
                            ), CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home",
                        tint = Color.Black,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

            Icon(
                imageVector = Icons.Outlined.BarChart,
                contentDescription = "Chart",
                tint = Color.Gray,
                modifier = Modifier.size(26.dp)
            )

            Icon(
                imageVector = Icons.Outlined.Article,
                contentDescription = "Docs",
                tint = Color.Gray,
                modifier = Modifier.size(26.dp)
            )

            Icon(
                imageVector = Icons.Outlined.PersonOutline,
                contentDescription = "Profile",
                tint = Color.Gray,
                modifier = Modifier.size(26.dp)
            )
        }
    }
}

@Composable
fun SystemBarStyle(
    darkIcons: Boolean,
    statusBarColor: Color = Color.Transparent
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        val window = (view.context as Activity).window
        val controller = WindowCompat.getInsetsController(window, view)

        DisposableEffect(darkIcons, statusBarColor) {
            window.statusBarColor = statusBarColor.toArgb()
            controller.isAppearanceLightStatusBars = darkIcons
            controller.isAppearanceLightNavigationBars = darkIcons
            onDispose { }
        }
    }
}