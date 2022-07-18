package com.example.subjects.data.dao

import androidx.room.*
import com.example.subjects.data.entities.*
import com.example.subjects.util.enums.SubjectStatue
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: Subject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertYear(years: Years)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQualification(qualification: Qualification)

    @Update
    suspend fun updateSubject(subject: Subject)

    @Update
    suspend fun updateQualification(qualification: Qualification)

    @Transaction
    @Query("SELECT * FROM subject WHERE yearId =:yearId")
    fun getAllSubjectStudentByYears(yearId: Int): Flow<List<SubjectWithQualification>>

    @Query(
        "SELECT * FROM subject INNER JOIN qualification_table ON subject.subjectNameId = qualification_table.subjectNameId " +
                "WHERE subject.subjectName LIKE '%' || :searchQuery || '%' ORDER BY subject.subYears"
    )
    fun getAllSubjects(searchQuery: String): Flow<List<SubjectWithQualification>>

    @Query(
        "SELECT * FROM subject INNER JOIN qualification_table ON subject.subjectNameId = qualification_table.subjectNameId " +
                "WHERE subject.subjectNameId = :nextSubjectName"
    )
    fun getSubjectBySubjectName(nextSubjectName: String): Flow<SubjectWithQualification>

    @Query(
        "SELECT * FROM subject INNER JOIN qualification_table ON subject.subjectNameId = qualification_table.subjectNameId " +
                "WHERE subject.subjectName LIKE '%' || :searchQuery || '%' AND qualification_table.subjectStatue= :statue ORDER BY " +
                "subject.subYears"
    )
    fun subjectSortedBySubjectStatue(
        searchQuery: String,
        statue: SubjectStatue
    ): Flow<List<SubjectWithQualification>>

    fun getSubjects(query: String, sortedBY: SubjectStatue): Flow<List<SubjectWithQualification>> =
        when (sortedBY) {
            SubjectStatue.ALL -> {
                getAllSubjects(query)
            }
            else -> {
                subjectSortedBySubjectStatue(query, sortedBY)
            }
        }

    @Query("SELECT * FROM years")
    fun getAllSubjectYears(): Flow<List<Years>>

    @Query("SELECT * FROM student")
    fun getStudent(): Flow<Student>

}