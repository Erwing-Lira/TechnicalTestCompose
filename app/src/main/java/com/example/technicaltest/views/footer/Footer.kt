package com.example.technicaltest.views.footer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Footer(
    descriptionText: String,
    linkText: String,
    modifier: Modifier = Modifier,
    onLinkTextListener: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = descriptionText,
            fontSize = 12.sp,
            color = Color(0xFF757575)
        )
        Text(
            text = linkText,
            fontSize = 13.sp,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clickable {
                    onLinkTextListener()
                },
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4EA8E9)
        )
    }
}