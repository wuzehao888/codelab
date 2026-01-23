package com.wzh.unscramble.ui

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wzh.unscramble.R
import com.wzh.unscramble.data.MAX_NO_OF_WORDS

/**
 * create by hao
 * 2026/1/21
 */
@Composable
fun UnscrambleScreen(
    viewModel: GameViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val gameUiState by viewModel.uiState.collectAsStateWithLifecycle()
    Column(
        modifier = modifier.padding(30.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.app_name),
            style = MaterialTheme.typography.titleMedium
        )
        UnscrambleCard(
            num = gameUiState.rightNum,
            total = MAX_NO_OF_WORDS,
            word = gameUiState.currentWord,
            input = viewModel.input,
            onValueChange = {
                viewModel.updateInput(it)
            },
            isError = gameUiState.inputError,
            keyboardDone = {
                viewModel.checkGuess()
            }
        )
        Button(onClick = {
            viewModel.checkGuess()
        }, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.submit))
        }
        OutlinedButton(onClick = {
            viewModel.skip()
        }, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.skip))
        }

        ScoreStatus(gameUiState.score)

        if (gameUiState.finish) {
            FinalDialog(
                playAgain = { viewModel.reset() },
                score = gameUiState.score
            )
        }
    }
}

@Composable
fun FinalDialog(
    playAgain: () -> Unit,
    score: Int,
    modifier: Modifier = Modifier
) {
    val activity = LocalContext.current as Activity
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {},
        confirmButton = {
            TextButton(playAgain) {
                Text(
                    stringResource(R.string.play_again)
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {
                activity.finish()
            }) {
                Text(
                    stringResource(R.string.exit)
                )
            }
        },
        title = {
            Text(
                stringResource(R.string.congratulations),
                style = MaterialTheme.typography.displaySmall
            )
        },
        text = {
            Text(
                stringResource(
                    R.string.you_scored,
                    score
                )
            )
        },
    )
}

@Composable
fun ScoreStatus(score: Int, modifier: Modifier = Modifier) {
    Text(
        stringResource(R.string.score, score),
        style = MaterialTheme.typography.headlineMedium,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray)
            .padding(vertical = 10.dp, horizontal = 15.dp)
    )
}

@Composable
fun UnscrambleCard(
    num: Int,
    total: Int,
    word: String,
    input: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    keyboardDone: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(R.string.word_count, num, total),
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(
                        Color(0xFFBAC3FF)
                    )
                    .padding(horizontal = 10.dp, vertical = 4.dp)
                    .align(Alignment.End)
            )

            Text(
                text = word,
                style = MaterialTheme.typography.displayMedium
            )

            Text(
                text = stringResource(R.string.instructions),
                style = MaterialTheme.typography.bodyMedium
            )

            OutlinedTextField(
                value = input,
                onValueChange = onValueChange,
                isError = isError,
                singleLine = true,
                label = {
                    if (isError) {
                        Text(stringResource(R.string.wrong_guess))
                    } else {
                        Text(stringResource(R.string.enter_your_word))
                    }
                },
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions {
                    keyboardDone()
                },
                modifier = Modifier
                    .fillMaxWidth()

            )
        }
    }
}