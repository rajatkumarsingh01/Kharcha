package com.example.xpense.presentation.wallet

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import com.example.xpense.R

@Composable
fun Wallet() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(
                colors = listOf(Color(0xFF3ABBB9), Color.White)
            ))
            .padding(8.dp)
    ) {
        TopSectionWallet()
        BalanceSection()
        ActionButtons()
        UpcomingBillsSection()
    }
}

@Composable
fun TopSectionWallet() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Wallet",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center) // Center the text
        )
        IconButton(
            onClick = { /* Handle notifications */ },
            modifier = Modifier.align(Alignment.TopEnd) // Align the icon to the right
        ) {
            Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = Color.White)
        }
    }
}

@Composable
fun BalanceSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Total Balance",
            fontSize = 16.sp,
            color = Color.Gray,
        )
        Text(
            text = "$2,548.00",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun ActionButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ActionButton(icon = Icons.Default.Add, label = "Add")
        ActionButton(icon = Icons.Default.QrCode, label = "Pay")
        ActionButton(icon = Icons.Default.Send, label = "Send")
    }
}

@Composable
fun ActionButton(icon: ImageVector, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFFE0F2F1))
                .padding(12.dp),
            tint = Color(0xFF3ABBB9)
        )
        Text(text = label, fontSize = 14.sp, color = Color.Gray)
    }
}

@Composable
fun UpcomingBillsSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Upcoming Bills",
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        val bills = listOf(
            BillItem("Youtube", "Feb 28, 2022", R.drawable.yt),
            BillItem("Electricity", "Mar 28, 2022", R.drawable.electricity),
            BillItem("House Rent", "Mar 31, 2022", R.drawable.homerent),
            BillItem("Spotify", "Feb 28, 2022", R.drawable.spotify)
        )

        bills.forEach { bill ->
            BillRow(bill)
        }
    }
}

data class BillItem(val title: String, val date: String, val iconRes: Int)

@Composable
fun BillRow(bill: BillItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = bill.iconRes),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = bill.title, fontSize = 18.sp, fontWeight = FontWeight.Medium)
            Text(text = bill.date, fontSize = 14.sp, color = Color.Gray)
        }
        Button(
            onClick = { /* Handle pay click */ },
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE0F2F1)),
            modifier = Modifier
                .height(40.dp)
                .width(80.dp)
        ) {
            Text(text = "Pay", color = Color(0xFF3ABBB9))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWalletScreen() {
    Wallet()
}
