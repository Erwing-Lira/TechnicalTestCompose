package com.example.technicaltest.home.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.technicaltest.home.state.Card

@Composable
fun CardView(
    modifier: Modifier = Modifier,
    card: Card
) {
    Card(
        onClick = { },
        modifier = modifier
            .width(200.dp)
            .height(160.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFC107)
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
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = card.number,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.wrapContentWidth(),
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = card.expiration,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    modifier = Modifier.padding(end = 16.dp),
                    text = "****",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
@Preview
fun CardPReview() {
    CardView(
        card = Card(
            "1234 1234 1234",
            "10/10",
            "1234"
        )
    )
}