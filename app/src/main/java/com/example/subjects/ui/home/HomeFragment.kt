package com.example.subjects.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.subjects.R
import com.example.subjects.adapter.adapters.SubjectAdapter
import com.example.subjects.databinding.FragmentHomeBinding
import com.example.subjects.ui.viewmodel.HomeViewModel
import com.example.subjects.util.enums.SubjectStatue
import com.example.subjects.util.onQueryTextChanged
import com.google.android.material.button.MaterialButton
import com.skydoves.balloon.*
import com.skydoves.balloon.overlay.BalloonOverlayAnimation
import com.skydoves.balloon.overlay.BalloonOverlayCircle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var subjectAdapter: SubjectAdapter

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        subjectAdapter = SubjectAdapter(
            onSubjectClick = {},
            onBtnEditSubjectClick = {
                viewModel.onClickEditFragment(it)
            },
            onBtnViewSubjectClick = {
                viewModel.onClickPreviewFragment(it)
            }
        )

        getAllSubjects2()

        binding.apply {
            recyclerHomeList.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = subjectAdapter
            }

            searchHomeItem.onQueryTextChanged {
                viewModel.setQuery(it)
            }
        }

        val balloon = Balloon.Builder(requireContext())
            .setLayout(R.layout.subject_statue_dialog)
            .setArrowSize(0)
            .setIsVisibleOverlay(true)
            .setBalloonOverlayAnimation(BalloonOverlayAnimation.FADE)
            .setOverlayColorResource(R.color.d_black)
            .setArrowOrientation(ArrowOrientation.START)
            .setOverlayShape(BalloonOverlayCircle(radius = 0f))
            .setHeight(BalloonSizeSpec.WRAP)
            .setWidth(BalloonSizeSpec.WRAP)
            .setWidthRatio(0.57f)
            .setCornerRadius(15f)
            .setBalloonAnimation(BalloonAnimation.CIRCULAR)
            .setLifecycleOwner(viewLifecycleOwner)
            .build()

        binding.imgFilter.setOnClickListener {
            balloon.showAtCenter(binding.rootingConstraintLayout)

            val allStatueButton: MaterialButton =
                balloon.getContentView().findViewById(R.id.btn_statue_all)

            val availableStatueButton: MaterialButton =
                balloon.getContentView().findViewById(R.id.btn_statue_available)

            val runningStatueButton: MaterialButton =
                balloon.getContentView().findViewById(R.id.btn_statue_running)

            val finishedStatueButton: MaterialButton =
                balloon.getContentView().findViewById(R.id.btn_statue_success)

            val failStatueButton: MaterialButton =
                balloon.getContentView().findViewById(R.id.btn_statue_failed)

            val closedStatueButton: MaterialButton =
                balloon.getContentView().findViewById(R.id.btn_statue_closed)

            allStatueButton.setOnClickListener {
                viewModel.onSortSubjectsUpdated(SubjectStatue.ALL)
                showEmptyPaperOrNot()
                binding.recyclerHomeList.scheduleLayoutAnimation()
                balloon.dismiss()
            }

            availableStatueButton.setOnClickListener {
                viewModel.onSortSubjectsUpdated(SubjectStatue.AVAILABLE)
                showEmptyPaperOrNot()
                binding.recyclerHomeList.scheduleLayoutAnimation()
                balloon.dismiss()
            }

            runningStatueButton.setOnClickListener {
                viewModel.onSortSubjectsUpdated(SubjectStatue.RUNNING)
                showEmptyPaperOrNot()
                binding.recyclerHomeList.scheduleLayoutAnimation()
                balloon.dismiss()
            }

            finishedStatueButton.setOnClickListener {
                viewModel.onSortSubjectsUpdated(SubjectStatue.SUCCESS)
                showEmptyPaperOrNot()
                binding.recyclerHomeList.scheduleLayoutAnimation()
                balloon.dismiss()
            }

            failStatueButton.setOnClickListener {
                viewModel.onSortSubjectsUpdated(SubjectStatue.FAIL)
                showEmptyPaperOrNot()
                binding.recyclerHomeList.scheduleLayoutAnimation()
                balloon.dismiss()
            }

            closedStatueButton.setOnClickListener {
                viewModel.onSortSubjectsUpdated(SubjectStatue.CLOSED)
                showEmptyPaperOrNot()
                binding.recyclerHomeList.scheduleLayoutAnimation()
                balloon.dismiss()
            }
        }

        whenTaskSelected()
    }

//    private fun getAllSubjects() =
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED)
//            {
//                viewModel.getSubjects.collectLatest { even ->
//                    subjectAdapter.submitList(even)
//                }
//            }
//        }

    private fun getAllSubjects2() =
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED)
            {
                viewModel.get.observe(viewLifecycleOwner) { even ->
                    viewModel.checkIrSubjectsEmptyOrNot(even)
                    subjectAdapter.submitList(even)
                    binding.recyclerHomeList.scheduleLayoutAnimation()
                }
            }
        }

    private fun whenTaskSelected() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.taskEvent.collectLatest {
                    when (it) {
                        is HomeViewModel.TaskEvent.NavigateToEditTaskScreen -> {
                            val action = HomeFragmentDirections.actionHomeFragmentToEditFragment(
                                it.subject,
                                it.qualification
                            )
                            findNavController().navigate(action)
                        }
                        is HomeViewModel.TaskEvent.NavigateToPreviewTaskScreen -> {
                            val action = HomeFragmentDirections.actionHomeFragmentToPreviewFragment(
                                it.subject,
                                it.qualification
                            )
                            findNavController().navigate(action)
                        }
                    }
                }
            }
        }
    }

    private fun showEmptyPaperOrNot() {
        binding.apply {
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED)
                {
                    viewModel.emptyOrNot.collectLatest {
                        when (it) {
                            true -> {
                                imgEmptyPaper.visibility = View.VISIBLE
                                txtEmptyPaper.visibility = View.VISIBLE
                            }
                            false -> {
                                imgEmptyPaper.visibility = View.INVISIBLE
                                txtEmptyPaper.visibility = View.INVISIBLE
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}