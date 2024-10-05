package com.example.technicaltest.home.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.technicaltest.home.state.Card
import com.example.technicaltest.home.state.Movement
import com.example.technicaltest.home.viewmodel.HomeViewModel

@Composable
@Preview(showBackground = true)
fun HomeView(
    viewModel: HomeViewModel = hiltViewModel(),
    onMovementClicked: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row {
            Column {
                Text(
                    text = "¡Hola!",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Name",
                    fontSize = 15.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "Descending",
                    tint = Color.Red
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        CardView(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            card = Card(
                "1234 1234 1234",
                "10/10",
                "1234"
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sorted by",
                modifier = Modifier.padding(vertical = 4.dp)
            )
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowUp,
                    contentDescription = "Descending"
                )
            }
        }

        val dividerList: Map<String, List<Movement>> = getList().sortedByDescending {
            it.date
        }.groupBy {
            it.date
        }
        LazyColumn {
            dividerList.forEach { (date, movementList) ->
                item {
                    Text(
                        text = "Date $date",
                        modifier = Modifier.padding(8.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
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
                            onMovementClicked(movement.reference)
                        }
                    )
                }
            }
        }
    }
}

fun getList() = listOf(
    Movement(
        "5/5/24",
        "Test 1",
        "87546tgvtyubhn",
        "1,000.00",
        MovementType.Income
    ),
    Movement(
        "5/5/24",
        "Test 1",
        "87546tgvtyubhn",
        "1,000.00",
        MovementType.Outgoing
    ),
    Movement(
        "2/5/24",
        "Test 1",
        "87546tgvtyubhn",
        "1,000.00",
        MovementType.Income
    ),
    Movement(
        "4/5/24",
        "Test 1",
        "87546tgvtyubhn",
        "1,000.00",
        MovementType.Income
    ),
    Movement(
        "2/5/24",
        "Test 1",
        "87546tgvtyubhn",
        "1,000.00",
        MovementType.Income
    ),
    Movement(
        "4/5/24",
        "Test 1",
        "87546tgvtyubhn",
        "1,000.00",
        MovementType.Income
    )
)