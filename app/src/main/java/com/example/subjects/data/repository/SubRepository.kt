package com.example.subjects.data.repository

import com.example.subjects.data.dao.SubjectDao
import com.example.subjects.data.entities.*
import com.example.subjects.data.models.SubIcons
import com.example.subjects.util.enums.SubjectStatue
import com.example.subjects.util.subIcons
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubRepository @Inject constructor(
    private val subDao: SubjectDao
) {

    fun getAllYears(): Flow<List<Years>> =
        subDao.getAllSubjectYears()

    fun getAllSubjectByYearsId(yearsId: Int) : Flow<List<SubjectWithQualification>> =
        subDao.getAllSubjectStudentByYears(yearsId)

    fun getSubjectBySubjectName(nextSubjectName:String) :Flow<SubjectWithQualification> =
    subDao.getSubjectBySubjectName(nextSubjectName)

    fun getAllSubjects(query: String, sortedBY: SubjectStatue) :Flow<List<SubjectWithQualification>> =
        subDao.getSubjects(query,sortedBY)

    fun getStudent(): Flow<Student> =
        subDao.getStudent()

    fun getIconsForEditFragment() : List<SubIcons>{
        return subIcons
    }

    suspend fun updateSubject(subject:Subject){
        subDao.updateSubject(subject)
    }

    suspend fun updateQualification(qualification: Qualification){
        subDao.updateQualification(qualification)
    }

}