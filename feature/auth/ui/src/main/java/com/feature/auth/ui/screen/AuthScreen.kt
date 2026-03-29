package com.feature.auth.ui.screen

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.common.bgColor
import com.core.common.greyColor
import com.feature.auth.ui.R


@Preview
@Composable
fun AuthScreen(
    onGoogleSignInClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = bgColor
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Subtle badge
                Box(
                    modifier = Modifier
                        .background(Color.White, RoundedCornerShape(16.dp))
                        .border(1.dp, Color(0xFFF0F0F0), RoundedCornerShape(16.dp))
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "VESTO SECURE",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.Black,
                        letterSpacing = 1.sp
                    )
                }
                
                Spacer(modifier = Modifier.height(32.dp))
                
                // Massive Typography
                Text(
                    text = "Unlock\nThe Market",
                    fontSize = 56.sp,
                    fontWeight = FontWeight.Black,
                    lineHeight = 56.sp,
                    color = Color.Black,
                    letterSpacing = (-1.5).sp,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Zero commissions. Pure performance.\nStart building your premium portfolio.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = greyColor,
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp
                )
            }

            // Bottom Section (Google Log In)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 60.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .clip(RoundedCornerShape(32.dp))
                        .background(Color.White)
                        .clickable { onGoogleSignInClick() }
                        .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(32.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.google),
                            contentDescription = "Google Logo",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Continue with Google",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Text(
                    text = "By continuing, you agree to our Terms of Service\nand acknowledge our Privacy Policy.",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = greyColor,
                    textAlign = TextAlign.Center,
                    lineHeight = 18.sp
                )
            }
        }
    }
}