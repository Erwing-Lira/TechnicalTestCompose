package com.example.technicaltest.home.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.technicaltest.home.state.Movement

@Composable
fun MovementList(
    movementsList: Map<String, List<Movement>>,
    onMovementListener: (String) -> Unit
) {
    LazyColumn {
        movementsList.forEach { (date, movementList) ->
            item {
                Text(
                    text = date,
                    modifier = Modifier.padding(8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
            itemsIndexed(movementList) { index, movement ->
                val elementType = when {
                    movementList.size == 1 -> ElementType.Only
                    index == 0 -> ElementType.First
                    movementList.lastIndex == index -> ElementType.Last
                    else -> ElementType.Middle
                }
                MovementItem(
                    elementType = elementType,
                    movement = movement,
                    onMovementClick = {
                        onMovementListener(movement.reference)
                    }
                )
                if (index < movementList.lastIndex) {
                    HorizontalDivider(
                        color = Color.LightGray,
                        thickness = 1.dp

                    )
                }
            }
        }
    }
}