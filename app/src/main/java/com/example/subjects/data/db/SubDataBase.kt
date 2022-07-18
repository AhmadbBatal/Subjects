package com.example.subjects.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.subjects.R
import com.example.subjects.data.dao.SubjectDao
import com.example.subjects.data.entities.*
import com.example.subjects.util.deleteSpaces
import com.example.subjects.util.enums.SubjectStatue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(
    entities = [
        Student::class,
        Subject::class,
        Years::class,
        Qualification::class
    ],
    version = 2
)
@TypeConverters(Converter::class)
abstract class SubDataBase : RoomDatabase() {

    abstract fun subjectDao(): SubjectDao

    class Callback @Inject constructor(
        private val database: Provider<SubDataBase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dap = database.get().subjectDao()

            applicationScope.launch {

                dap.insertYear(
                    Years(
                        1,
                        yearName = R.string.first_year,
                        icon = R.drawable.sumphor
                    )
                )

                dap.insertYear(
                    Years(
                        2,
                        yearName = R.string.second_year,
                        icon = R.drawable.second_year
                    )
                )
                dap.insertYear(
                    Years(
                        3,
                        yearName = R.string.third_year,
                        icon = R.drawable.third_year
                    )
                )
//                dap.insertYear(
//                    Years(
//                        4,
//                        yearName = R.string.fourth_year,
//                        icon = R.drawable.fife_year
//                    )
//                )
//                dap.insertYear(
//                    Years(
//                        5,
//                        yearName = R.string.fifth_year,
//                        icon = R.drawable.fourth_year
//                    )
//                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("لغة إنكليزية 1"),
                        yearId = 1,
                        number = 1,
                        subjectName = "لغة إنكليزية 1",
                        qualification = "Non",
                        subjectColor = "Red",
                        teacherName = "عبد السلام قلعجي",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.mind,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "1",
                    )
                )
                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("لغة إنكليزية 1"),
                        qualificationNameId = deleteSpaces("Non"),
                        nextSubjectId = deleteSpaces("لغة إنكليزية 2"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = false,
                        subjectStatue = SubjectStatue.AVAILABLE
                        )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("عربي"),
                        yearId = 1,
                        number = 2,
                        subjectName = "عربي",
                        qualification = "Non",
                        subjectColor = "Red",
                        teacherName = "محمد المحمد",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.zero_one,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "1",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("عربي"),
                        qualificationNameId = deleteSpaces("Non"),
                        nextSubjectId = deleteSpaces("Non"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = false,
                        subjectStatue = SubjectStatue.AVAILABLE
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("اسس تقانة المعلومات"),
                        yearId = 1,
                        number = 3,
                        subjectName = "اسس تقانة المعلومات",
                        qualification = "Non",
                        subjectColor = "Red",
                        teacherName = "عبد الكريم المحمد",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.one_zero,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "1",
//                        subjectStatue = SubjectStatue.AVAILABLE
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("اسس تقانة المعلومات"),
                        qualificationNameId = deleteSpaces("Non"),
                        nextSubjectId = deleteSpaces("مدخل الى لغة البرمجة و الخوارزميات"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = false,
                        subjectStatue = SubjectStatue.AVAILABLE
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("فيزياء عامة"),
                        yearId = 1,
                        number = 4,
                        subjectName = "فيزياء عامة",
                        qualification = "Non",
                        subjectColor = "Red",
                        teacherName = "سامر المصطفى",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.zero_zero,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "1",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("فيزياء عامة"),
                        qualificationNameId = deleteSpaces("Non"),
                        nextSubjectId = deleteSpaces("فيزياء حديثة"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = false,
                        subjectStatue = SubjectStatue.AVAILABLE
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("رياضيات 1"),
                        yearId = 1,
                        number = 5,
                        subjectName = "رياضيات 1",
                        qualification = "Non",
                        subjectColor = "Red",
                        teacherName = "صبحي سامر",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.hacker,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "1",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("رياضيات 1"),
                        qualificationNameId = deleteSpaces("Non"),
                            nextSubjectId = deleteSpaces("رياضيات 2"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = false,
                        subjectStatue = SubjectStatue.AVAILABLE
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("اسس الهندسة الكهربائية 1"),
                        yearId = 1,
                        number = 6,
                        subjectName = "اسس الهندسة الكهربائية 1",
                        qualification = "Non",
                        subjectColor = "Red",
                        teacherName = "Rami And Jolit",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.zero_zero,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "1",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("اسس الهندسة الكهربائية 1"),
                        qualificationNameId = deleteSpaces("Non"),
                        nextSubjectId = deleteSpaces("اسس الهندسة الكهربائية 2"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = false,
                        subjectStatue = SubjectStatue.AVAILABLE
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("لغة إنكليزية 2"),
                        yearId = 1,
                        number = 7,
                        subjectName = "لغة إنكليزية 2",
                        qualification = "لغة إنكليزية 1",
                        subjectColor = "Red",
                        teacherName = "SpiderMan",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.mind,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "1",
//                        subjectStatue = SubjectStatue.CLOSED
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("لغة إنكليزية 2"),
                        qualificationNameId = deleteSpaces("لغة إنكليزية 1"),
                        nextSubjectId = deleteSpaces("لغة تخصصية 1"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.CLOSED
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("مدخل الى البرمجة و الخوارزميات"),
                        yearId = 1,
                        number = 8,
                        subjectName = "مدخل الى البرمجة و الخوارزميات",
                        qualification = "اسس تقانة المعلومات",
                        subjectColor = "Red",
                        teacherName = "عبد الكريم المحمد",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.mind,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "1",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("مدخل الى البرمجة و الخوارزميات"),
                        qualificationNameId = deleteSpaces("اسس تقانة المعلومات"),
                        nextSubjectId = deleteSpaces("برمجة 1"),
                        nextSubjectTow = deleteSpaces("خوارزميات"),
                        nextSubjectThree = deleteSpaces("شبكات حاسوبية 1"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.CLOSED
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("فيزياء حديثة"),
                        yearId = 1,
                        number = 9,
                        subjectName = "فيزياء حديثة",
                        qualification = "فيزياء عامة",
                        subjectColor = "Red",
                        teacherName = "محمد عبد السلام",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.one_zero,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "1",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("فيزياء حديثة"),
                        qualificationNameId = deleteSpaces("فيزياء عامة"),
                        nextSubjectId = deleteSpaces("Non"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.CLOSED
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("رياضيات 2"),
                        yearId = 1,
                        number = 10,
                        subjectName = "رياضيات 2",
                        qualification = "رياضيات 1",
                        subjectColor = "Red",
                        teacherName = "محمود جليلاتي",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.one_zero,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "1",
                    )
                )
                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("رياضيات 2"),
                        qualificationNameId = deleteSpaces("رياضيات 1"),
                        nextSubjectId = deleteSpaces("رياضيات 3"),
                        nextSubjectTow = deleteSpaces("انظمة خطية"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.CLOSED
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("اسس الهندسة الكهربائية 2"),
                        yearId = 1,
                        number = 11,
                        subjectName = "اسس الهندسة الكهربائية 2",
                        qualification = "اسس الهندسة الكهربائية 1",
                        subjectColor = "Red",
                        teacherName = "محمد مارتيني",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.one_zero,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "1",
                    )
                )
                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("اسس الهندسة الكهربائية 2"),
                        qualificationNameId = deleteSpaces("اسس الهندسة الكهربائية 1"),
                        nextSubjectId = deleteSpaces("هندسة إلكترونية 1"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.CLOSED
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("ثقافة عربية"),
                        yearId = 1,
                        number = 12,
                        subjectName = "ثقافة عربية",
                        qualification = "Non",
                        subjectColor = "Red",
                        teacherName = "زمرد راحلة",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.one_zero,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "1",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("ثقافة عربية"),
                        qualificationNameId = deleteSpaces("Non"),
                        nextSubjectId = deleteSpaces("Non"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = false,
                        subjectStatue = SubjectStatue.AVAILABLE
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("لغة تخصصية 1"),
                        yearId = 2,
                        number = 13,
                        subjectName = "لغة تخصصية 1",
                        qualification = "لغة إنكليزية 2",
                        subjectColor = "Red",
                        teacherName = "عبد السلام قلعجي",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.one_zero,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "2",
//                        subjectStatue = SubjectStatue.CLOSED
                    )
                )
                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("لغة تخصصية 1"),
                        qualificationNameId = deleteSpaces("لغة إنكليزية 2"),
                        nextSubjectId = deleteSpaces("لغة تخصصية 2"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.CLOSED
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("هندسة إلكترونية 1"),
                        yearId = 2,
                        number = 14,
                        subjectName = "هندسة إلكترونية 1",
                        qualification = "اسس الهندسة الكهربائية 2",
                        subjectColor = "Red",
                        teacherName = "محمد مارتيني",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.one_zero,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "2",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("هندسة إلكترونية 1"),
                        qualificationNameId = deleteSpaces("اسس الهندسة الكهربائية 2"),
                        nextSubjectId = deleteSpaces("إلكترونيات رقمية"),
                        nextSubjectTow = deleteSpaces("هندسة إلكترونية 2"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.CLOSED
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("برمجة 1"),
                        yearId = 2,
                        number = 15,
                        subjectName = "برمجة 1",
                        qualification = "مدخل الى البرمجة و الخوارزميات",
                        subjectColor = "Red",
                        teacherName = "محمد الخلاف",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.one_zero,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "2",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("برمجة 1"),
                        qualificationNameId = deleteSpaces("مدخل الى البرمجة و الخوارزميات"),
                        nextSubjectId = deleteSpaces("برمجة 2"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.CLOSED
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("رياضيات 3"),
                        yearId = 2,
                        number = 16,
                        subjectName = "رياضيات 3",
                        qualification = "رياضيات 2",
                        subjectColor = "Red",
                        teacherName = "محمد الخلف",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.one_zero,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "2",
                    )
                )
                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("رياضيات 3"),
                        qualificationNameId = deleteSpaces("رياضيات 2"),
                        nextSubjectId = deleteSpaces("Non"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.CLOSED
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("انظمة خطية"),
                        yearId = 2,
                        number = 17,
                        subjectName = "انظمة خطية",
                        qualification = "رياضيات 2",
                        subjectColor = "Red",
                        teacherName = "محمد الحمد",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.one_zero,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "2",
                    )
                )
                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("انظمة خطية"),
                        qualificationNameId = deleteSpaces("رياضيات 2"),
                        nextSubjectId = deleteSpaces("انظمة متقطعة"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.CLOSED
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("اساسيات الادارة 1"),
                        yearId = 2,
                        number = 18,
                        subjectName = "اساسيات الادارة 1",
                        qualification = "Non",
                        subjectColor = "Red",
                        teacherName = "رامز القلاع",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.zero_zero,
                        collage = "إدارة",
                        subHours = "3",
                        subYears = "2",
                    )
                )
                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("اساسيات الادارة 1"),
                        qualificationNameId = deleteSpaces("Non"),
                        nextSubjectId = deleteSpaces("Non"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = false,
                        subjectStatue = SubjectStatue.AVAILABLE
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("لغة تخصصية 2"),
                        yearId = 2,
                        number = 19,
                        subjectName = "لغة تخصصية 2",
                        qualification = "لغة تخصصية 1",
                        subjectColor = "Red",
                        teacherName = "محمد عبد السلام",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.zero_one,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "2",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("لغة تخصصية 2"),
                        qualificationNameId = deleteSpaces("بغة تخصصية 1"),
                        nextSubjectId = deleteSpaces("Non"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.CLOSED
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("الكترونيات رقمية"),
                        yearId = 2,
                        number = 20,
                        subjectName = "الكترونيات رقمية",
                        qualification = "هندسة إلكترونية 1",
                        subjectColor = "Red",
                        teacherName = "محمد مارتيني",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.zero_one,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "2",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("الكترونيات رقمية"),
                        qualificationNameId = deleteSpaces("هندسة إلكترونية 1"),
                        nextSubjectId = deleteSpaces("دارات رقمية"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.CLOSED
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("برمجة 2"),
                        yearId = 2,
                        number = 21,
                        subjectName = "برمجة 2",
                        qualification = "برمجة 1",
                        subjectColor = "Red",
                        teacherName = "عبد الكريم المحمد",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.hacker,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "2",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("برمجة 2"),
                        qualificationNameId = deleteSpaces("برمجة 1"),
                        nextSubjectId = deleteSpaces("تحليل و تصميم النظم"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.CLOSED
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("انظمة متقطعة"),
                        yearId = 2,
                        number = 22,
                        subjectName = "انظمة متقطعة",
                        qualification = "انظمة خطية",
                        subjectColor = "Red",
                        teacherName = "عماد الروح",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.mind,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "2",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("انظمة متقطعة"),
                        qualificationNameId = deleteSpaces("انظمة خطية"),
                        nextSubjectId = deleteSpaces("Non"), // Fourth
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.CLOSED
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("إحصاء و احتمالات للمهندسين"),
                        yearId = 2,
                        number = 23,
                        subjectName = "إحصاء و احتمالات للمهندسين",
                        qualification = "Non",
                        subjectColor = "Red",
                        teacherName = "جاسم المحمد",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.zero_one,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "2",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("إحصاء و احتمالات للمهندسين"),
                        qualificationNameId = deleteSpaces("Non"),
                        nextSubjectId = deleteSpaces("نمذجة و محاكاة"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.AVAILABLE
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("مهارات التواصل للمهندسين"),
                        yearId = 2,
                        number = 24,
                        subjectName = "مهارات التواصل للمهندسين",
                        qualification = "Non",
                        subjectColor = "Red",
                        teacherName = "جاسم المحمد",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.zero_one,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "2",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("مهارات التواصل للمهندسين"),
                        qualificationNameId = deleteSpaces("Non"),
                        nextSubjectId = deleteSpaces("Non"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.AVAILABLE
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("مبادء مالية"),
                        yearId = 2,
                        number = 25,
                        subjectName = "مبادء مالية",
                        qualification = "Non",
                        subjectColor = "Red",
                        teacherName = "جاسم المحمد",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.hacker,
                        collage = "إدارة",
                        subHours = "3",
                        subYears = "2",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("مبادء مالية"),
                        qualificationNameId = deleteSpaces("Non"),
                        nextSubjectId = deleteSpaces("Non"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = false,
                        subjectStatue = SubjectStatue.AVAILABLE
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("خوارزميات"),
                        yearId = 3,
                        number = 26,
                        subjectName = "خوارزميات",
                        qualification = "مدخل الى البرمجة و الخوارزميات",
                        subjectColor = "Red",
                        teacherName = "Mohmad Al Mohmad",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.one_zero,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "3",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("خوارزميات"),
                        qualificationNameId = deleteSpaces("مدخل الى البرمجة و الخوارزميات"),
                        nextSubjectId = deleteSpaces("ذكاء صنعي"),
                        nextSubjectTow = deleteSpaces("Non"), //Forth
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.CLOSED
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("نمذجة و محاكاة"),
                        yearId = 3,
                        number = 27,
                        subjectName = "نمذجة و محاكاة",
                        qualification = "إحصاء و احتمالات للمهندسين",
                        subjectColor = "Red",
                        teacherName = "محمد ناصيف",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.one_zero,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "3",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("نمذجة و محاكاة"),
                        qualificationNameId = deleteSpaces("إحصاء و احتمالات للمهندسين"),
                        nextSubjectId = deleteSpaces("Non"), //Fifth
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.CLOSED
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("دارات رقمية"),
                        yearId = 3,
                        number = 28,
                        subjectName = "دارات رقمية",
                        qualification = "الكترونيات رقمية",
                        subjectColor = "Red",
                        teacherName = "محمد مارتيني",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.one_zero,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "3",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("دارات رقمية"),
                        qualificationNameId = deleteSpaces("الكترونيات رقمية"),
                        nextSubjectId = deleteSpaces("بنيان حاسوب"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.CLOSED
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("هندسة إلكترونية 2"),
                        yearId = 3,
                        number = 29,
                        subjectName = "هندسة إلكترونية 2",
                        qualification = "هندسة إلكترونية 1",
                        subjectColor = "Red",
                        teacherName = "محمد مارتيني",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.one_zero,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "3",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("هندسة إلكترونية 2"),
                        qualificationNameId = deleteSpaces("هندسة إلكترونية 1"),
                        nextSubjectId = deleteSpaces("دارات إلكترونية"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.CLOSED
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("تحليل و تصميم نظم"),
                        yearId = 3,
                        number = 30,
                        subjectName = "تحليل و تصميم نظم",
                        qualification = "برمجة 2",
                        subjectColor = "Red",
                        teacherName = "محمد ناصيف",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.one_zero,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "3",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("تحليل و تصميم نظم"),
                        qualificationNameId = deleteSpaces("برمجة 2"),
                        nextSubjectId = deleteSpaces("Non"), // Forth
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.CLOSED
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("نظم المعلومات الجغرافية"),
                        yearId = 3,
                        number = 31,
                        subjectName = "نظم المعلومات الجغرافية",
                        qualification = "Non",
                        subjectColor = "Red",
                        teacherName = "محمد جاسم",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.one_zero,
                        collage = "معلوماتية",
                        subHours = "2",
                        subYears = "3",
//                        subjectStatue = SubjectStatue.AVAILABLE
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("نظم المعلومات الجغرافية"),
                        qualificationNameId = deleteSpaces("Non"),
                        nextSubjectId = deleteSpaces("Non"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = false,
                        subjectStatue = SubjectStatue.AVAILABLE
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("مبادء التسويق"),
                        yearId = 3,
                        number = 32,
                        subjectName = "مبادء التسويق",
                        qualification = "Non",
                        subjectColor = "Red",
                        teacherName = "محمد الجاسم",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.one_zero,
                        collage = "إدارة اعمال",
                        subHours = "2",
                        subYears = "3",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("مبادء التسويق"),
                        qualificationNameId = deleteSpaces("Non"),
                        nextSubjectId = deleteSpaces("Non"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = false,
                        subjectStatue = SubjectStatue.AVAILABLE
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("قواعد البيانات"),
                        yearId = 3,
                        number = 33,
                        subjectName = "قواعد البيانات",
                        qualification = "تحليل و تصميم النظم",
                        subjectColor = "Red",
                        teacherName = "عبد الكريم المحمد",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.one_zero,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "3",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("قواعد البيانات"),
                        qualificationNameId = deleteSpaces("تحليل و تصميم النظم"),
                        nextSubjectId = deleteSpaces("Non"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.CLOSED
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("بنيان حاسوب"),
                        yearId = 3,
                        number = 34,
                        subjectName = "بنيان حاسوب",
                        qualification = "دارات رقمية",
                        subjectColor = "Red",
                        teacherName = "محمد مارتيني",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.one_zero,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "3",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("بنيان حاسوب"),
                        qualificationNameId = deleteSpaces("دارات رقمية"),
                        nextSubjectId = deleteSpaces("Non"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.CLOSED
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("دارات إلكترونية"),
                        yearId = 3,
                        number = 35,
                        subjectName = "دارات إلكترونية",
                        qualification = "هندسة إلكترونية 2",
                        subjectColor = "Red",
                        teacherName = "محمد مارتيني",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.one_zero,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "3",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("دارات إلكترونية"),
                        qualificationNameId = deleteSpaces("هندسة إلكترونية 2"),
                        nextSubjectId = deleteSpaces("Non"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.CLOSED
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("نظم متعددة الوسائط"),
                        yearId = 3,
                        number = 36,
                        subjectName = "نظم متعددة الوسائط",
                        qualification = "Non",
                        subjectColor = "Red",
                        teacherName = "Rami And Jolit",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.one_zero,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "3",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("نظم متعددة الوسائط"),
                        qualificationNameId = deleteSpaces("Non"),
                        nextSubjectId = deleteSpaces("Non"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = false,
                        subjectStatue = SubjectStatue.AVAILABLE
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("ذكاء صنعي"),
                        yearId = 3,
                        number = 37,
                        subjectName = "ذكاء صنعي",
                        qualification = "خوارزميات",
                        subjectColor = "Red",
                        teacherName = "عبد الكريم المحمد",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.one_zero,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "3",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("ذكاء صنعي"),
                        qualificationNameId = deleteSpaces("خوارزميات"),
                        nextSubjectId = deleteSpaces("Non"),
                        nextSubjectTow = deleteSpaces("Non"),
                        nextSubjectThree = deleteSpaces("Non"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.CLOSED
                    )
                )

                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("شبكات حاسوبية 1"),
                        yearId = 3,
                        number = 38,
                        subjectName = "شبكات حاسوبية 1",
                        qualification = "مدخل الى البرمجة و الخوارزميات",
                        subjectColor = "Red",
                        teacherName = "ياسر الفواز",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.one_zero,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "3",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("شبكات حاسوبية 1"),
                        qualificationNameId = deleteSpaces("مدخل الى البرمجة و الخوارزميات"),
                        nextSubjectId = deleteSpaces("no_qualification"),
                        nextSubjectTow = deleteSpaces("no_qualification"),
                        nextSubjectThree = deleteSpaces("no_qualification"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.CLOSED
                    )
                )


                dap.insertSubject(
                    Subject(
                        subjectNameId = deleteSpaces("شبكات حاسوبية 1"),
                        yearId = 4,
                        number = 39,
                        subjectName = "شبكات حاسوبية 1",
                        qualification = "مدخل الى البرمجة و الخوارزميات",
                        subjectColor = "Red",
                        teacherName = "ياسر الفواز",
                        practicalMarks = "0",
                        finalMark = "0",
                        subjectImage = R.drawable.one_zero,
                        collage = "معلوماتية",
                        subHours = "3",
                        subYears = "3",
                    )
                )

                dap.insertQualification(
                    Qualification(
                        subjectNameId = deleteSpaces("شبكات حاسوبية 1"),
                        qualificationNameId = deleteSpaces("مدخل الى البرمجة و الخوارزميات"),
                        nextSubjectId = deleteSpaces("no_qualification"),
                        nextSubjectTow = deleteSpaces("no_qualification"),
                        nextSubjectThree = deleteSpaces("no_qualification"),
                        isHasQualification = true,
                        subjectStatue = SubjectStatue.CLOSED
                    )
                )





















            }
        }
    }
}













