package com.example.xpense.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xpense.R

@Composable
fun OnboardingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFE6F2F0)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween // Distribute elements equally
        ) {


            Spacer(modifier = Modifier.height(32.dp))
            // Top Image
            Image(
                painter = painterResource(id = R.drawable.img), // replace with your image
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(300.dp), // Adjust height for the image
                contentScale = ContentScale.Fit
            )


            Text(
                text = "Spend Smarter\nSave More",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF438883),
                textAlign = TextAlign.Center
            )

            // Get Started Button
            Button(
                onClick = { /* TODO: Handle click */ },
                shape = RoundedCornerShape(50.dp), // Capsule shape
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(56.dp),
                elevation = ButtonDefaults.elevation(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2A7C76))
            ) {
                Text(text = "Get Started", color = Color.White, fontSize = 18.sp)
            }

            // Already have account text\
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp, top = 8.dp) // Add padding at the bottom
            ) {
                Text(
                    text = "Already Have Account?",
                    fontSize = 14.sp,
                    color = Color(0xFF8A8A8A)
                )
                Spacer(modifier = Modifier.width(4.dp))
                TextButton(onClick = { /* TODO: Navigate to login */ }) {
                    Text(text = "Log In", fontSize = 14.sp, color = Color(0xFF4A6160))
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewOnboardingScreen() {
    OnboardingScreen()
}
