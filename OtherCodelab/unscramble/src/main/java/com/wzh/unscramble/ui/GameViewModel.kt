package com.wzh.unscramble.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.wzh.unscramble.data.MAX_NO_OF_WORDS
import com.wzh.unscramble.data.SCORE_INCREASE
import com.wzh.unscramble.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * create by hao
 * 2026/1/23
 */
class GameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())

    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private val gameWords = mutableSetOf<String>()

    private var currentWord: String = ""

    var input by mutableStateOf("")
        private set

    init {
        reset()
    }

    private fun getGameWord(): String {
        if (gameWords.size == allWords.size) return ""
        var word = allWords.random()
        while (gameWords.contains(word)) {
            word = allWords.random()
        }
        currentWord = word
        gameWords.add(word)
        return shuffleCurrentWord(word)
    }

    private fun shuffleCurrentWord(word: String): String {
        val temp = word.toCharArray()
        temp.shuffle()//将单词打乱
        while (String(temp) == word) {//如果打乱后与原单词一样，继续打乱
            temp.shuffle()
        }
        return String(temp)
    }

    fun reset() {
        gameWords.clear()
        input = ""
        currentWord = ""
        _uiState.value = GameUiState(currentWord = getGameWord())
    }

    fun checkGuess() {
        val num = _uiState.value.rightNum
        var score = _uiState.value.score
        if (num < MAX_NO_OF_WORDS) {
            if (input.equals(currentWord, true)) {
                updateInput("")
                score += SCORE_INCREASE
                _uiState.update {
                    it.copy(
                        inputError = false,
                        currentWord = getGameWord(),
                        rightNum = it.rightNum.inc(),
                        score = score
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        inputError = true,
                        currentWord = getGameWord(),
                        rightNum = it.rightNum.inc()
                    )
                }
            }
        } else {
            _uiState.update {
                it.copy(finish = true, score = score)
            }
        }
    }

    fun updateInput(s: String) {
        input = s
    }

    fun skip() {
        updateInput("")
        val num = _uiState.value.rightNum
        if (num < MAX_NO_OF_WORDS) {
            _uiState.update {
                it.copy(
                    inputError = false,
                    currentWord = getGameWord(),
                    rightNum = it.rightNum.inc()
                )
            }
        } else {
            _uiState.update {
                it.copy(finish = true)
            }
        }
    }
}