package com.example.technicaltest.signup.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.technicaltest.signup.viewmodel.SignUpViewModel
import com.example.technicaltest.views.buttons.ButtonLoader
import com.example.technicaltest.views.footer.Footer
import com.example.technicaltest.views.inputs.InputText

@Preview(showBackground = true)
@Composable
fun SignUpView(
    onNavigateUp: () -> Unit = {},
    viewmodel: SignUpViewModel = hiltViewModel(),
) {
    val state = viewmodel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        InputText(
            value = state.value.name,
            keyboardType = KeyboardType.Text,
            isError = state.value.isNameWrong,
            errorMessage = "It should only have letters",
            labelText = {
                Text(text = "Name")
            },
            onTextChanged = viewmodel::onNameChanged
        )
        Spacer(modifier = Modifier.size(8.dp))
        InputText(
            value = state.value.lastName,
            keyboardType = KeyboardType.Text,
            isError = state.value.isLastNameWrong,
            errorMessage = "It should only have letters",
            labelText = {
                Text(text = "Last name")
            },
            onTextChanged = viewmodel::onLastNameChanged
        )
        Spacer(modifier = Modifier.size(8.dp))
        InputText(
            value = state.value.email,
            keyboardType = KeyboardType.Email,
            isError = state.value.isEmailWrong,
            errorMessage = "It should be a valid email",
            labelText = {
                Text(text = "Email")
            },
            onTextChanged = viewmodel::onEmailChanged
        )
        Spacer(modifier = Modifier.size(8.dp))
        InputText(
            value = state.value.password,
            keyboardType = KeyboardType.Password,
            isError = state.value.isPassWrong,
            errorMessage = "Must contain at least 6 letter",
            visualTransformation = if (state.value.passwordVisibility) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            labelText = {
                Text(text = "Password")
            },
            trailingIcon = {
                val iconImage = if (state.value.passwordVisibility) {
                    Icons.Filled.Search
                } else {
                    Icons.Filled.Lock
                }
                IconButton(
                    onClick = viewmodel::onPasswordVisibility
                ) {
                    Icon(imageVector = iconImage, contentDescription = "Visibility")
                }
            },
            onTextChanged = viewmodel::onPasswordChanged
        )
        Spacer(modifier = Modifier.size(8.dp))
        InputText(
            value = state.value.repeatPassword,
            keyboardType = KeyboardType.Password,
            isError = state.value.isRepeatPassWrong,
            errorMessage = "Must contain at least 6 letter",
            visualTransformation = if (state.value.repeatPasswordVisibility) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            labelText = {
                Text(text = "Repeat password")
            },
            trailingIcon = {
                val iconImage = if (state.value.repeatPasswordVisibility) {
                    Icons.Filled.Search
                } else {
                    Icons.Filled.Lock
                }
                IconButton(
                    onClick = viewmodel::onRepeatPasswordVisibility
                ) {
                    Icon(imageVector = iconImage, contentDescription = "Visibility")
                }
            },
            onTextChanged = viewmodel::onRepeatPasswordChanged
        )
        Spacer(modifier = Modifier.size(16.dp))
        ButtonLoader(
            modifier = Modifier.fillMaxWidth(),
            isEnabled = state.value.isButtonEnabled,
            buttonState = state.value.buttonState,
            buttonText = "Create account",
            onClickListener = {
                viewmodel.authenticate()
            }
        )
        Spacer(modifier = Modifier.size(16.dp))
        Footer(
            descriptionText = "You have an account",
            linkText = "Sign in",
            modifier = Modifier.fillMaxWidth(),
            onLinkTextListener = {
                onNavigateUp()
            }
        )
    }
}