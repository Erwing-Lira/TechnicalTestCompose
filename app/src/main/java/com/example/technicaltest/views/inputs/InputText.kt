package com.example.technicaltest.views.inputs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InputText(
    value: String,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean,
    errorMessage: String? = null,
    labelText: @Composable () -> Unit,
    trailingIcon: (@Composable () -> Unit)? = null,
    onTextChanged: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        maxLines = 1,
        singleLine = true,
        label = labelText,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        value = value,
        supportingText = {
            if (isError && errorMessage != null) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        onValueChange = { onTextChanged(it) },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFFFAFAFA),
            focusedContainerColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color(0xFF7EA8E9),
            unfocusedIndicatorColor = Color(0xFF7EA8E9),
            errorContainerColor = Color.Transparent,
        ),
        isError = isError,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation
    )
}

@Preview(showBackground = true)
@Composable
fun InputTextPreview() {
    InputText(
        value = "",
        keyboardType = KeyboardType.Email,
        isError = false,
        errorMessage = "Must be an email",
        labelText = {
            Text(text = "Username")
        },
        onTextChanged = {

        }
    )
}