package com.example.xpense.presentation.profile
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.xpense.R

@Composable
fun Profile() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(
                colors = listOf(Color(0xFF3ABBB9), Color.White)
            ))
            .padding(8.dp)
    ) {
        TopSection()
        ProfileDetails()
        ProfileOptions()
    }
}

@Composable
fun TopSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Profile",
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
fun ProfileDetails() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_wallet_24), // Replace with actual drawable resource
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Enjelin Morgeana", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text(text = "@enjelin_morgeana", fontSize = 16.sp, color = Color.Gray)
    }
}

@Composable
fun ProfileOptions() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        val options = listOf(
            "Invite Friends" to Icons.Default.PersonAdd,
            "Account info" to Icons.Default.AccountBox,
            "Personal profile" to Icons.Default.Person,
            "Message center" to Icons.Default.Mail,
            "Login and security" to Icons.Default.Lock,
            "Data and privacy" to Icons.Default.PrivacyTip
        )
        options.forEach { option ->
            ProfileOptionItem(option.first, option.second)
        }
    }
}

@Composable
fun ProfileOptionItem(optionText: String, optionIcon: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = optionIcon,
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .padding(end = 16.dp),
            tint = Color.Gray
        )
        Text(
            text = optionText,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    Profile()
}
