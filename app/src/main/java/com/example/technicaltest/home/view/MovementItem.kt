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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.technicaltest.home.state.Movement

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
                    text = movement.destiny,
                    fontSize = 20.sp,
                    color = Color(0xFF424242)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = movement.reference,
                    fontSize = 15.sp,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            val (colorText, sign) = when(movement.movementType) {
                MovementType.Income -> Color.Green to "+"
                MovementType.Outgoing -> Color.Red to "-"
            }
            Text(
                text = "$sign${movement.money}",
                color = colorText,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Go detail",
                tint = Color(0xFF2196F3)
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovementItemPreview() {
    MovementItem(
        movement = Movement(
            "Date",
            "Destiny",
            "37tfg435yuf",
            "50.00",
            movementType = MovementType.Income
        ),
        onMovementClick = {

        }
    )
}