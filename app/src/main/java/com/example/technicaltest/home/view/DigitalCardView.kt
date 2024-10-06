package com.example.technicaltest.home.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.technicaltest.home.state.CardState

@Composable
fun DigitalCardView(
    modifier: Modifier = Modifier,
    card: CardState
) {
    Card(
        onClick = { },
        modifier = modifier
            .width(300.dp)
            .height(200.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF4EA8E9)
        ),
        elevation = CardDefaults.cardElevation(
            focusedElevation = 16.dp,
            hoveredElevation = 16.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (card.isLoading) {
                CircularProgressIndicator(
                    color = Color.White,
                    strokeWidth = 2.dp,
                    strokeCap = StrokeCap.Round,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = card.cardNumber,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier.wrapContentWidth(),
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = card.expiresDate,
                        color = Color.White,
                        fontSize = 20.sp,
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        modifier = Modifier.padding(end = 16.dp),
                        text = maskedCVV(card.cvv),
                        color = Color.White,
                        fontSize = 20.sp,
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

fun maskedCVV(cvv: String): String {
    return cvv.replace(Regex("\\d"), "*")

}

@Composable
@Preview
fun CardPReview() {
    DigitalCardView(
        card = CardState(
            cardNumber = "1234 1234 1234",
            expiresDate = "10/10",
            cvv = "1234"
        )
    )
}