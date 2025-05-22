package com.example.funwithflags.viewmodel

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.funwithflags.R
import com.example.funwithflags.data.FlagDatabase
import com.example.funwithflags.data.FlagEntity
import com.example.funwithflags.data.repository.FlagRepository.FlagRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FlagViewModel(application: Application) : AndroidViewModel(application) {

    // Repository and DB
    private val repository: FlagRepository
    val allFlags: Flow<List<FlagEntity>>
    val favoriteFlags: Flow<List<FlagEntity>>

    // Quiz questions
    val quizQuestions = listOf(
        QuizQuestion(
            flagImage = R.drawable.flag_us,
            correctAnswer = "United States",
            options = listOf("Canada", "Mexico", "United States", "Brazil")
        ),
        QuizQuestion(
            flagImage = R.drawable.flag_uk,
            correctAnswer = "United Kingdom",
            options = listOf("Australia", "United Kingdom", "New Zealand", "Fiji")
        ),
        QuizQuestion(
            flagImage = R.drawable.flag_ie,
            correctAnswer = "Ireland",
            options = listOf("Iran", "Ivory Coast", "Ireland", "Iraq")
        ),

        QuizQuestion(
            flagImage = R.drawable.flag_ng,
            correctAnswer = "Nigeria",
            options = listOf("Lebanon", "Ireland", "Nigeria", "Jamaica")
        ),

        QuizQuestion(
            flagImage = R.drawable.flag_sd,
            correctAnswer = "Sudan",
            options = listOf("Palestine", "Kuwait", "Sudan", "United Arab Emirates")
        ),

        QuizQuestion(
            flagImage = R.drawable.flag_nd,
            correctAnswer = "The Netherlands",
            options = listOf("The Netherlands", "Russia", "France", "Luxembourg")
        ),

        QuizQuestion(
            flagImage = R.drawable.flag_ch,
            correctAnswer = "China",
            options = listOf("Taiwan", "Mongolia", "China", "Nepal")
        ),

        QuizQuestion(
            flagImage = R.drawable.flag_as,
            correctAnswer = "Austria",
            options = listOf("Australia", "Latvia", "Malta", "Austria")
        ),

        QuizQuestion(
            flagImage = R.drawable.flag_in,
            correctAnswer = "India",
            options = listOf("Indonesia", "Pakistan", "Sri Lanka", "India")
        ),

        QuizQuestion(
            flagImage = R.drawable.flag_sk,
            correctAnswer = "South Korea",
            options = listOf("North Korea", "South Korea", "Japan", "Indonesia")
        ),
    )

    // Quiz state (exposed as State<Int>)
    private var _score = mutableIntStateOf(0)
    val score: State<Int> get() = _score

    private var _questionCount = mutableIntStateOf(1)
    val questionCount: State<Int> get() = _questionCount

    private var _currentQuizIndex by mutableIntStateOf(0)
    val currentQuestion: QuizQuestion get() = quizQuestions[_currentQuizIndex]

    init {
        val dao = FlagDatabase.getDatabase(application).flagDao()
        repository = FlagRepository(dao)
        allFlags = repository.allFlags
        favoriteFlags = repository.getFlagsByFavoriteStatus(true)
    }

    // Quiz logic
    fun onAnswerSelected(isCorrect: Boolean) {
        if (isCorrect) {
            _score.value++
        }
    }

    fun nextQuestion() {
        _currentQuizIndex = (_currentQuizIndex + 1) % quizQuestions.size
        _questionCount.value++
    }

    fun resetQuiz() {
        _currentQuizIndex = 0
        _score.value = 0
        _questionCount.value = 1
    }

    // Database actions
    fun insertFlag(flag: FlagEntity) = viewModelScope.launch {
        repository.insertFlags(listOf(flag))
    }

    fun toggleFavorite(flag: FlagEntity) = viewModelScope.launch {
        repository.toggleFavorite(flag)
    }

    suspend fun getFlagById(id: Int): FlagEntity? {
        return repository.getFlagById(id)
    }

    // Quiz question model
    data class QuizQuestion(
        val flagImage: Int,
        val correctAnswer: String,
        val options: List<String>
    ) {
        val shuffledOptions: List<String> = options.shuffled()
    }
}
