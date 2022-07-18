package com.example.subjects.ui.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.subjects.R
import com.example.subjects.adapter.adapters.SubjectAdapter
import com.example.subjects.adapter.adapters.YearChipAdapter
import com.example.subjects.data.entities.Years
import com.example.subjects.databinding.FragmentCategoriesBinding
import com.example.subjects.ui.viewmodel.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CategoriesFragment : Fragment(R.layout.fragment_categories),
        YearChipAdapter.OnChipClick
{
    private var _binding : FragmentCategoriesBinding?= null
    private val binding get() = _binding!!

    private val categoriesViewModel : CategoriesViewModel by viewModels()

    private lateinit var subjectsAdapter : SubjectAdapter
    private lateinit var chipYearAdapter : YearChipAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCategoriesBinding.bind(view)

        subjectsAdapter = SubjectAdapter ({

        },{

        },{

        })

        chipYearAdapter = YearChipAdapter(this)

        getAllChips()
        getAllSubjectByYear()

        binding.apply {

            recChips.apply {
                layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL,false)
                setHasFixedSize(true)
                adapter = chipYearAdapter
            }

            recSubjectsChips.apply {
//                layoutManager = StaggeredGridLayoutManager(2
//                    , StaggeredGridLayoutManager.VERTICAL)
                layoutManager = LinearLayoutManager(requireContext(),
                    LinearLayoutManager.VERTICAL,false)
                setHasFixedSize(true)
                adapter = subjectsAdapter
            }
        }
    }

    private fun getAllChips(){
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            categoriesViewModel.getYears.collectLatest {
                chipYearAdapter.submitList(it)
            }
        }
    }

    private fun getAllSubjectByYear(){
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            categoriesViewModel.getAllSubjectsByYear.collectLatest {
                subjectsAdapter.submitList(it)
            }
        }
    }

    override fun onChipClickListener(chip: Years) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            categoriesViewModel.getSubjectOnClickYear(chip.yearId)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}