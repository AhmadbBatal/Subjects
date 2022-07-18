package com.example.subjects.ui.preview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.subjects.R
import com.example.subjects.databinding.FragmentPreviewBinding
import com.example.subjects.util.enums.SubjectStatue
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreviewFragment : Fragment(R.layout.fragment_preview) {

    private var _binding: FragmentPreviewBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<PreviewFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPreviewBinding.bind(view)

        whenInitialize()
    }

    private fun whenInitialize() {
        binding.apply {
            markProgressBar.apply {
                progress = (args.previewArgsActionSub.finalMark?.toFloat() ?: 0.0) as Float

                setProgressWithAnimation(progress,1000)
                progressMax = 100f

                onProgressChangeListener = { progresses->
                    txtNumberOfCircleProgress.text = progresses.toString()
                }
            }

            practicalMarkProgressBar.apply {
                progress = (args.previewArgsActionSub.practicalMarks?.toFloat() ?: 0.0) as Float

                setProgressWithAnimation(progress,1000)
                progressMax = 100f

                onProgressChangeListener = { progresses->
                    txtNumberOfCirclePracticalProgress.text = progresses.toString()
                }
            }

            txtSubjectName.text = args.previewArgsActionSub.subjectName
            txtPreviewTeacherName.text = args.previewArgsActionSub.teacherName
            txtPreviewQualificationName.text = args.previewArgsActionSub.qualification
            txtPreviewCollageName.text = args.previewArgsActionSub.collage
            txtPreviewHourName.text = args.previewArgsActionSub.subHours + " Hour "
            txtPreviewYearName.text = args.previewArgsActionSub.subYears + " Year "

            when (args.previewArgsActionQualifiaction.subjectStatue) {
                SubjectStatue.AVAILABLE -> {
                    imgStatusSubject.setImageResource(R.drawable.green_fill_circle)
                }
                SubjectStatue.RUNNING -> {
                    imgStatusSubject.setImageResource(R.drawable.orange_fill_cicle)
                }
                SubjectStatue.FAIL -> {
                    imgStatusSubject.setImageResource(R.drawable.fail_retry)
                }
                SubjectStatue.SUCCESS -> {
                    imgStatusSubject.setImageResource(R.drawable.success_correct)
                }
                else -> {
                    Log.d("ExceptionFromEdit", "I Don't Know")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}