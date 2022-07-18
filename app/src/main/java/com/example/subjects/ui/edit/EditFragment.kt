package com.example.subjects.ui.edit

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.subjects.R
import com.example.subjects.adapter.adapters.SubIconsAdapter
import com.example.subjects.data.entities.Qualification
import com.example.subjects.data.entities.Subject
import com.example.subjects.data.models.SubIcons
import com.example.subjects.databinding.FragmentEditBinding
import com.example.subjects.ui.viewmodel.EditViewModel
import com.example.subjects.util.enums.SubjectStatue
import com.example.subjects.util.enums.SubjectStatue.*
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditFragment : Fragment(R.layout.fragment_edit), SubIconsAdapter.OnClickIcon {

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EditViewModel by viewModels()
    private lateinit var iconAdapter: SubIconsAdapter
    private val args by navArgs<EditFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEditBinding.bind(view)

        iconAdapter = SubIconsAdapter(this)
        iconAdapter.submitList(viewModel.getIcons)

        binding.apply {

            recImgGridDrawable.apply {
                layoutManager = StaggeredGridLayoutManager(
                    4, StaggeredGridLayoutManager.VERTICAL
                )
                setHasFixedSize(true)
                adapter = iconAdapter
            }

            editTeacherEditName.addTextChangedListener {
                viewModel.setTeacherName(it.toString())
            }

            editMarkEdit.addTextChangedListener {
                viewModel.setMark(it.toString())
            }

            editPracticalMarkEdit.addTextChangedListener {
                viewModel.setPracticeMark(it.toString())
            }

            cardBackEdit.setOnClickListener {
                findNavController().popBackStack()
            }

            whenInitialize()
            onChooseChip()

            cardAddEdit.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        clickSave()
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.editSubEventChannel.collectLatest { event ->
                    when (event) {

                        is EditViewModel.TaskEvent.ShowErrorMessage -> {
                            Snackbar.make(requireView(), event.error, Snackbar.LENGTH_LONG).show()
                        }

                        is EditViewModel.TaskEvent.ShowMessageAndBack -> {
                            if (event.msg == "Putting Subject Success is success") {
                                updateNextFirst(AVAILABLE)
                                updateNextSecond(AVAILABLE)
                                updateNextThird(AVAILABLE)
                                delay(150)
                           }
//                            else {
//                                updateNextFirst(CLOSED)
//                                updateNextSecond(CLOSED)
//                                updateNextThird(CLOSED)
//                                delay(150)
//                            }
                            binding.editTeacherEditName.clearFocus()
                            binding.editMarkEdit.clearFocus()
                            binding.editPracticalMarkEdit.clearFocus()
                            Snackbar.make(requireView(), event.msg, Snackbar.LENGTH_LONG).show()
                            findNavController().popBackStack()
                        }
                    }
                }
            }
        }
    }

    private suspend fun clickSave() {
        val argsSubject = args.editArguments.apply {
            Subject(
                subjectNameId,
                yearId,
                number,
                subjectName,
                qualification,
                subjectColor,
                teacherName,
                practicalMarks,
                finalMark,
                subjectImage,
                collage,
                subHours,
                subYears,
            )
        }
        val argsQualification = args.sendqualification.apply {
            Qualification(
                subjectNameId,
                qualificationNameId,
                nextSubjectId,
                nextSubjectTow,
                nextSubjectThree,
                isHasQualification,
                subjectStatue
            )
        }
        viewModel.onSaveClick(argsSubject, argsQualification)
    }

    // Initialize
    @SuppressLint("ResourceType")
    fun whenInitialize() {
        binding.apply {

            txtSubjectEditName.text = args.editArguments.subjectName
            editTeacherEditName.setText(args.editArguments.teacherName)
            editMarkEdit.setText(args.editArguments.finalMark)
            editPracticalMarkEdit.setText(args.editArguments.practicalMarks)

            args.editArguments.finalMark?.let { viewModel.setMark(it)}
            args.editArguments.practicalMarks?.let { viewModel.setPracticeMark(it)}
            args.editArguments.teacherName?.let { viewModel.setTeacherName(it)}
            args.sendqualification.subjectStatue?.let { viewModel.setSubStatue(it) }
            args.editArguments.subjectImage?.let { viewModel.setIcon(it) }

            viewModel.setNextSubjectFirst(args.sendqualification.nextSubjectId.toString())
            viewModel.setNextSubjectSecond(args.sendqualification.nextSubjectTow.toString())
            viewModel.setNextSubjectThird(args.sendqualification.nextSubjectThree.toString())

            when (args.sendqualification.subjectStatue) {
                AVAILABLE -> {
                    chipAvailable.isChecked = true
                }
                RUNNING -> {
                    chipRunning.isChecked = true
                }
                FAIL -> {
                    chipFail.isChecked = true
                }
                SUCCESS -> {
                    chipSuccess.isChecked = true
                }
                else -> {
                    Log.d("ExceptionFromEdit", "I Don't Know")
                }
            }
        }
    }

    private fun updateNextFirst(subjectStatue: SubjectStatue) {
        if (args.sendqualification.nextSubjectId.toString() != "non") {
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED)
                {
                    viewModel.getSubjectBySubjectNameFirst.collect {
                        it?.let { it1 ->
                            viewModel.updateNextSubjectAndQualificationFirst(it1, subjectStatue)
                            Log.d("updateNextSubjectAnd", "${it1.qualification.subjectStatue}")
                        }
                    }
                }
            }
        }
    }

    private fun updateNextSecond(subjectStatue: SubjectStatue) {
        if (args.sendqualification.nextSubjectTow.toString() != "non") {
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED)
                {
                    viewModel.getSubjectBySubjectNameSecond.collect {
                        it?.let { it2 ->
                            viewModel.updateNextSubjectAndQualificationSecond(it2, subjectStatue)
                            Log.d("updateNextSecond2", "$it2")
                        }
                    }
                }
            }
        }
    }

    private fun updateNextThird(subjectStatue: SubjectStatue) {
        if (args.sendqualification.nextSubjectThree.toString() != "non") {
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED)
                {
                    viewModel.getSubjectBySubjectNameThird.collect {
                        it?.let { it3 ->
                            viewModel.updateNextSubjectAndQualificationThird(it3, subjectStatue)
                        }
                    }
                }
            }
        }
    }

    // When Click Chip
    private fun onChooseChip() {
        binding.apply {
            chipSuccess.setOnCheckedChangeListener { _, b ->
                if (b) {
                    viewModel.setSubStatue(SUCCESS)
                }
            }
            chipAvailable.setOnCheckedChangeListener { _, b ->
                if (b) {
                    viewModel.setSubStatue(AVAILABLE)
                }
            }
            chipFail.setOnCheckedChangeListener { _, b ->
                if (b) {
                    viewModel.setSubStatue(FAIL)
                }
            }
            chipRunning.setOnCheckedChangeListener { _, b ->
                if (b) {
                    viewModel.setSubStatue(RUNNING)
                }
            }
        }
    }

    override fun onClickIcons(subIcon: SubIcons) {
        subIcon.icon?.let { viewModel.setIcon(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}