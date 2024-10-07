package com.example.technicaltest.movement.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.technicaltest.R
import com.example.technicaltest.home.model.Movement
import com.example.technicaltest.home.state.MovementType
import com.example.technicaltest.utils.formatDate
import java.util.Date

@Composable
fun MovementDetailView(
    movement: Movement,
    onNavigateUp: () -> Unit = {},
) {
    BackHandler {
        onNavigateUp()
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .systemBarsPadding()
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.movement_detail_title),
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.size(8.dp))
        QuantityView(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally),
            quantity = movement.money,
            movementType = movement.movementType
        )
        Spacer(modifier = Modifier.size(16.dp))
        RowTex(
            leftInformation = stringResource(id = R.string.movement_detail_date),
            rightInformation = Date(movement.date).formatDate()
        )
        RowTex(
            leftInformation = stringResource(id = R.string.movement_detail_destination),
            rightInformation = movement.destination
        )
        RowTex(
            leftInformation = stringResource(id = R.string.movement_detail_concept),
            rightInformation = movement.concept
        )
        RowTex(
            leftInformation = stringResource(id = R.string.movement_detail_reference),
            rightInformation = movement.reference
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MovementDetailViewPreview() {
    MovementDetailView(
        movement = Movement(
            id = "",
            date = 0L,
            destination = "Paco",
            reference = "345678rfcvr",
            money = 89.07,
            concept = "Prueba",
            movementType = MovementType.Income
        )
    )
}