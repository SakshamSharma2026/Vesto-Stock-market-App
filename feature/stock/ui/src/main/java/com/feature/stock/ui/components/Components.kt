package com.feature.stock.ui.components


import android.app.Activity
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
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
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
fun TopBarSection(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.user_icon), // Replace with actual profile picture
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
        items(storyList) { it ->
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
                            .background(Color(0xFFF5F5F5), RoundedCornerShape(16.dp)),
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
                            fontSize = 13.sp,
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
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Black, // Heavier weight for Gen-Z
                    letterSpacing = (-1).sp
                )

                Spacer(Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.ArrowOutward,
                        contentDescription = null,
                        tint = Color(0xFF1E8E42),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        "+\u20B9295.83 (12.5%) Today",
                        color = Color(0xFF1E8E42),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
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

@Preview
@Composable
fun AiInsightsCard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF1DBF9A), // primary
                        Color(0xFF8CF4AF),
                        Color(0xFFE2FBE9)
                    )
                )
            )
            .clickable { }
            .padding(24.dp)
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
                        .background(Color.White, RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.AutoAwesome,
                        contentDescription = null,
                        tint = Color(0xFF1DBF9A),
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "AI Market Pulse",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Black
                    )
                    Text(
                        text = "Real-time analysis of your portfolio",
                        color = Color.Black.copy(alpha = 0.6f),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            Icon(
                imageVector = Icons.Default.ArrowOutward,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
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
    modifier: Modifier = Modifier,
    index: Int,
    companyName: String,
    ticker: String,
    percentChange: String,
    isGainer: Boolean,
    priceCurrent: String,
    minPrice: String,
    maxPrice: String,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 8.dp),

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
                text = "\u20B9 $priceCurrent",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Min \u20B9 $minPrice • Max \u20B9 $maxPrice",
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VestoBottomBar(
    modifier: Modifier = Modifier,
    selectedRoute: String? = StockFeature.STOCK_SCREEN_ROUTE,
    onHomeClick: () -> Unit = {},
    onNewsClick: () -> Unit = {},
    onChartClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    Column(modifier = modifier) {
        // Subtle top border
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFF0F0F0))
        )

        NavigationBar(
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.White,
            tonalElevation = 0.dp
        ) {
            NavigationBarItem(
                selected = selectedRoute == StockFeature.STOCK_SCREEN_ROUTE || selectedRoute == StockFeature.STOCK_NESTED_ROUTE,
                onClick = onHomeClick,
                icon = {
                    Icon(
                        imageVector = if (selectedRoute == StockFeature.STOCK_SCREEN_ROUTE || selectedRoute == StockFeature.STOCK_NESTED_ROUTE) Icons.Default.Home else Icons.Outlined.Home,
                        contentDescription = "Home",
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text("Home", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color.Black,
                    indicatorColor = primaryColor
                )
            )

            NavigationBarItem(
                selected = false,
                onClick = onChartClick,
                icon = {
                    Icon(
                        Icons.Outlined.BarChart,
                        contentDescription = "Chart",
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text("Analytics", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color.Black,
                    indicatorColor = primaryColor
                )
            )

            NavigationBarItem(
                selected = selectedRoute == NewsFeature.NEWS_SCREEN_ROUTE || selectedRoute == NewsFeature.NEWS_NESTED_ROUTE,
                onClick = onNewsClick,
                icon = {
                    Icon(
                        imageVector = if (selectedRoute == NewsFeature.NEWS_SCREEN_ROUTE || selectedRoute == NewsFeature.NEWS_NESTED_ROUTE) Icons.AutoMirrored.Filled.Article else Icons.AutoMirrored.Outlined.Article,
                        contentDescription = "News",
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text("News", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color.Black,
                    indicatorColor = primaryColor
                )
            )

            NavigationBarItem(
                selected = selectedRoute == ProfileFeature.PROFILE_SCREEN_ROUTE || selectedRoute == ProfileFeature.PROFILE_NESTED_ROUTE,
                onClick = onProfileClick,
                icon = {
                    Icon(
                        Icons.Outlined.PersonOutline,
                        contentDescription = "Profile",
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text("Profile", fontSize = 10.sp, fontWeight = FontWeight.Bold) },
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color.Black,
                    indicatorColor = primaryColor
                )
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