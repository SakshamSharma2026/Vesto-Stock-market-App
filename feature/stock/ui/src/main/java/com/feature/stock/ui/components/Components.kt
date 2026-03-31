package com.feature.stock.ui.components


import android.app.Activity
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.automirrored.outlined.Article
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.ArrowOutward
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
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
import coil.compose.AsyncImage
import com.core.common.AiGlowEnd
import com.core.common.AiGlowStart
import com.core.common.LossRed
import com.core.common.ProfitGreen
import com.core.common.User
import com.core.common.accentGreen
import com.core.common.bgColor
import com.core.common.greyColor
import com.core.common.navigation_constant.NewsFeature
import com.core.common.navigation_constant.ProfileFeature
import com.core.common.navigation_constant.StockFeature
import com.core.common.primaryColor
import com.feature.stock.ui.R
import com.feature.stock.ui.model.Company
import com.feature.stock.ui.model.StoryItemInfoModel
import com.feature.stock.ui.screen.stock.StockTab


val companyList1 = listOf(
    Company("Spotify", "SPOT", R.drawable.spotify),
    Company("Meta", "FB", R.drawable.meta),
    Company("Apple", "AAPL", R.drawable.apple),
    Company("Amazon", "AMZN", R.drawable.amazon)
)
val companyList2 = listOf(
    Company("Alibaba", "BABA", R.drawable.alibaba),
    Company("Google", "GOOG", R.drawable.google),
    Company("Netflix", "NFLX", R.drawable.netflix),
    Company("Tesla", "TSLA", R.drawable.tesla)
)
val companyList3 = listOf(
    Company("Microsoft", "MSFT", R.drawable.microsoft),
    Company("Paypal", "PYPL", R.drawable.paypal),
    Company("Pinterest", "PINS", R.drawable.pinterest),
    Company("Youtube", "YOUTUBE", R.drawable.youtube)
)

@Composable
fun TopBarSection(
    user: User?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = user?.profilePicUrl,
                contentDescription = "Profile Photo",
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Black.copy(alpha = 0.05f), CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(16.dp))
            Column {
                Text(
                    text = "Welcome back",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
                Text(
                    text = user?.name?.split(" ")?.getOrNull(0) ?: "User",
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Black,
                    letterSpacing = (-0.5).sp
                )
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            IconButtonWithBg(icon = Icons.Outlined.Search)
            IconButtonWithBg(icon = Icons.Outlined.Notifications)
        }
    }
}

@Composable
fun IconButtonWithBg(icon: ImageVector) {
    Box(
        modifier = Modifier
            .size(44.dp)
            .background(Color.White, CircleShape)
            .border(1.dp, greyColor.copy(alpha = 0.3f), CircleShape)
            .clip(CircleShape)
            .clickable { }, // Ensures ripple is bounded by CircleShape
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
        StoryItemInfoModel(
            label = "Meta",
            iconRes = R.drawable.meta,
            ringColors = listOf(Color(0xFF1E88E5), Color(0xFF64B5F6)),
            bg = Color.White
        ),
        StoryItemInfoModel(
            label = "Apple",
            iconRes = R.drawable.apple,
            ringColors = listOf(Color.DarkGray, Color.Gray),
            bg = Color.White
        ),
        StoryItemInfoModel(
            label = "Google",
            iconRes = R.drawable.google,
            ringColors = listOf(Color.Red, Color.Blue),
            bg = Color.White
        ),
        StoryItemInfoModel(
            label = "Pinterest",
            iconRes = R.drawable.pinterest,
            ringColors = listOf(Color.Red, Color.Red),
            bg = Color.White
        ),
        StoryItemInfoModel(
            label = "Alibaba",
            iconRes = R.drawable.alibaba,
            ringColors = listOf(Color(0xFFFFA500), Color.Yellow),
            bg = Color.White
        ),
        StoryItemInfoModel(
            label = "Netflix",
            iconRes = R.drawable.netflix,
            ringColors = listOf(Color.Red, Color.Red),
            bg = Color.White
        ),
        StoryItemInfoModel(
            label = "Paypal",
            iconRes = R.drawable.paypal,
            ringColors = listOf(Color(0xFF64B5F6), Color(0xFF1E88E5)),
            bg = Color.White
        ),
        StoryItemInfoModel(
            label = "Spotify",
            iconRes = R.drawable.spotify,
            ringColors = listOf(Color.Green, Color.Green),
            bg = Color.White
        ),

        )

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(horizontal = 4.dp)
    ) {
        items(storyList) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(72.dp) // Larger stories
                        .background(bgColor, CircleShape)
                        .border(
                            2.5.dp,
                            Brush.linearGradient(it.ringColors),
                            CircleShape
                        )
                        .padding(6.dp),
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
                                .size(36.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = it.label,
                    color = Color.Black.copy(alpha = 0.8f),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BalanceCard(modifier: Modifier = Modifier, balance: Double = 0.0) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp), // Softer corners
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp) // More airy padding
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.AccountBalanceWallet,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Available Balance",
                            color = Color.Gray,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Savings • ",
                                color = Color.Gray.copy(alpha = 0.7f),
                                fontSize = 11.sp
                            )
                            Text(
                                text = "Primary",
                                color = primaryColor,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(accentGreen, shape = CircleShape)
                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowOutward,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

            Spacer(Modifier.height(32.dp))

            Column {
                Text(
                    text = "\u20B9 $balance",
                    color = Color.Black,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = (-2).sp
                )
                Spacer(Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.ArrowOutward,
                        contentDescription = null,
                        tint = ProfitGreen,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        "Trending Up Today",
                        color = ProfitGreen,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ActionButtonsSection(
    modifier: Modifier = Modifier,
    onAddMoney: () -> Unit = {},
    onTransfer: () -> Unit = {},
    onWithdraw: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ActionButton(
            modifier = Modifier.weight(1f),
            title = "Add Money",
            icon = R.drawable.add_money,
            onClick = onAddMoney
        )
        Spacer(modifier = Modifier.width(12.dp))
        ActionButton(
            modifier = Modifier.weight(1f),
            title = "Transfer",
            icon = R.drawable.transfer,
            onClick = onTransfer
        )
        Spacer(modifier = Modifier.width(12.dp))
        ActionButton(
            modifier = Modifier.weight(1f),
            title = "Withdraw",
            icon = R.drawable.withdraw,
            onClick = onWithdraw
        )
    }
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    title: String,
    icon: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(100.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        border = BorderStroke(1.dp, Color(0xFFF0F0F0))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFFF9F9F9), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                title,
                fontSize = 11.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun AiInsightsCard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(24.dp))
            .border(
                2.dp,
                Brush.linearGradient(
                    colors = listOf(AiGlowStart, AiGlowEnd)
                ),
                RoundedCornerShape(24.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        val infiniteTransition = rememberInfiniteTransition(label = "pulse")
        val alpha by infiniteTransition.animateFloat(
            initialValue = 0.4f,
            targetValue = 0.8f,
            animationSpec = infiniteRepeatable(
                animation = tween(2000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "alpha"
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            primaryColor.copy(alpha = alpha * 0.2f),
                            Color.Transparent
                        )
                    )
                )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(AiGlowStart.copy(alpha = 0.1f), RoundedCornerShape(14.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.AutoAwesome,
                            contentDescription = null,
                            tint = AiGlowStart,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "AI Market Pulse",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = (-0.5).sp
                        )
                        Text(
                            text = "Smart analysis of your portfolio",
                            color = Color.Gray,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Icon(
                    imageVector = Icons.Default.ArrowOutward,
                    contentDescription = null,
                    tint = Color.Black.copy(alpha = 0.3f),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}


@Composable
fun MarketTabs(
    selectedTab: StockTab,
    onTabSelected: (StockTab) -> Unit,
    modifier: Modifier = Modifier
) {
    val tabs = StockTab.entries.toTypedArray()

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = tabs,
            key = { it.name }   // stable key
        ) { tab ->
            val isSelected = tab == selectedTab

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(if (isSelected) Color.Black else Color.Transparent)
                    .clickable { onTabSelected(tab) }
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = tab.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) Color.White else Color.Gray
                )
            }
        }
    }
}

@Composable
fun StockItemCard(
    ticker: String,
    companyName: String,
    priceCurrent: String,
    percentChange: String,
    isGainer: Boolean,
    maxPrice: String,
    onClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(ticker) }
            .padding(vertical = 16.dp, horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon Placeholder
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(bgColor, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = ticker.split(".")[0].take(1),
                fontWeight = FontWeight.Black,
                fontSize = 16.sp,
                color = Color.Black.copy(alpha = 0.4f)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))

        // Info
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = companyName,
                fontSize = 17.sp,
                fontWeight = FontWeight.Black,
                color = Color.Black,
                letterSpacing = (-0.5).sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = ticker.split(".")[0],
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(3.dp)
                        .background(Color.LightGray, CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "High: ₹$maxPrice",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
            }
        }

        // Prices & Trend
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "₹$priceCurrent",
                fontSize = 16.sp,
                fontWeight = FontWeight.Black,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (isGainer) ProfitGreen.copy(alpha = 0.12f) else LossRed.copy(alpha = 0.12f))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "${if (isGainer) "+" else ""}$percentChange%",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Black,
                    color = if (isGainer) ProfitGreen else LossRed
                )
            }
        }
    }
}

@Composable
fun VestoBottomBar(
    modifier: Modifier = Modifier,
    selectedRoute: String? = StockFeature.STOCK_SCREEN_ROUTE,
    onHomeClick: () -> Unit = {},
    onNewsClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 24.dp)
            .height(72.dp)
            .clip(RoundedCornerShape(36.dp))
            .background(Color.Black)
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(
                selected = selectedRoute == StockFeature.STOCK_SCREEN_ROUTE || selectedRoute == StockFeature.STOCK_NESTED_ROUTE,
                onClick = onHomeClick,
                icon = Icons.Outlined.Home,
                selectedIcon = Icons.Default.Home,
                label = "Home"
            )

//            BottomNavItem(
//                selected = false, // Not yet implemented
//                onClick = onChartClick,
//                icon = Icons.Outlined.BarChart,
//                selectedIcon = Icons.Outlined.BarChart,
//                label = "Analytics"
//            )

            BottomNavItem(
                selected = selectedRoute == NewsFeature.NEWS_SCREEN_ROUTE || selectedRoute == NewsFeature.NEWS_NESTED_ROUTE,
                onClick = onNewsClick,
                icon = Icons.AutoMirrored.Outlined.Article,
                selectedIcon = Icons.AutoMirrored.Filled.Article,
                label = "News"
            )

            BottomNavItem(
                selected = selectedRoute == ProfileFeature.PROFILE_SCREEN_ROUTE || selectedRoute == ProfileFeature.PROFILE_NESTED_ROUTE,
                onClick = onProfileClick,
                icon = Icons.Outlined.PersonOutline,
                selectedIcon = Icons.Outlined.Person,
                label = "Profile"
            )
        }
    }
}

@Composable
fun BottomNavItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    selectedIcon: ImageVector,
    label: String
) {
    val backgroundColor = if (selected) primaryColor else Color.Transparent
    val contentColor = if (selected) Color.Black else Color.White.copy(alpha = 0.5f)

    Box(
        modifier = Modifier
            .height(48.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = if (selected) selectedIcon else icon,
                contentDescription = label,
                tint = contentColor,
                modifier = Modifier.size(22.dp)
            )
            if (selected) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = label,
                    color = contentColor,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Black
                )
            }
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