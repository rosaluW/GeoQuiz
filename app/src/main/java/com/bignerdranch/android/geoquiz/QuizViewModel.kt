package com.bignerdranch.android.geoquiz

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"

const val IS_CHEATER_KEY = "IS_CHEATER_KEY"
class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val questionBank = listOf(
        Question(R.string.question_cpp, false, false),
        Question(R.string.question_cs_is_fun, true, false),
        Question(R.string.question_independence_day, true, false),
        Question(R.string.question_heartbeat, false, false),
        Question(R.string.question_national_parks, true, false),
        Question(R.string.question_first_president, false, false))

    var isCheater: Boolean
        get() = savedStateHandle.get(IS_CHEATER_KEY) ?: false
        set(value) = savedStateHandle.set(IS_CHEATER_KEY, value)

    private var currentIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    val currentQuestionIsAnswered: Boolean
        get() = questionBank[currentIndex].isAnswered

    fun setIsAnswered() {
        questionBank[currentIndex].isAnswered = true
    }

    fun moveToNext() {
        if (currentIndex != (questionBank.size - 1))
            currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrevious() {
        if (currentIndex != 0)
            currentIndex -= 1
    }
}