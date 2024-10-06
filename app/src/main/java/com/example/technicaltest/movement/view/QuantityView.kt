package com.example.technicaltest.movement.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.technicaltest.R
import com.example.technicaltest.home.state.MovementType
import com.example.technicaltest.utils.formatAmount

@Composable
fun QuantityView(
    modifier: Modifier = Modifier,
    quantity: Double,
    movementType: MovementType
) {
    val (icon, color) = when(movementType) {
        MovementType.Expense -> Icons.Filled.KeyboardArrowDown to Color.Red
        MovementType.Income -> Icons.Filled.KeyboardArrowUp to Color.Green
    }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = modifier
                .wrapContentHeight(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                focusedElevation = 16.dp,
                hoveredElevation = 16.dp
            ),
            shape = CircleShape
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.wrapContentHeight()
            ) {
                Icon(
                    modifier = Modifier.wrapContentHeight(),
                    imageVector = icon,
                    contentDescription = stringResource(id = R.string.icon_movement_type),
                    tint = color,
                )
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            color = color,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            text = quantity.formatAmount(),
        )
    }
}

@Preview
@Composable
fun QuantityViewPreviewIncome() {
    QuantityView(
        quantity = 30.98,
        movementType = MovementType.Income
    )
}

@Preview
@Composable
fun QuantityViewPreviewExpense() {
    QuantityView(
        quantity = 30.98,
        movementType = MovementType.Expense
    )
}