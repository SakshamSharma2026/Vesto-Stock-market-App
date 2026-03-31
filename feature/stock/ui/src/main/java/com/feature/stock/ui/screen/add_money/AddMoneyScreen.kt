package com.feature.stock.ui.screen.add_money

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.core.common.accentGreen
import com.core.common.bgColor
import com.core.common.primaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMoneyScreen(
    walletViewModel: WalletViewModel = hiltViewModel(),
    onBackClicked: () -> Unit
) {
    var amountInput by rememberSaveable { mutableStateOf("") }
    val balance by walletViewModel.balance.collectAsState()

    val isValidAmount = amountInput.isNotBlank()

    Scaffold(
        containerColor = bgColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
        ) {
            // High Impact Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(Color.White, CircleShape)
                        .clip(CircleShape)
                        .clickable { onBackClicked() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Text(
                    text = "Deposit Funds",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.Black
                )

                Box(modifier = Modifier.size(44.dp)) // Spacer for center alignment
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Premium Balance Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                Row(
                    modifier = Modifier.padding(24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .background(primaryColor.copy(alpha = 0.1f), RoundedCornerShape(16.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountBalanceWallet,
                            contentDescription = null,
                            tint = primaryColor,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "BUYING POWER",
                            color = Color.Gray,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "₹${"%.2f".format(balance)}",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.Black,
                            letterSpacing = (-1).sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Amount Input Section
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "SPECIFY AMOUNT",
                    fontSize = 11.sp,
                    letterSpacing = 1.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "₹",
                        fontSize = 54.sp,
                        fontWeight = FontWeight.Black,
                        color = if (isValidAmount) Color.Black else Color.LightGray
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    BasicTextField(
                        value = amountInput,
                        onValueChange = { newValue ->
                            if (newValue.length <= 7 && (newValue.isEmpty() || newValue.matches(
                                    Regex("^\\d*$")
                                ))
                            ) {
                                amountInput = newValue
                            }
                        },
                        textStyle = TextStyle(
                            fontSize = 54.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.Black,
                            textAlign = TextAlign.Start
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        cursorBrush = SolidColor(accentGreen),
                        modifier = Modifier
                            .width(IntrinsicSize.Min)
                            .widthIn(min = 60.dp)
                    ) { innerTextField ->
                        if (amountInput.isEmpty()) {
                            Text(
                                text = "0",
                                fontSize = 54.sp,
                                fontWeight = FontWeight.Black,
                                color = Color.LightGray
                            )
                        }
                        innerTextField()
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Quick Action Chips
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    listOf("500", "1000", "5000").forEach { valAmount ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(20.dp))
                                .background(if (amountInput == valAmount) Color.Black else Color.White)
                                .border(
                                    width = 1.dp,
                                    color = if (amountInput == valAmount) Color.Black else Color(0xFFF0F0F0),
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .clickable { amountInput = valAmount }
                                .padding(vertical = 14.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "+₹$valAmount",
                                color = if (amountInput == valAmount) Color.White else Color.Black,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Black
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Payment Method Preview
            Text(
                text = "PAYMENT SOURCE",
                fontSize = 11.sp,
                letterSpacing = 1.sp,
                fontWeight = FontWeight.Black,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFFF5F5F5))
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(Color(0xFFFFE0B2).copy(alpha = 0.3f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("H", fontWeight = FontWeight.Bold, color = Color(0xFFE65100))
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text("HDFC Bank •••• 8291", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text("Instant Deposit supported", color = Color.Gray, fontSize = 11.sp)
                    }
                    Icon(
                        imageVector = Icons.Default.ArrowOutward, // Using as a "change" icon
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = Color.LightGray
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                ElevatedButton(
                    onClick = {
                        amountInput.toDoubleOrNull()?.let { amount ->
                            walletViewModel.addMoney(amount)
                            onBackClicked()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    enabled = isValidAmount,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White,
                        disabledContainerColor = Color.LightGray.copy(alpha = 0.2f),
                        disabledContentColor = Color.Gray
                    ),
                    shape = RoundedCornerShape(32.dp),
                    elevation = ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp
                    )
                ) {
                    Text(
                        text = if (isValidAmount) "Add ₹$amountInput to Wallet" else "Deposit Funds",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 0.5.sp
                    )
                }
            }
        }
    }
}


