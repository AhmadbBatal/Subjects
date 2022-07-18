package com.example.subjects.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.subjects.data.dataPreferences.PreferencesManager
import com.example.subjects.data.entities.Qualification
import com.example.subjects.data.entities.Subject
import com.example.subjects.data.entities.SubjectWithQualification
import com.example.subjects.data.repository.SubRepository
import com.example.subjects.util.enums.SubjectStatue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val subRepository: SubRepository,
    private val preferenceManager: PreferencesManager
) : ViewModel() {

    private val taskEventChannel = Channel<TaskEvent>()
    val taskEvent = taskEventChannel.receiveAsFlow()

    private val _emptyOrNot = MutableStateFlow<Boolean>(false)
    val emptyOrNot: StateFlow<Boolean> = _emptyOrNot

    private val _searchQuery = MutableStateFlow("")

    private val preferencesFlow = preferenceManager.preferencesFlow

    fun setQuery(query: String) {
        _searchQuery.value = query
    }

    fun checkIrSubjectsEmptyOrNot(subjectWithQualification: List<SubjectWithQualification>) {
        _emptyOrNot.value = subjectWithQualification.isEmpty()
    }

    private val getSubjects = combine(
        _searchQuery.asStateFlow(),
        preferencesFlow
    ) { searchQuery, filterPreferences ->
        Pair(searchQuery, filterPreferences)
    }.flatMapLatest { (searchQuery, filterPreferences) ->
        subRepository.getAllSubjects(searchQuery, filterPreferences.sortStoreOrder)
    }

    //    .stateIn(viewModelScope, SharingStarted.Eagerly,null)
    val get = getSubjects.asLiveData()

    fun onClickEditFragment(subject: SubjectWithQualification) = viewModelScope.launch {
        taskEventChannel.send(
            TaskEvent.NavigateToEditTaskScreen(
                subject.subject,
                subject.qualification
            )
        )
    }

    fun onClickPreviewFragment(subject: SubjectWithQualification) = viewModelScope.launch {
        taskEventChannel.send(
            TaskEvent.NavigateToPreviewTaskScreen(
                subject.subject,
                subject.qualification
            )
        )
    }

    fun onSortSubjectsUpdated(subSort: SubjectStatue) = viewModelScope.launch {
        preferenceManager.updateSortOrder(subSort)
    }

    sealed class TaskEvent {

        data class NavigateToEditTaskScreen(
            val subject: Subject,
            val qualification: Qualification
        ) : TaskEvent()

        data class NavigateToPreviewTaskScreen(
            val subject: Subject,
            val qualification: Qualification
        ) : TaskEvent()
    }
}















