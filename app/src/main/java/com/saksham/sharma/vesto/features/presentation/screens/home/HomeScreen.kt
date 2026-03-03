package com.saksham.sharma.vesto.features.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.saksham.sharma.vesto.R
import com.saksham.sharma.vesto.features.presentation.components.ActionButtonsSection
import com.saksham.sharma.vesto.features.presentation.components.AiInsightsCard
import com.saksham.sharma.vesto.features.presentation.components.BalanceCard
import com.saksham.sharma.vesto.features.presentation.components.FloatingBottomBar
import com.saksham.sharma.vesto.features.presentation.components.MarketTabs
import com.saksham.sharma.vesto.features.presentation.components.StockItemCard
import com.saksham.sharma.vesto.features.presentation.components.StoriesSection
import com.saksham.sharma.vesto.features.presentation.components.SystemBarStyle
import com.saksham.sharma.vesto.features.presentation.components.TopBarSection
import com.saksham.sharma.vesto.ui.theme.bgColor
import com.saksham.sharma.vesto.ui.theme.greyColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    SystemBarStyle(darkIcons = true, statusBarColor = bgColor)
    var selectedTab by remember { mutableIntStateOf(0) }
    val viewModel = hiltViewModel<HomeViewModel>()
    val result by viewModel.indianStockList.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = bgColor, content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                ) {
                    AnimatedVisibility(visible = result.isOffline) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Red.copy(alpha = 0.8f))
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "No Internet Connection",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )

                        }
                    }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 20.dp),
                        contentPadding = PaddingValues(
                            top = 20.dp, bottom = 120.dp
                        ) // Leave space for bottom bar
                    ) {

                        item {
                            TopBarSection()
                            Spacer(modifier = Modifier.height(20.dp))
                        }

                        item {
                            StoriesSection()
                            Spacer(modifier = Modifier.height(20.dp))
                        }

                        item {
                            BalanceCard()
                            Spacer(modifier = Modifier.height(20.dp))
                        }

                        item {
                            ActionButtonsSection()
                            Spacer(modifier = Modifier.height(20.dp))
                        }

                        item {
                            AiInsightsCard()
                            Spacer(modifier = Modifier.height(20.dp))
                        }

                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(
                                        width = 1.dp,
                                        color = greyColor,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .padding(16.dp)
                            ) {
                                MarketTabs(
                                    selectedTabIndex = selectedTab,
                                    onTabSelected = { selectedTab = it })

                                Spacer(modifier = Modifier.height(8.dp))
                                val gainers =
                                    result.data?.trending_stocks?.top_gainers ?: emptyList()
                                val losers = result.data?.trending_stocks?.top_losers ?: emptyList()

                                if (result.isLoading) {
                                    repeat(10) {
                                        Box(
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            ShimmerStockCard()
                                        }
                                    }
                                } else if (selectedTab == 1) {
                                    gainers.forEach { stock ->
                                        Box(
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            StockItemCard(
                                                index = gainers.indexOf(stock) + 1,
                                                companyName = stock.company_name,
                                                ticker = stock.ric,
                                                priceCurrent = stock.price,
                                                percentChange = stock.percent_change,
                                                isGainer = true,
                                                minPrice = stock.low,
                                                maxPrice = stock.high,
                                            )
                                        }
                                    }
                                } else {
                                    losers.forEach { stock ->
                                        Box(
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            StockItemCard(
                                                index = losers.indexOf(stock) + 1,
                                                companyName = stock.company_name,
                                                ticker = stock.ric,
                                                priceCurrent = stock.price,
                                                percentChange = stock.percent_change,
                                                isGainer = false,
                                                minPrice = stock.low,
                                                maxPrice = stock.high,
                                            )
                                        }
                                    }
                                }
                            }

                        }
                    }
                }


                // Floating Bottom Bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 24.dp)
                ) {
                    FloatingBottomBar()
                }
            }
        })
}

@Composable
fun ShimmerStockCard() {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val alpha by transition.animateFloat(
        initialValue = 0.2f, targetValue = 0.9f, animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing), repeatMode = RepeatMode.Reverse
        ), label = "shimmer_alpha"
    )

    val shimmerColor = Color.LightGray.copy(alpha = alpha)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon Outline
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(shimmerColor, CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        // Detail
        Column(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth(0.5f)
                    .background(shimmerColor, RoundedCornerShape(4.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .height(12.dp)
                    .fillMaxWidth(0.3f)
                    .background(shimmerColor, RoundedCornerShape(4.dp))
            )
        }
        // Price
        Column(horizontalAlignment = Alignment.End) {
            Box(
                modifier = Modifier
                    .height(16.dp)
                    .width(60.dp)
                    .background(shimmerColor, RoundedCornerShape(4.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .height(12.dp)
                    .width(40.dp)
                    .background(shimmerColor, RoundedCornerShape(4.dp))
            )
        }
    }
}

@Composable
fun StockCard(
    companyName: String, ticker: String, price: String, percentChange: String, isGainer: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon
        Box(
            modifier = Modifier
                .size(48.dp)
                .border(1.dp, Color(0xFFF0F0F0), CircleShape)
                .padding(8.dp), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.microsoft),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Inside
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        // Detail
        Column(modifier = Modifier.weight(1f)) {
            Text(
                companyName,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(ticker, fontSize = 13.sp, color = Color.Gray)
        }
        // Price
        Column(horizontalAlignment = Alignment.End) {
            Text(
                "$$price", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "${if (isGainer) "+" else ""}$percentChange%",
                fontSize = 13.sp,
                color = if (isGainer) Color(0xFF5BC17A) else Color(0xFFE56A6A),
                fontWeight = FontWeight.Medium
            )
        }
    }
}


@Composable
fun NavBarIcon(icon: androidx.compose.ui.graphics.vector.ImageVector, isSelected: Boolean) {
    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = if (isSelected) Color.Black else Color.Gray.copy(alpha = 0.6f),
        modifier = Modifier.size(26.dp)
    )
}

@Composable
fun SummaryCard(
    modifier: Modifier, amount: String, label: String, arrowIcon: ImageVector, color: Color
) {
    Box(
        modifier = modifier
            .background(Color(0xFFFAFAFA), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(amount, fontSize = 24.sp, color = Color.Black)
                Icon(
                    imageVector = arrowIcon, contentDescription = null, tint = color
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                label,
                fontSize = 14.sp,
                color = Color.Gray.copy(alpha = 0.8f),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

