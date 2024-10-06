package com.example.technicaltest.home.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.technicaltest.R
import com.example.technicaltest.home.model.Movement
import com.example.technicaltest.home.state.ProcessState
import com.example.technicaltest.home.viewmodel.HomeViewModel
import com.example.technicaltest.utils.json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
@Preview(showBackground = true)
fun HomeView(
    viewModel: HomeViewModel = hiltViewModel(),
    onLogoutClicked: () -> Unit = {},
    onMovementClicked: (String) -> Unit = {},
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val userState = viewModel.userState.collectAsStateWithLifecycle()
    val cardState = viewModel.cardState.collectAsStateWithLifecycle()
    val movementsState = viewModel.movementsState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = state.value.isLogOut) {
        if (state.value.isLogOut) {
            onLogoutClicked()
        }
    }

    BackHandler {
        viewModel.showDialog()
    }

    if (state.value.showLogOut) {
        LogOutDialog(
            onConfirm = {
                viewModel.onLogOut()
            },
            onDismiss = {
                viewModel.hideDialog()
            }
        )
    }

    when (userState.value.processState) {
        ProcessState.Failure -> {
            ErrorView(
                modifier = Modifier.fillMaxSize(),
                onLogOutListener = viewModel::onLogOut
            )
        }
        ProcessState.Loading,
        ProcessState.Success,
        -> {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .systemBarsPadding()
                    .fillMaxWidth()
            ) {
                Row {
                    Column {
                        Text(
                            text = stringResource(id = R.string.home_greeting),
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = userState.value.name,
                            fontSize = 20.sp
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = viewModel::showDialog
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = stringResource(id = R.string.log_out_icon_description),
                            tint = Color.Red
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                DigitalCardView(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    card = cardState.value
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.home_movements_title),
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = stringResource(id = R.string.home_sorted),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    IconButton(
                        onClick = {
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = stringResource(id = R.string.home_sorted_icon_description)
                        )
                    }
                }

                when (movementsState.value.processState) {
                    ProcessState.Failure -> {
                        WithoutMovementsView(
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }

                    ProcessState.Loading -> {
                        CircularProgressIndicator(
                            color = Color(0xFF4EA8E9),
                            strokeWidth = 5.dp,
                            strokeCap = StrokeCap.Round,
                            modifier = Modifier
                                .size(100.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }

                    ProcessState.Success -> {
                        MovementList(
                            movementsList = movementsState.value.movementList,
                            onMovementListener = { movement ->
                                onMovementClicked(
                                    json.encodeToString(movement)
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}