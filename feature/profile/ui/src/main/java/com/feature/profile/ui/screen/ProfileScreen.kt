package com.feature.profile.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.MilitaryTech
import androidx.compose.material.icons.outlined.NotificationsActive
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Timeline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.common.accentGreen
import com.core.common.bgColor
import com.core.common.primaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = bgColor,
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = "My Profile", 
                        fontWeight = FontWeight.Bold, 
                        fontSize = 20.sp,
                        color = Color.Black
                    ) 
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = bgColor
                ),
                actions = {
                    IconButton(onClick = { /* Handle settings */ }) {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = "Settings",
                            tint = Color.Black
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp)
        ) {
            // Profile Header
            item {
                ProfileHeaderSection()
                Spacer(modifier = Modifier.height(32.dp))
            }

            // Portfolio/Account Stats
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    AccountStatCard(
                        modifier = Modifier.weight(1f),
                        title = "Member Status",
                        value = "Alpha Elite",
                        icon = Icons.Outlined.MilitaryTech,
                        iconBg = accentGreen.copy(alpha = 0.3f)
                    )
                    AccountStatCard(
                        modifier = Modifier.weight(1f),
                        title = "Joined Date",
                        value = "Nov 2024",
                        icon = Icons.Outlined.Timeline,
                        iconBg = primaryColor.copy(alpha = 0.15f)
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
            }

            // Settings Sections
            item {
                ProfileSettingsGroup(title = "Account Details") {
                    ProfileOptionItem(
                        icon = Icons.Outlined.Person,
                        title = "Personal Information",
                        subtitle = "Manage name, email, phone",
                        isFirst = true
                    )
                    HorizontalDivider(color = Color(0xFFF0F0F0), modifier = Modifier.padding(horizontal = 24.dp))
                    ProfileOptionItem(
                        icon = Icons.Outlined.AccountBalanceWallet,
                        title = "Linked Banks",
                        subtitle = "2 Banks connected",
                        isLast = true
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                ProfileSettingsGroup(title = "App Settings") {
                    ProfileOptionItem(
                        icon = Icons.Outlined.Security,
                        title = "Security & Pin",
                        subtitle = "TouchID, PIN protection",
                        isFirst = true
                    )
                    HorizontalDivider(color = Color(0xFFF0F0F0), modifier = Modifier.padding(horizontal = 24.dp))
                    ProfileOptionItem(
                        icon = Icons.Outlined.NotificationsActive,
                        title = "Notification Setup",
                        subtitle = "Market alerts, news",
                        isLast = true
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                ProfileSettingsGroup(title = "More Options") {
                    ProfileOptionItem(
                        icon = Icons.AutoMirrored.Outlined.HelpOutline,
                        title = "Help & Support",
                        subtitle = "24/7 Priority support",
                        isFirst = true
                    )
                    HorizontalDivider(color = Color(0xFFF0F0F0), modifier = Modifier.padding(horizontal = 24.dp))
                    ProfileOptionItem(
                        icon = Icons.Outlined.Share,
                        title = "Invite Friends",
                        subtitle = "Get \u20B9100 on each referral",
                        isLast = true
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
            }

            // Logout Section
            item {
                LogoutButton()
                Spacer(modifier = Modifier.height(40.dp))
                
                Text(
                    text = "Vesto v1.4.2",
                    color = Color.Gray.copy(alpha = 0.6f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun ProfileHeaderSection(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.size(110.dp),
            contentAlignment = Alignment.Center
        ) {
            // Gradient Ring
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(listOf(primaryColor, accentGreen)),
                        CircleShape
                    )
                    .padding(3.dp)
            ) {
                // White inner gap
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(bgColor, CircleShape)
                        .padding(4.dp)
                ) {
                    // Profile Image
//                    Image(
//                        painter = painterResource(id = R.drawable.user_icon),
//                        contentDescription = "User Photo",
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .clip(CircleShape),
//                        contentScale = ContentScale.Crop
//                    )
                }
            }
            
            // Edit Badge
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(Color.Black, CircleShape)
                    .align(Alignment.BottomEnd)
                    .border(2.dp, bgColor, CircleShape)
                    .clip(CircleShape)
                    .clickable { /* TODO: Image picker */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = "Edit Profile Picture",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Saksham Sharma",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color = Color.Black,
                letterSpacing = (-0.5).sp
            )
            Spacer(modifier = Modifier.width(6.dp))
            Icon(
                imageVector = Icons.Default.Verified,
                contentDescription = "Verified User",
                tint = primaryColor,
                modifier = Modifier.size(20.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(
            text = "@saksham_sharma",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray.copy(alpha = 0.8f)
        )
    }
}

@Composable
fun AccountStatCard(
    title: String,
    value: String,
    icon: ImageVector,
    iconBg: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(iconBg, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(22.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = title, fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
            Text(text = value, fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight.Black)
        }
    }
}

@Composable
fun ProfileSettingsGroup(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = title.uppercase(),
            fontSize = 12.sp,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(start = 16.dp, bottom = 12.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                content()
            }
        }
    }
}

@Composable
fun ProfileOptionItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    isFirst: Boolean = false,
    isLast: Boolean = false,
    onClick: () -> Unit = {}
) {
    // Determine bounds for ripple clipping based on position in the group
    val shape = when {
        isFirst && isLast -> RoundedCornerShape(16.dp)
        isFirst -> RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
        isLast -> RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp)
        else -> RoundedCornerShape(0.dp)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape) // Ensures the ripple doesn't bleed out of the card corners
            .clickable { onClick() }
            .padding(horizontal = 24.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .background(bgColor, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(20.dp)
            )
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                fontSize = 13.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
        }

        Icon(
            imageVector = Icons.Default.NavigateNext,
            contentDescription = null,
            tint = Color.LightGray,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun LogoutButton(modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEDEE)),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .clip(RoundedCornerShape(32.dp))
            .clickable { /* Handle Logout */ }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.Logout,
                contentDescription = null,
                tint = Color(0xFFD32F2F),
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Log Out Account",
                color = Color(0xFFD32F2F),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}