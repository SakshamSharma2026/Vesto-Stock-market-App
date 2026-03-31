package com.feature.stock.ui.screen.stock_details

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.common.DeepPurple
import com.core.common.InfoBlue
import com.core.common.LossRed
import com.core.common.ProfitGreen
import com.core.common.SlateBlueGrey
import com.core.common.WarningYellow
import com.core.common.bgColor
import com.core.common.greyColor
import com.core.common.primaryColor
import com.feature.stock.ui.R
import com.feature.stock.ui.components.SystemBarStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockDetailsScreen(
    stockName: String,
    onBackClicked: () -> Unit,
    viewModel: StockDetailsViewModel = hiltViewModel()
) {
    SystemBarStyle(darkIcons = true, statusBarColor = bgColor)

    val state by viewModel.stockDetailsUiState.collectAsStateWithLifecycle()

    LaunchedEffect(stockName) {
        viewModel.loadStock(stockName)
    }

    Scaffold(
        containerColor = bgColor,
        bottomBar = {
            if (!state.isLoading && state.stockDetails != null) {
                BuySellBar()
            }
        }
    ) { padding ->

        if (state.isLoading) {
            StockDetailsSkeleton()
            return@Scaffold
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(bottom = 120.dp, top = 16.dp)
        ) {
            item {
                TopBarSection(
                    name = state.stockDetails?.companyName ?: "",
                    ticker = state.stockDetails?.ticker ?: "",
                    onBackClicked = onBackClicked
                )
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                PriceCardSection(
                    state = state,
                    onRangeSelected = viewModel::onRangeSelected
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            state.stockDetails?.let { response ->
                item {
                    MarketDetails(response)
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    MarketStatsSection(response)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            state.stockDetails?.description?.let {
                item {
                    AboutCompanySection(it)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            if (state.stockDetails?.news?.isNotEmpty() == true) {
                item {
                    NewsSection(state.stockDetails?.news ?: emptyList())
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }

            state.stockDetails?.analystSentiment?.let {
                item {
                    AnalystSentimentSection(it)
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }

            if (state.stockDetails?.shareholding?.isNotEmpty() == true) {
                item {
                    ShareholdingSection(state.stockDetails?.shareholding ?: emptyList())
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
fun TopBarSection(
    name: String,
    ticker: String,
    onBackClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBackClicked,
            modifier = Modifier
                .background(Color.White, CircleShape)
                .border(1.dp, Color(0xFFF0F0F0), CircleShape)
                .clip(CircleShape)
                .size(44.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier.size(20.dp)
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Black,
                color = Color.Black,
                letterSpacing = (-0.5).sp
            )
            if (ticker.isNotEmpty()) {
                Text(
                    text = ticker.split(".")[0],
                    fontSize = 12.sp,
                    color = greyColor
                )
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            IconButton(
                onClick = { /* Favorite action */ },
                modifier = Modifier
                    .background(Color.White, CircleShape)
                    .border(1.dp, Color(0xFFF0F0F0), CircleShape)
                    .clip(CircleShape)
                    .size(44.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.StarBorder,
                    contentDescription = "Favorite",
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
            }
            IconButton(
                onClick = { /* Options action */ },
                modifier = Modifier
                    .background(Color.White, CircleShape)
                    .border(1.dp, Color(0xFFF0F0F0), CircleShape)
                    .clip(CircleShape)
                    .size(44.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Options",
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun PriceCardSection(
    state: StockDetailsUiState,
    onRangeSelected: (ChartRange) -> Unit
) {
    val pctColor = if (state.stockDetails?.isGainer ?: true) ProfitGreen else LossRed

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .background(Color.White, RoundedCornerShape(24.dp))
            .padding(24.dp)
    ) {
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = "₹${state.stockDetails?.priceWhole ?: "0"}",
                fontSize = 44.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = (-1).sp,
                color = Color.Black
            )
            Text(
                text = state.stockDetails?.priceDecimal ?: ".00",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = state.stockDetails?.percentText ?: "0.00%",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = pctColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        PriceChart(
            prices = state.stockDetails?.chartPrices ?: emptyList(),
            color = pctColor
        )

        Spacer(modifier = Modifier.height(24.dp))

        ChartRangeTabs(
            selected = state.selectedRange,
            onSelected = onRangeSelected
        )
    }
}

@Composable
fun PriceChart(
    prices: List<Double>,
    color: Color
) {
    val textMeasurer = rememberTextMeasurer()
    var touchedIndex by remember { mutableStateOf<Int?>(null) }
    var touchX by remember { mutableStateOf(0f) }

    val density = LocalDensity.current

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .pointerInput(prices) {
                detectDragGestures(
                    onDragStart = { offset ->
                        if (prices.isNotEmpty()) {
                            val index = (offset.x / size.width * (prices.size - 1)).toInt()
                                .coerceIn(0, prices.size - 1)
                            touchedIndex = index
                            touchX = offset.x
                        }
                    },
                    onDrag = { change, _ ->
                        if (prices.isNotEmpty()) {
                            val index = (change.position.x / size.width * (prices.size - 1)).toInt()
                                .coerceIn(0, prices.size - 1)
                            touchedIndex = index
                            touchX = change.position.x
                        }
                    },
                    onDragEnd = { touchedIndex = null },
                    onDragCancel = { touchedIndex = null }
                )
            }
    ) {
        val width = constraints.maxWidth.toFloat()
        val height = constraints.maxHeight.toFloat()

        val minPrice = prices.minOrNull() ?: 0.0
        val maxPrice = prices.maxOrNull() ?: 100.0
        val priceRange = (maxPrice - minPrice).coerceAtLeast(0.01)

        val points = if (prices.size > 1) {
            prices.mapIndexed { index, price ->
                val x = (index.toFloat() / (prices.size - 1)) * width
                val y = (1f - ((price - minPrice).toFloat() / priceRange.toFloat())) * height
                Offset(x, y)
            }
        } else emptyList()

        Canvas(modifier = Modifier.fillMaxSize()) {
            val path = Path()

            // Grid lines
            val gridLines = 3
            for (i in 0..gridLines) {
                val gridY = (height / gridLines) * i
                drawLine(
                    color = Color.LightGray.copy(alpha = 0.15f),
                    start = Offset(0f, gridY),
                    end = Offset(width, gridY),
                    strokeWidth = 1.dp.toPx()
                )

                val priceAtGrid = maxPrice - (i.toDouble() / gridLines) * priceRange
                drawText(
                    textMeasurer = textMeasurer,
                    text = "₹${"%.1f".format(priceAtGrid)}",
                    style = TextStyle(fontSize = 10.sp, color = greyColor),
                    topLeft = Offset(4.dp.toPx(), gridY - 14.dp.toPx())
                )
            }

            if (points.isNotEmpty()) {
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

                val fillPath = Path().apply {
                    addPath(path)
                    lineTo(width, height)
                    lineTo(0f, height)
                    close()
                }

                drawPath(
                    path = fillPath,
                    brush = Brush.verticalGradient(
                        colors = listOf(color.copy(alpha = 0.25f), Color.Transparent)
                    )
                )

                drawPath(
                    path = path,
                    color = color,
                    style = Stroke(
                        width = 3.dp.toPx(),
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )

                // Interaction Line and Indicator
                touchedIndex?.let { index ->
                    val point = points[index]
                    drawLine(
                        color = greyColor,
                        start = Offset(point.x, 0f),
                        end = Offset(point.x, height),
                        strokeWidth = 1.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    )
                    drawCircle(
                        color = color,
                        radius = 6.dp.toPx(),
                        center = point
                    )
                    drawCircle(
                        color = Color.White,
                        radius = 3.dp.toPx(),
                        center = point
                    )
                } ?: run {
                    // Last point indicator
                    val lastPoint = points.last()
                    drawCircle(
                        color = color,
                        radius = 4.dp.toPx(),
                        center = lastPoint
                    )
                }
            }
        }

        // Floating Price Label on Touch
        touchedIndex?.let { index ->
            if (index in points.indices && index in prices.indices) {
                val price = prices[index]
                val xPx = with(density) { 40.dp.toPx() }
                val xPos = touchX.coerceIn(xPx, width - xPx)
                val yPos = points[index].y - with(density) { 45.dp.toPx() }

                Box(
                    modifier = Modifier
                        .offset(
                            x = with(density) { xPos.toDp() } - 40.dp,
                            y = with(density) { yPos.toDp() }
                        )
                        .background(Color.Black, RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "₹${"%.2f".format(price)}",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun ChartRangeTabs(
    selected: ChartRange,
    onSelected: (ChartRange) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ChartRange.entries.forEach { range ->
            val isSelected = range == selected
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(if (isSelected) Color.Black else Color.Transparent)
                    .clickable { onSelected(range) }
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            ) {
                Text(
                    text = range.name.take(1) + range.name.substring(1).lowercase(),
                    color = if (isSelected) Color.White else Color.Gray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun MarketDetails(data: com.feature.stock.domain.model.StockDetailsData) {
    val stats = data.stats
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.performance_and_ratios),
            fontSize = 12.sp,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.Black,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Market Stats",
            fontSize = 22.sp,
            fontWeight = FontWeight.Black,
            color = Color.Black,
            letterSpacing = (-1).sp
        )
        Spacer(modifier = Modifier.height(20.dp))

        val items = listOf(
            Triple(stringResource(id = R.string.one_d_high), "₹${stats.high}", ProfitGreen),
            Triple(
                stringResource(id = R.string.p_e_ratio), try {
                    stats.peRatio.toDouble()
                        .let { "%.2f".format(it) }
                } catch (e: Exception) {
                    stats.peRatio
                }, InfoBlue),
            Triple(stringResource(id = R.string.one_d_low), "₹${stats.low}", LossRed),
            Triple(
                stringResource(id = R.string.div_yield), try {
                    stats.divYield.toDouble()
                        .let { "%.2f%%".format(it) }
                } catch (e: Exception) {
                    stats.divYield
                }, WarningYellow),
            Triple(stringResource(id = R.string.market_cap), "₹${stats.marketCap}", DeepPurple),
            Triple(stringResource(id = R.string.prev_close), "₹${stats.close}", SlateBlueGrey)
        )

        Column {
            for (i in items.indices step 2) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    ImprovedStatBox(
                        items[i].first,
                        items[i].second,
                        items[i].third,
                        Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    if (i + 1 < items.size) {
                        ImprovedStatBox(
                            items[i + 1].first,
                            items[i + 1].second,
                            items[i + 1].third,
                            Modifier.weight(1f)
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
                if (i + 2 < items.size) {
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
fun ImprovedStatBox(
    label: String,
    value: String,
    accentColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(accentColor.copy(alpha = 0.08f), RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        Column {
            Text(text = label, fontSize = 11.sp, color = Color.Gray, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = value, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        }
    }
}

@Composable
fun MarketStatsSection(data: com.feature.stock.domain.model.StockDetailsData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .background(Color.White, RoundedCornerShape(24.dp))
            .padding(20.dp)
    ) {
        Column(modifier = Modifier.padding(top = 8.dp, bottom = 12.dp)) {
            Text(
                text = stringResource(id = R.string.historical_stats),
                fontSize = 11.sp,
                letterSpacing = 1.sp,
                fontWeight = FontWeight.Black,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "History",
                fontSize = 22.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = (-1).sp,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            StatItem(stringResource(id = R.string.fifty_two_w_high), "₹${data.yearHigh}", Modifier.weight(1f))
            StatItem(stringResource(id = R.string.fifty_two_w_low), "₹${data.yearLow}", Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            StatItem(
                stringResource(id = R.string.industry),
                data.industry.takeIf { it.isNotEmpty() } ?: "-",
                Modifier.weight(1f)
            )
            StatItem(
                stringResource(id = R.string.exchange),
                data.exchange.takeIf { it.isNotEmpty() } ?: "-",
                Modifier.weight(1f)
            )
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
            .background(Color.White, RoundedCornerShape(24.dp)) // Refined corners
            .padding(24.dp)
    ) {
        Column(modifier = Modifier.padding(top = 8.dp, bottom = 12.dp)) {
            Text(
                text = stringResource(id = R.string.about_company),
                fontSize = 11.sp,
                letterSpacing = 1.sp,
                fontWeight = FontWeight.Black,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Intelligence", // Abstracted title for premium feel
                fontSize = 22.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = (-1).sp,
                color = Color.Black
            )
        }
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
                text = if (expanded) stringResource(id = R.string.show_less) else stringResource(id = R.string.show_more),
                color = primaryColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable { expanded = !expanded })
        }
    }
}

@Composable
fun NewsSection(newsList: List<com.feature.stock.domain.model.StockNews>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Column(modifier = Modifier.padding(top = 8.dp, bottom = 12.dp)) {
            Text(
                text = stringResource(id = R.string.recent_news),
                fontSize = 11.sp,
                letterSpacing = 1.sp,
                fontWeight = FontWeight.Black,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Market Press",
                fontSize = 22.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = (-1).sp,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        newsList.take(3).forEach { news ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
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
                        Text(text = news.date, fontSize = 12.sp, color = greyColor)
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun AnalystSentimentSection(recos: com.feature.stock.domain.model.AnalystSentiment) {
    val analysts = recos.analysts
    val totalAnalysts = recos.totalAnalysts

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .background(Color.White, RoundedCornerShape(24.dp))
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.padding(top = 0.dp, bottom = 12.dp)) {
                Text(
                    text = stringResource(id = R.string.analyst_sentiment),
                    fontSize = 11.sp,
                    letterSpacing = 1.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Consensus",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = (-1).sp,
                    color = Color.Black
                )
            }

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(ProfitGreen.copy(alpha = 0.1f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${recos.tickerPercentage.toInt()}%",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = ProfitGreen
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(5.dp))
        ) {
            analysts.forEach { analyst ->
                val weight =
                    if (totalAnalysts > 0) analyst.numberOfAnalysts.toFloat() / totalAnalysts else 0f
                if (weight > 0) {
                    val color = try {
                        Color(android.graphics.Color.parseColor(analyst.colorCode))
                    } catch (e: Exception) {
                        primaryColor
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(weight)
                            .background(color)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            analysts.forEach { analyst ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val color = try {
                        Color(analyst.colorCode.toColorInt())
                    } catch (e: Exception) {
                        primaryColor
                    }
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(color, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = analyst.ratingName, fontSize = 11.sp, color = greyColor)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${analyst.numberOfAnalysts}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun ShareholdingSection(holdings: List<com.feature.stock.domain.model.ShareholdingPattern>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .background(Color.White, RoundedCornerShape(24.dp))
            .padding(20.dp)
    ) {
        Column(modifier = Modifier.padding(top = 8.dp, bottom = 12.dp)) {
            Text(
                text = "SHAREHOLDING PATTERN",
                fontSize = 11.sp,
                letterSpacing = 1.sp,
                fontWeight = FontWeight.Black,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Structure",
                fontSize = 22.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = (-1).sp,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        holdings.forEachIndexed { index, holding ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = holding.name, fontSize = 14.sp, color = Color.DarkGray)
                val latestPercentage = holding.latestPercentage
                Text(
                    text = "$latestPercentage%",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }
            if (index < holdings.size - 1) {
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = bgColor)
            }
        }
    }
}

@Composable
fun BuySellBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp, start = 24.dp, end = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black, RoundedCornerShape(32.dp))
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            // Sell Button
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(Color.White.copy(alpha = 0.15f))
                    .clickable { /* Sell logic */ },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "SELL",
                    color = Color.White,
                    fontWeight = FontWeight.Black,
                    fontSize = 14.sp,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Buy Button
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(ProfitGreen)
                    .clickable { /* Buy logic */ },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "BUY",
                    color = Color.White,
                    fontWeight = FontWeight.Black,
                    fontSize = 14.sp,
                    letterSpacing = 1.sp
                )
            }
        }
    }
}

@Composable
fun StockDetailsSkeleton() {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val alpha by transition.animateFloat(
        initialValue = 0.2f, targetValue = 0.9f, animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing), repeatMode = RepeatMode.Reverse
        ), label = "shimmer_alpha"
    )

    val shimmerColor = Color.LightGray.copy(alpha = alpha)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 80.dp)
    ) {
        Spacer(modifier = Modifier.height(28.dp))

        // Top Bar Skeleton
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(shimmerColor, CircleShape)
            )
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .height(24.dp)
                    .background(shimmerColor, RoundedCornerShape(4.dp))
            )
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(shimmerColor, CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Main Card Skeleton
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .background(Color.White, RoundedCornerShape(24.dp))
                .padding(24.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(shimmerColor, CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(20.dp)
                        .background(shimmerColor, RoundedCornerShape(4.dp))
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(48.dp)
                    .background(shimmerColor, RoundedCornerShape(4.dp))
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Chart Skeleton
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(shimmerColor.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Filters Skeleton
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(5) {
                    Box(
                        modifier = Modifier
                            .width(50.dp)
                            .height(24.dp)
                            .background(shimmerColor, RoundedCornerShape(16.dp))
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // About Skeleton
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(100.dp)
                .background(Color.White, RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tabs Skeleton
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(56.dp)
                .background(Color.White, RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Stats Skeleton
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(120.dp)
                .background(Color.White, RoundedCornerShape(16.dp))
        )

    }
}



