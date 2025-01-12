package com.example.xpense.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NorthEast
import androidx.compose.material.icons.outlined.SouthWest
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xpense.domain.model.Transaction

@Composable
fun TransactionCard(transaction: Transaction, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .background(Color(0xFFFFFFFF))
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = if (transaction.transactionType == "Expense") {
                Icons.Outlined.NorthEast
            } else {
                Icons.Outlined.SouthWest
            },
            contentDescription = transaction.tags,
            modifier = Modifier.size(40.dp),
            tint = Color.Black
        )
        Spacer(modifier = Modifier.width(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = transaction.title,
                    color = Color.Black,
                    fontSize = 16.sp,
                )

                Text(
                    modifier = Modifier.width(64.dp),
                    text = transaction.tags,
                    color = Color.Gray.copy(0.7f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = if (transaction.transactionType == "Expense") {
                        "-$${transaction.amount}"
                    } else {
                        "+$${transaction.amount}"
                    },
                    color = if (transaction.transactionType == "Expense") {
                        Color.Red // Red color for Expense
                    } else {
                        Color(0xFF4CAF50) // Green color for Income
                    },
                    fontSize = 16.sp,
                )

                Text(
                    text = transaction.date,
                    color = Color.Gray.copy(0.7f),
                )
            }
        }
    }
}

@Preview
@Composable
private fun TransactionCardPreview() {
    TransactionCard(
        transaction = Transaction(
            0,
            "Hello",
            500,
            "School",
            "Expense",
            "15/08/2004",
            "lket kdkd"
        )
    ) {}
}
