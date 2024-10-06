package com.example.technicaltest.signin.view

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.technicaltest.R
import com.example.technicaltest.signin.state.ProcessState
import com.example.technicaltest.signin.viewmodel.SignInViewModel
import com.example.technicaltest.views.buttons.ButtonLoader
import com.example.technicaltest.views.footer.Footer
import com.example.technicaltest.views.inputs.InputText

@Preview(showBackground = true)
@Composable
fun SignInView(
    context: Context = LocalContext.current,
    onNavigateToSignUp: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    viewModel: SignInViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val processState = viewModel.processState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = processState.value) {
        when (processState.value) {
            ProcessState.Failure -> {
                Toast.makeText(context, "Email or Password my be wrong", Toast.LENGTH_SHORT).show()
                viewModel.resetProcessState()
            }
            ProcessState.Success -> {
                onNavigateToHome()
            }
            ProcessState.StandBy -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
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
                    onClick = viewModel::onPasswordVisibilityChange
                ) {
                    Icon(
                        painter = iconImage,
                        contentDescription = stringResource(id = R.string.password_visibility)
                    )
                }
            },
            onTextChanged = viewModel::onPasswordChange
        )
        Spacer(modifier = Modifier.size(16.dp))
        ButtonLoader(
            modifier = Modifier.fillMaxWidth(),
            isEnabled = state.value.isButtonEnabled,
            buttonState = state.value.buttonState,
            buttonText = stringResource(id = R.string.login),
            onClickListener = viewModel::authenticate
        )
        Spacer(modifier = Modifier.size(16.dp))
        Footer(
            descriptionText = stringResource(id = R.string.signin_account),
            linkText = stringResource(id = R.string.signin_signup),
            modifier = Modifier.fillMaxWidth(),
            onLinkTextListener = {
                onNavigateToSignUp()
            }
        )
    }
}