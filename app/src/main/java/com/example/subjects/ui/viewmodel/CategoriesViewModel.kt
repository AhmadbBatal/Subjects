package com.example.subjects.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.subjects.data.repository.SubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val subRepository: SubRepository
) : ViewModel() {

    private val _yearsIdForSubject = MutableStateFlow(1)
    private val yearsIdForSubject : StateFlow<Int> = _yearsIdForSubject

    val getYears = subRepository
        .getAllYears()
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            null
        )

    val getStudent = subRepository
        .getStudent()
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            null
        )

    val getAllSubjectsByYear = yearsIdForSubject.flatMapLatest{ id ->
        subRepository.getAllSubjectByYearsId(yearsId = id)
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    fun getSubjectOnClickYear(yearId :Int)
    {
        viewModelScope.launch {
            _yearsIdForSubject.value = yearId
        }
    }
}