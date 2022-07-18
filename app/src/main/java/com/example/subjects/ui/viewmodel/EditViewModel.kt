package com.example.subjects.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.subjects.R
import com.example.subjects.data.entities.Qualification
import com.example.subjects.data.entities.Subject
import com.example.subjects.data.entities.SubjectWithQualification
import com.example.subjects.data.repository.SubRepository
import com.example.subjects.util.enums.SubjectStatue
import com.example.subjects.util.enums.SubjectStatue.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val repository: SubRepository
) : ViewModel() {

    val getIcons = repository.getIconsForEditFragment()

    private val _teacherName = MutableStateFlow("")
    private val _mark = MutableStateFlow("0")
    private val _practiceMark = MutableStateFlow("0")
    private val _subStatue = MutableStateFlow(AVAILABLE)

    private val _nextSubject = MutableStateFlow("")
    private val nextSubject: StateFlow<String> = _nextSubject

    private val _nextSubjectSecond = MutableStateFlow("")
    private val nextSubjectSecond: StateFlow<String> = _nextSubjectSecond

    private val _nextSubjectThird = MutableStateFlow("")
    private val nextSubjectThird: StateFlow<String> = _nextSubjectThird

    private val _icon = MutableStateFlow(R.drawable.zero_one)
    val icon = _icon.asStateFlow()

    private val _messageStatue = MutableStateFlow("")

    private val _editSubEventChannel = Channel<TaskEvent>()
    val editSubEventChannel = _editSubEventChannel.receiveAsFlow()

    fun setTeacherName(teacherName: String) {
        _teacherName.value = teacherName
    }

    fun setMark(mark: String) {
        _mark.value = mark
    }

    fun setPracticeMark(practiceMark: String) {
        _practiceMark.value = practiceMark
    }

    fun setIcon(icon: Int) {
        _icon.value = icon
    }

    fun setSubStatue(statue: SubjectStatue) {
        _subStatue.value = statue
    }

    fun setNextSubjectFirst(nextSubjectName: String) {
        _nextSubject.value = nextSubjectName
    }

    fun setNextSubjectSecond(nextSubjectName: String) {
        _nextSubjectSecond.value = nextSubjectName
    }

    fun setNextSubjectThird(nextSubjectName: String) {
        _nextSubjectThird.value = nextSubjectName
    }

    suspend fun onSaveClick(subject: Subject, qualification: Qualification) {
        if (_teacherName.value.isBlank()) {
            TaskEvent.ShowErrorMessage("Teacher name cannot be empty")
            return
        }
        if (_mark.value.isBlank()) {
            TaskEvent.ShowErrorMessage("Mark name cannot be empty")
            return
        }
        if (_practiceMark.value.isBlank()) {
            TaskEvent.ShowErrorMessage("practiceMark name cannot be empty")
            return
        }
        if (!isStatueRight()) {
            _editSubEventChannel.send(TaskEvent.ShowErrorMessage(_messageStatue.value))
            return
        } else {
            updateSubjects(subject, qualification)
            _editSubEventChannel.send(TaskEvent.ShowMessageAndBack(_messageStatue.value))
        }
    }

    private suspend fun updateSubjects(subject: Subject, qualification: Qualification) {
        val updateSub = subject.copy(
            teacherName = _teacherName.value,
            finalMark = _mark.value,
            practicalMarks = _practiceMark.value,
            subjectImage = _icon.value
        )
        val updateQualification = qualification.copy(subjectStatue = _subStatue.value)
        when (qualification.subjectStatue?.name) {
            SUCCESS.name -> {
                when(_subStatue.value)
                {
                    SUCCESS ->{
                        _messageStatue.value = "Success editing but can't change statue"

                        viewModelScope.launch {
                            repository.updateSubject(updateSub)
                        }
                    }
                    else ->{
                        _messageStatue.value ="You Can't Change Subject Statue"

                        viewModelScope.launch {
                            repository.updateSubject(updateSub)
                        }
                    }
                }
            }
            else ->{
                when(_subStatue.value)
                {
                    SUCCESS ->{
                        _messageStatue.value ="Putting Subject Success is success"

                        viewModelScope.launch {
                            repository.updateQualification(updateQualification)
                        }

                        viewModelScope.launch {
                            repository.updateSubject(updateSub)
                        }
                    }
                    else ->{
                        _messageStatue.value ="Success Editing"

                        viewModelScope.launch {
                            repository.updateQualification(updateQualification)
                        }

                        viewModelScope.launch {
                            repository.updateSubject(updateSub)
                        }
                    }
                }
            }
        }
    }

    private fun isStatueRight(): Boolean {
        when (_subStatue.value) {
            AVAILABLE -> {
                return if (_practiceMark.value.toInt() > 0 || _mark.value.toInt() > 0) {
                    _messageStatue.value = "Marks must be zero or put subject is running"
                    false
                } else {
                    _messageStatue.value = "Success Edit Subject Is AVAILABLE"
                    true
                }
            }
            FAIL -> {
                return if (_mark.value.toInt() >= 50 || _practiceMark.value.toInt() >= 50) {
                    _messageStatue.value = "The marks must be below the pass limit(50)"
                    false
                } else {
                    _messageStatue.value = "Success Edit Subject Is FAIL"
                    true
                }
            }
            SUCCESS -> {
                return if (_mark.value.toInt() < 50) {
                    _messageStatue.value = "The marks must not be below the pass limit(50)"
                    false
                } else {
                    _messageStatue.value = "Success Edit Subject Is SUCCESS"
                    true
                }
            }
            RUNNING -> {
                _messageStatue.value = "Success Edit Subject Is RUNNING"
                return true
            }
            else -> {
                return false
            }
        }
    }

    fun updateNextSubjectAndQualificationFirst(
        subjectWithQualification: SubjectWithQualification,
        statue: SubjectStatue
    ) {
        val updateNextSubjectFirst =
            subjectWithQualification.qualification.copy(subjectStatue = statue)
        Log.d("updateNextSubjectF", "${updateNextSubjectFirst.subjectStatue}")
        viewModelScope.launch { repository.updateQualification(updateNextSubjectFirst) }
    }

    fun updateNextSubjectAndQualificationSecond(
        subjectWithQualification: SubjectWithQualification,
        statue: SubjectStatue
    ) {
        val updateNextSubjectSecond =
            subjectWithQualification.qualification.copy(subjectStatue = statue)
        viewModelScope.launch { repository.updateQualification(updateNextSubjectSecond) }
    }

    fun updateNextSubjectAndQualificationThird(
        subjectWithQualification: SubjectWithQualification,
        statue: SubjectStatue
    ) {
        val updateNextSubjectThird =
            subjectWithQualification.qualification.copy(subjectStatue = statue)
        viewModelScope.launch { repository.updateQualification(updateNextSubjectThird) }
    }

    val getSubjectBySubjectNameFirst = nextSubject.flatMapLatest { nextSubject ->
        repository.getSubjectBySubjectName(nextSubjectName = nextSubject)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    val getSubjectBySubjectNameSecond = nextSubjectSecond.flatMapLatest { nextSubjectTwo ->
        repository.getSubjectBySubjectName(nextSubjectName = nextSubjectTwo)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    val getSubjectBySubjectNameThird = nextSubjectThird.flatMapLatest { nextSubjectThird ->
        repository.getSubjectBySubjectName(nextSubjectName = nextSubjectThird)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    sealed class TaskEvent {
        data class ShowMessageAndBack(val msg: String) : TaskEvent()
        data class ShowErrorMessage(val error: String) : TaskEvent()
    }
}