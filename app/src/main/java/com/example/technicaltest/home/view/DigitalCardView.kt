package com.example.technicaltest.home.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.technicaltest.R
import com.example.technicaltest.home.model.CardInformation
import com.example.technicaltest.home.state.CardState
import com.example.technicaltest.home.state.ProcessState
import com.example.technicaltest.signup.util.getDate
import com.example.technicaltest.utils.formatExpiration
import com.example.technicaltest.utils.maskedCVV
import com.example.technicaltest.utils.maskedCardNumber
import com.google.firebase.Timestamp
import java.util.Calendar

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
            when (card.processState) {
                ProcessState.Failure -> {
                    Card(
                        modifier = Modifier
                            .width(300.dp)
                            .height(200.dp)
                            .align(Alignment.CenterHorizontally),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF4EA8E9)
                        ),
                        elevation = CardDefaults.cardElevation(
                            focusedElevation = 16.dp,
                            hoveredElevation = 16.dp
                        )
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = stringResource(id = R.string.home_icon_empty_card)
                            )
                        }
                    }
                }
                ProcessState.Loading -> {
                    CircularProgressIndicator(
                        color = Color.White,
                        strokeWidth = 2.dp,
                        strokeCap = StrokeCap.Round,
                        modifier = Modifier.size(24.dp)
                    )
                }
                ProcessState.Success -> {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = card.cardInformation.cardNumber.maskedCardNumber(),
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
                            text = card.cardInformation.expiresDate.formatExpiration(),
                            color = Color.White,
                            fontSize = 20.sp,
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            modifier = Modifier.padding(end = 16.dp),
                            text = card.cardInformation.cvv.maskedCVV(),
                            color = Color.White,
                            fontSize = 20.sp,
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
@Preview
fun CardPReview() {
    val calendar = Calendar.getInstance()
    calendar.set(2027, Calendar.FEBRUARY, 24)
    val timeStamps = Timestamp(getDate().time)
    DigitalCardView(
        card = CardState(
            cardInformation = CardInformation(
                id = "",
                cardNumber = "123412341234",
                expiresDate = timeStamps.toDate(),
                cvv = "1234"
            ),
            processState = ProcessState.Success
        )
    )
}