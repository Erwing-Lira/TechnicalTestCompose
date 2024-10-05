package com.example.technicaltest.views.buttons

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
fun ButtonLoader(
    modifier: Modifier = Modifier,
    isEnabled: Boolean,
    buttonState: ButtonState,
    buttonText: String,
    onClickListener: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = {
            onClickListener()
        },
        enabled = isEnabled,
        shape = RoundedCornerShape(8.dp)
    ) {
        when (buttonState) {
            ButtonState.Loading -> CircularProgressIndicator(
                color = Color.White,
                strokeWidth = 2.dp,
                strokeCap = StrokeCap.Round,
                modifier = Modifier.size(24.dp)
            )
            ButtonState.StandBy -> Text(text = buttonText)
        }
    }
}