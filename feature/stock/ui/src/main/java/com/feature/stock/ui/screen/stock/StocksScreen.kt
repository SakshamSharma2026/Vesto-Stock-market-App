package com.feature.stock.ui.screen.stock

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.feature.stock.ui.R
import com.feature.stock.ui.components.ActionButtonsSection
import com.feature.stock.ui.components.AiInsightsCard
import com.feature.stock.ui.components.BalanceCard
import com.feature.stock.ui.components.FloatingBottomBar
import com.feature.stock.ui.components.MarketTabs
import com.feature.stock.ui.components.StockItemCard
import com.feature.stock.ui.components.StoriesSection
import com.feature.stock.ui.components.SystemBarStyle
import com.feature.stock.ui.components.TopBarSection
import com.feature.stock.ui.screen.ui.theme.bgColor
import com.feature.stock.ui.screen.ui.theme.greyColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StocksScreen(
    viewModel: StocksViewModel = hiltViewModel(),
    onStockClicked: (String) -> Unit = {}
) {
    SystemBarStyle(darkIcons = true, statusBarColor = bgColor)

    val stocksState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = bgColor
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 20.dp,
                    end = 20.dp,
                    top = 20.dp,
                    bottom = 140.dp
                )
            ) {

                // Offline Banner
                if (stocksState.isOffline) {
                    item(key = "offline_banner") {
                        OfflineBanner()
                    }
                }

                item(key = "top_bar") { TopBarSection() }
                item(key = "stories") { StoriesSection() }
                item(key = "balance") { BalanceCard() }
                item(key = "actions") { ActionButtonsSection() }
                item(key = "ai_insights") { AiInsightsCard() }

                stocksSection(
                    stocksState = stocksState.stocksSection,
                    onTabSelected = viewModel::onTabSelected,
                    onStockClicked = onStockClicked,
                )


            }

            FloatingBottomBar(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp)
            )
        }
    }
}

fun LazyListScope.stocksSection(
    stocksState: StocksSectionUiState,
    onTabSelected: (StockTab) -> Unit,
    onStockClicked: (String) -> Unit,
) {

    item(key = "stocks_container") {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .border(
                    width = 1.dp,
                    color = greyColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .clip(RoundedCornerShape(12.dp))
        ) {

            Text(
                text = stringResource(id = R.string.stocks),
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(16.dp)
            )

            HorizontalDivider(color = greyColor)

            Spacer(modifier = Modifier.height(8.dp))

            MarketTabs(
                selectedTab = stocksState.selectedTab,
                onTabSelected = onTabSelected
            )

            Spacer(modifier = Modifier.height(8.dp))
            when {
                stocksState.isLoading -> {
                    repeat(10) {
                        ShimmerStockCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )
                    }
                }

                stocksState.selectedTab == StockTab.GAINERS -> {
                    stocksState.gainers.forEachIndexed { index, stock ->
                        StockItemCard(
                            index = index + 1,
                            companyName = stock.companyName,
                            ticker = stock.ticker,
                            priceCurrent = stock.price,
                            percentChange = stock.percentChange.toString(),
                            isGainer = true,
                            minPrice = stock.low,
                            maxPrice = stock.high,
                            onClick = { onStockClicked(stock.companyName) },
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }

                stocksState.selectedTab == StockTab.LOSERS -> {
                    stocksState.losers.forEachIndexed { index, stock ->
                        StockItemCard(
                            index = index + 1,
                            companyName = stock.companyName,
                            ticker = stock.ticker,
                            priceCurrent = stock.price,
                            percentChange = stock.percentChange.toString(),
                            isGainer = false,
                            minPrice = stock.low,
                            maxPrice = stock.high,
                            onClick = { onStockClicked(stock.companyName) },
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }

                else -> {
                    repeat(10) {
                        ShimmerStockCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}


@Composable
fun OfflineBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Red.copy(alpha = 0.8f))
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            stringResource(id = R.string.no_internet_connection),
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )

    }
}

@Composable
fun ShimmerStockCard(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val alpha by transition.animateFloat(
        initialValue = 0.2f, targetValue = 0.9f, animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing), repeatMode = RepeatMode.Reverse
        ), label = "shimmer_alpha"
    )

    val shimmerColor = Color.LightGray.copy(alpha = alpha)

    Row(
        modifier = modifier
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

