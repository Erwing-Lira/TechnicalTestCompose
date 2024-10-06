package com.example.technicaltest.movement.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RowTex(
    modifier: Modifier = Modifier,
    leftInformation: String,
    rightInformation: String,
    lasItem: Boolean = false
) {
    Row(
        modifier = modifier.padding(8.dp)
    ) {
        Text(
            text = "$leftInformation:",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier.wrapContentSize()
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = rightInformation,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray,
            fontSize = 15.sp,
            modifier = Modifier.wrapContentSize()
        )
    }
    if (!lasItem) {
        Spacer(modifier = Modifier.size(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun RowTextPreview() {
    RowTex(
        leftInformation = "Destination",
        rightInformation = "Test"
    )
}