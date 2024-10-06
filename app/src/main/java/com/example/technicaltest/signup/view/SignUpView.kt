package com.example.technicaltest.signup.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.technicaltest.R
import com.example.technicaltest.signin.state.ProcessState
import com.example.technicaltest.signup.viewmodel.SignUpViewModel
import com.example.technicaltest.views.buttons.ButtonLoader
import com.example.technicaltest.views.footer.Footer
import com.example.technicaltest.views.inputs.InputText

@Preview(showBackground = true)
@Composable
fun SignUpView(
    onNavigateUp: () -> Unit = {},
    onSignUpSuccess: () -> Unit = {},
    context: Context = LocalContext.current,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val processState = viewModel.processState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = processState.value) {
        when (processState.value) {
            ProcessState.Failure -> {
                Toast.makeText(context, "It should be an error, try again", Toast.LENGTH_SHORT).show()
                viewModel.resetProcessState()
            }
            ProcessState.Success -> {
                onSignUpSuccess()
            }
            ProcessState.StandBy -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .systemBarsPadding()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            color = Color(0xFF4EA8E9),
            text = stringResource(id = R.string.signup_title),
            fontSize = 18.sp,
        )
        Spacer(modifier = Modifier.size(8.dp))
        InputText(
            value = state.value.name,
            keyboardType = KeyboardType.Text,
            isError = state.value.isNameWrong,
            errorMessage = stringResource(id = R.string.signup_error_text),
            labelText = {
                Text(text = stringResource(id = R.string.signup_name))
            },
            onTextChanged = viewModel::onNameChanged
        )
        Spacer(modifier = Modifier.size(8.dp))
        InputText(
            value = state.value.lastName,
            keyboardType = KeyboardType.Text,
            isError = state.value.isLastNameWrong,
            errorMessage = stringResource(id = R.string.signup_error_text),
            labelText = {
                Text(text = stringResource(id = R.string.signup_last_name))
            },
            onTextChanged = viewModel::onLastNameChanged
        )
        Spacer(modifier = Modifier.size(8.dp))
        InputText(
            value = state.value.email,
            keyboardType = KeyboardType.Email,
            isError = state.value.isEmailWrong,
            errorMessage = stringResource(id = R.string.email_error),
            labelText = {
                Text(text = stringResource(id = R.string.email))
            },
            onTextChanged = viewModel::onEmailChanged
        )
        Spacer(modifier = Modifier.size(8.dp))
        InputText(
            value = state.value.password,
            keyboardType = KeyboardType.Password,
            isError = state.value.isPassWrong,
            errorMessage = stringResource(id = R.string.password_error),
            visualTransformation = if (state.value.passwordVisibility) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            labelText = {
                Text(text = stringResource(id = R.string.password))
            },
            trailingIcon = {
                val iconImage = if (state.value.passwordVisibility) {
                    painterResource(id = R.drawable.visibility_off)
                } else {
                    painterResource(id = R.drawable.visibility)
                }
                IconButton(
                    onClick = viewModel::onPasswordVisibility
                ) {
                    Icon(
                        painter = iconImage,
                        contentDescription = stringResource(id = R.string.password_visibility)
                    )
                }
            },
            onTextChanged = viewModel::onPasswordChanged
        )
        Spacer(modifier = Modifier.size(8.dp))
        InputText(
            value = state.value.repeatPassword,
            keyboardType = KeyboardType.Password,
            isError = state.value.isRepeatPassWrong,
            errorMessage = stringResource(id = R.string.password_error),
            visualTransformation = if (state.value.repeatPasswordVisibility) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            labelText = {
                Text(text = stringResource(id = R.string.signup_repeat_password))
            },
            trailingIcon = {
                val iconImage = if (state.value.repeatPasswordVisibility) {
                    painterResource(id = R.drawable.visibility_off)
                } else {
                    painterResource(id = R.drawable.visibility)
                }
                IconButton(
                    onClick = viewModel::onRepeatPasswordVisibility
                ) {
                    Icon(
                        painter = iconImage,
                        contentDescription = stringResource(id = R.string.password_visibility)
                    )
                }
            },
            onTextChanged = viewModel::onRepeatPasswordChanged
        )
        if (!state.value.bothPasswordMatches) {
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                color = Color.Red,
                text = stringResource(id = R.string.signup_both_password_error),
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            color = Color(0xFF4EA8E9),
            text = stringResource(id = R.string.add_photo_description),
            fontSize = 15.sp,
        )
        Spacer(modifier = Modifier.size(8.dp))
        CameraCapture(
            context = context,
            onImageCapture = viewModel::onPhotoChanged
        )
        Spacer(modifier = Modifier.size(16.dp))
        ButtonLoader(
            modifier = Modifier.fillMaxWidth(),
            isEnabled = state.value.isButtonEnabled,
            buttonState = state.value.buttonState,
            buttonText = stringResource(id = R.string.signup_create_button),
            onClickListener = {
                viewModel.registry()
            }
        )
        Spacer(modifier = Modifier.size(16.dp))
        Footer(
            descriptionText = stringResource(id = R.string.signup_account),
            linkText = stringResource(id = R.string.signup_signin),
            modifier = Modifier.fillMaxWidth(),
            onLinkTextListener = {
                onNavigateUp()
            }
        )
    }
}