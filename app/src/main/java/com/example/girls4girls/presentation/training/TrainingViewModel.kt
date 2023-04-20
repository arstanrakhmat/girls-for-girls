package com.example.girls4girls.presentation.training

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.girls4girls.data.model.*
import com.example.girls4girls.data.repository.TrainingRepository
import kotlinx.coroutines.launch

class TrainingViewModel(private val repository: TrainingRepository) : ViewModel() {

    val upcomingTraining = MutableLiveData<TrainingResponse>()
    val pastTraining = MutableLiveData<TrainingResponse>()

    val trainingById = MutableLiveData<TrainingById>()

    val questionnaireApplySuccess = MutableLiveData<QuestionnaireApplyResponse>()
    val trainingApplySuccess = MutableLiveData<TrainingApplyResponse>()

    val error = MutableLiveData<String>()

    private val _answersMultipleChoice = mutableListOf<Int>()
    //val answersMultipleChoice: List<Int> = _answersMultipleChoice


    //TESTING
    private val _testAnswer = mutableListOf<Answer>()
    val testAnswer: List<Answer> = _testAnswer

    fun addOnTextSelected2(questionId: Int, text: String) {
        val existingAnswer = _testAnswer.find { it.questionId == questionId }

        if (existingAnswer != null) {
            existingAnswer.text = text
        } else {
//            _answers.add(TextAnswer(questionId, AnswerType.TEXT, text))
            _testAnswer.add(
                Answer(
                    questionId = questionId,
                    type = AnswerType.TEXT.toString(),
                    text = text
                )
            )
        }
    }

    fun addOnVariantSelected2(questionId: Int, answerIndex: Int) {
        for (answer in _testAnswer) {
            if (answer.type == AnswerType.VARIANTS.toString()) {
                _testAnswer.remove(answer)
            }
        }
        _testAnswer.add(
            Answer(
                questionId = questionId,
                type = AnswerType.VARIANTS.toString(),
                answerIndex = answerIndex
            )
        )
    }

    fun deleteOnVariantSelected2(questionId: Int, answerIndex: Int) {
        if (_testAnswer.find { it.questionId == questionId } != null) {
            _testAnswer.remove(
                Answer(
                    questionId = questionId,
                    type = AnswerType.VARIANTS.toString(),
                    answerIndex = answerIndex
                )
            )
        }
    }

    fun addChoice2(questionId: Int, index: Int) {
        _answersMultipleChoice.add(index)

        addOnCheckBoxSelected2(questionId, _answersMultipleChoice)
    }

//    fun delete(index: Int, questionId: Int) {
//        for (ans in _answers) {
//            if (_answers.find { it.questionId == questionId } != null) {
//                _answersMultipleChoice.remove(index)
//            }
//        }
//    }

    private fun addOnCheckBoxSelected2(questionId: Int, indexes: List<Int>) {
        if (_testAnswer.find { it.questionId == questionId } == null) {
            _testAnswer.add(
                Answer(
                    questionId = questionId,
                    type = AnswerType.CHECK_BOX.toString(),
                    multipleChoices = indexes
                )
            )
        }
    }

    fun getUpcomingTrainings(
        page: Int,
        limit: Int,
        order: String,
        orderField: String
    ) {
        viewModelScope.launch {
            val response = repository.getUpcomingTrainings(
                page, limit, order, orderField
            )

            if (response.isSuccessful) {
                upcomingTraining.postValue(response.body())
            } else {
                error.postValue(response.errorBody().toString())
            }
        }
    }

    fun getPastTrainings(
        page: Int,
        limit: Int,
        order: String,
        orderField: String
    ) {
        viewModelScope.launch {
            val response = repository.getPastTrainings(
                page, limit, order, orderField
            )

            if (response.isSuccessful) {
                pastTraining.postValue(response.body())
            } else {
                error.postValue(response.errorBody().toString())
            }
        }
    }


    fun getTrainingById(
        id: Int
    ) {
        viewModelScope.launch {
            val response = repository.getTrainingById(id)
            if (response.isSuccessful) {
                trainingById.postValue(response.body())
            } else {
                error.postValue(response.errorBody().toString())
            }
        }
    }

    fun questionnaireApply2(
        token: String, answers: QuestionnaireFillOut
    ) {
        viewModelScope.launch {
            val response = repository.questionnaireApply2(token, answers)

            if (response.isSuccessful) {
                questionnaireApplySuccess.postValue(response.body())
            } else {
                error.postValue(
                    response.errorBody().toString()
                )
            }
        }
    }

    fun trainingApply(
        token: String,
        trainingId: Int,
        questionnaireResponseId: Int
    ) {
        viewModelScope.launch {
            val response = repository.trainingApply(token, trainingId, questionnaireResponseId)

            if (response.isSuccessful) {
                trainingApplySuccess.postValue(response.body())
            } else {
                error.postValue(
                    response.errorBody().toString()
                )
            }
        }
    }
}