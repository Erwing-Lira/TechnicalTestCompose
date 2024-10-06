package com.example.technicaltest.home.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.technicaltest.R
import com.example.technicaltest.home.model.Movement
import com.example.technicaltest.home.state.MovementType
import com.example.technicaltest.signup.util.getDate
import com.example.technicaltest.utils.formatAmount
import com.google.firebase.Timestamp
import java.util.Calendar

@Composable
fun MovementItem(
    elementType: ElementType = ElementType.Only,
    movement: Movement,
    onMovementClick: () -> Unit
) {
    Card(
        modifier = Modifier.wrapContentWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = when(elementType) {
            ElementType.First -> RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
            ElementType.Last -> RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
            ElementType.Middle -> RectangleShape
            ElementType.Only -> RoundedCornerShape(8.dp)
        },
        onClick = { onMovementClick() }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = movement.destination,
                    fontSize = 18.sp,
                    color = Color(0xFF424242),
                    modifier = Modifier.wrapContentWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier.padding(8.dp).wrapContentWidth(),
                    text = movement.concept,
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            val (colorText, sign) = when(movement.movementType) {
                MovementType.Income -> Color.Green to "+"
                MovementType.Expense -> Color.Red to "-"
            }
            Text(
                text = "$sign${movement.money.formatAmount()}",
                color = colorText,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = stringResource(id = R.string.home_icon_go_detail),
                tint = Color(0xFF2196F3)
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovementItemPreview() {
    val calendar = Calendar.getInstance()
    calendar.set(2027, Calendar.FEBRUARY, 24)
    val timeStamps = Timestamp(getDate().time)
    MovementItem(
        movement = Movement(
            id ="",
            date = timeStamps.toDate().time,
            destination = "Destiny",
            reference = "37tfg435yuf",
            money = 5000.00,
            movementType = MovementType.Income,
            concept = "Prueba"
        ),
        onMovementClick = {

        }
    )
}