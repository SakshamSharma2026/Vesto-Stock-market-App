package com.saksham.sharma.vesto.features.presentation.screens.details

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.saksham.sharma.vesto.R
import com.saksham.sharma.vesto.features.data.model.RecentNew
import com.saksham.sharma.vesto.features.data.model.StockDetailsResponse
import com.saksham.sharma.vesto.features.presentation.components.FloatingBottomBar
import com.saksham.sharma.vesto.features.presentation.components.SystemBarStyle
import com.saksham.sharma.vesto.ui.theme.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockDetailsScreen(
    stockName: String = "",
    onBackClicked: () -> Unit = {},
    viewModel: StockDetailsViewModel = hiltViewModel()
) {
    SystemBarStyle(darkIcons = true, statusBarColor = bgColor)

    val stocksState by viewModel.stockDetailsUiState.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = stockName) {
        viewModel.getStockDetails(stockName)
    }

    val data = stocksState.data
    val cName = data?.companyName ?: stockName
    val ticker = data?.companyProfile?.exchangeCodeNse ?: ""
    val industry = data?.industry ?: "Stock"

    val priceStr = data?.currentPrice?.NSE ?: "0.00"
    val priceParts = priceStr.split(".")
    val wholePrice = priceParts.getOrNull(0) ?: "0"
    val decimalPrice = if (priceParts.size > 1) "." + priceParts[1] else ""

    val pctChange = data?.percentChange ?: "0.00"
    val isGainer = !pctChange.startsWith("-")
    val pctColor = if (isGainer) Color(0xFF1E8E42) else Color(0xFFEA4335)
    val pctText = "${if (isGainer && !pctChange.startsWith("+")) "+" else ""}$pctChange%"

    Scaffold(
        containerColor = bgColor
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .fillMaxSize()
        ) {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 120.dp) // space for floating bottom bar
            ) {
                Spacer(modifier = Modifier.height(28.dp)) // top padding for status bar

                // Top Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Back Button
                    IconButton(
                        onClick = onBackClicked,
                        modifier = Modifier
                            .background(Color.White, CircleShape)
                            .size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }

                    // Title
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = cName,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (ticker.isNotEmpty()) "($ticker)" else "",
                            fontSize = 14.sp,
                            color = greyColor
                        )
                    }

                    // Options
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .background(Color.White, CircleShape)
                            .size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Options",
                            tint = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Main Card Content
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .background(Color.White, RoundedCornerShape(12.dp))
                        .padding(24.dp)
                ) {
                    // Header (Icon + Name)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(primaryColor.copy(alpha = 0.1f), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = cName.firstOrNull()?.toString() ?: "",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = primaryColor
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = cName,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "($industry)",
                                fontSize = 12.sp,
                                color = greyColor
                            )
                        }

                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = "Info",
                            tint = greyColor,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Price
                    if (stocksState.isLoading) {
                        Box(modifier = Modifier.padding(vertical = 16.dp)) {
                            CircularProgressIndicator(
                                color = primaryColor,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    } else {
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = "\u20B9$wholePrice",
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )
                            Text(
                                text = decimalPrice,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black,
                                modifier = Modifier.padding(bottom = 2.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = pctText,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = pctColor,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Chart Area
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                    ) {
                        // Mock stock chart using Canvas
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            val path = Path()
                            val width = size.width
                            val height = size.height

                            // Some random looking points for the stock trend
                            val points = listOf(
                                Offset(0f, height * 0.8f),
                                Offset(width * 0.1f, height * 0.6f),
                                Offset(width * 0.2f, height * 0.7f),
                                Offset(width * 0.3f, height * 0.5f),
                                Offset(width * 0.4f, height * 0.8f),
                                Offset(width * 0.5f, height * 0.4f),
                                Offset(width * 0.6f, height * 0.6f),
                                Offset(width * 0.7f, height * 0.3f), // The spike
                                Offset(width * 0.8f, height * 0.4f),
                                Offset(width * 0.9f, height * 0.1f),
                                Offset(width, height * 0.5f)
                            )

                            path.moveTo(points.first().x, points.first().y)
                            for (i in 1 until points.size) {
                                val current = points[i]
                                val previous = points[i - 1]
                                val controlPoint1 = Offset((current.x + previous.x) / 2, previous.y)
                                val controlPoint2 = Offset((current.x + previous.x) / 2, current.y)
                                path.cubicTo(
                                    controlPoint1.x, controlPoint1.y,
                                    controlPoint2.x, controlPoint2.y,
                                    current.x, current.y
                                )
                            }

                            drawPath(
                                path = path,
                                color = pctColor,
                                style = Stroke(
                                    width = 2.dp.toPx(),
                                    cap = StrokeCap.Round,
                                    join = StrokeJoin.Round
                                )
                            )

                            // Draw the little current price point
                            val targetPoint = points[7]
                            drawCircle(
                                color = primaryColor,
                                radius = 4.dp.toPx(),
                                center = targetPoint
                            )
                        }

                        // Price pill
                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .offset(x = 30.dp, y = (-20).dp)
                                .background(primaryColor, RoundedCornerShape(8.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "₹$wholePrice$decimalPrice",
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Time Filters
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val tabs = listOf("Days", "Weeks", "Months", "Years", "All")
                        var selectedTab by remember { mutableStateOf("Months") }

                        tabs.forEach { tab ->
                            val isSelected = tab == selectedTab
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(if (isSelected) primaryColor else Color.Transparent)
                                    .clickable { selectedTab = tab }
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = tab,
                                    color = if (isSelected) Color.White else greyColor,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // About Company Section
                data?.companyProfile?.companyDescription?.let { description ->
                    if (description.isNotEmpty()) {
                        AboutCompanySection(description)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                // Order Tabs
                var selectedOrderType by remember { mutableStateOf("Stop / Limit order") }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .background(Color.White, RoundedCornerShape(24.dp))
                        .padding(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (selectedOrderType == "Market order") bgColor else Color.Transparent)
                            .clickable { selectedOrderType = "Market order" }
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Market order",
                            fontSize = 14.sp,
                            color = if (selectedOrderType == "Market order") Color.Black else greyColor,
                            fontWeight = if (selectedOrderType == "Market order") FontWeight.Medium else FontWeight.Normal
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (selectedOrderType == "Stop / Limit order") bgColor else Color.Transparent)
                            .clickable { selectedOrderType = "Stop / Limit order" }
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Stop / Limit order",
                            fontSize = 14.sp,
                            color = if (selectedOrderType == "Stop / Limit order") Color.Black else greyColor,
                            fontWeight = if (selectedOrderType == "Stop / Limit order") FontWeight.Medium else FontWeight.Normal
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Volume Margin
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .background(Color.White, RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Volume Margin",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Minus Button
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(bgColor, RoundedCornerShape(12.dp))
                                .clickable { /* decrease */ },
                            contentAlignment = Alignment.Center
                        ) {
                            Text("-", fontSize = 24.sp, color = Color.Black)
                        }

                        // Value
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "0.001",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                            Text(
                                text = if (isGainer) "+$0.48" else "-$0.48",
                                fontSize = 12.sp,
                                color = greyColor
                            )
                        }

                        // Plus Button
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(bgColor, RoundedCornerShape(12.dp))
                                .clickable { /* increase */ },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add",
                                tint = Color.Black,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Spread & Earnings
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .background(accentGreen, RoundedCornerShape(12.dp))
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Spread",
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                        Text(
                            text = "Your Earnings",
                            fontSize = 12.sp,
                            color = greyColor
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Dropdown mock
                        Row(
                            modifier = Modifier
                                .background(Color.White, RoundedCornerShape(16.dp))
                                .padding(horizontal = 12.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "$", fontSize = 14.sp)
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = "Drop",
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        Text(
                            text = "₹ / 272.5 PIPS",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Market Stats Section
                data?.let {
                    MarketStatsSection(it)
                    Spacer(modifier = Modifier.height(24.dp))
                }

                // Recent News Section
                data?.recentNews?.let { news ->
                    if (news.isNotEmpty()) {
                        NewsSection(news)
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }

                Spacer(modifier = Modifier.height(48.dp)) // Extra space at bottom

            }
            // Sell and Buy Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 24.dp, vertical = 30.dp),
                horizontalArrangement = Arrangement.Center
            ) {

                Box(
                    modifier = Modifier
                        .width(120.dp)
                        .height(50.dp)
                        .border(width = 1.dp, color = Color.Red, shape = RoundedCornerShape(28.dp))
                ) {

                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .background(Color.Red, shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.ArrowDownward,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "SELL",
                            fontSize = 16.sp,
                            color = Color.Red,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                Spacer(modifier = Modifier.width(20.dp))


                Box(
                    modifier = Modifier
                        .width(120.dp)
                        .height(50.dp)
                        .background(color = Color(0xff4CBB17), shape = RoundedCornerShape(28.dp)),
                    contentAlignment = Alignment.Center
                ) {

                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .background(Color.White, shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.ArrowUpward,
                                contentDescription = null,
                                tint = Color(0xff4CBB17)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "BUY",
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }


            }
        }
    }


}

@Composable
fun MarketStatsSection(data: StockDetailsResponse) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(20.dp)
    ) {
        Text(
            text = "Market Stats",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StatItem("52W High", "₹${data.yearHigh}", Modifier.weight(1f))
            StatItem("52W Low", "₹${data.yearLow}", Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StatItem("Industry", data.industry, Modifier.weight(1f))
            StatItem("Exchange", data.companyProfile.exchangeCodeNse, Modifier.weight(1f))
        }
    }


}

@Composable
fun StatItem(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = label, fontSize = 12.sp, color = greyColor)
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            maxLines = 1
        )
    }
}

@Composable
fun AboutCompanySection(description: String) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(20.dp)
    ) {
        Text(
            text = "About Company",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = description,
            fontSize = 14.sp,
            color = Color.DarkGray,
            maxLines = if (expanded) Int.MAX_VALUE else 3,
            lineHeight = 20.sp
        )
        if (description.length > 100) {
            Text(
                text = if (expanded) "Show Less" else "Show More",
                color = primaryColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable { expanded = !expanded }
            )
        }
    }
}

@Composable
fun NewsSection(newsList: List<RecentNew>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "Recent News",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        newsList.take(3).forEach { news ->
            NewsItem(news)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun NewsItem(news: RecentNew) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = news.headline,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = news.date,
                    fontSize = 12.sp,
                    color = greyColor
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StockDetailsScreenPreview() {
    StockDetailsScreen()
}
