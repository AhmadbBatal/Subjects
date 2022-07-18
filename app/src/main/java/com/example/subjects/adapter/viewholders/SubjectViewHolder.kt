package com.example.subjects.adapter.viewholders

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.subjects.R
import com.example.subjects.data.entities.SubjectWithQualification
import com.example.subjects.databinding.ItemHomeSubjectsListBinding
import com.example.subjects.util.enums.SubjectStatue.*

@SuppressLint("NewApi")
class SubjectViewHolder(
    private val binding: ItemHomeSubjectsListBinding,
    private val onSubjectItemClick: (Boolean) -> Unit,
    private val onBtnEditSubjectClick: (Int) -> Unit,
    private val onBtnViewSubjectClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    var extendCard: Boolean = true

    fun bind(subject: SubjectWithQualification) {
        binding.apply {
            subjectItemId.animation = AnimationUtils.loadAnimation(
                itemView.context, R.anim.fall_down
            )
            txtSubNameItem.text = subject.subject.subjectName
//          txtTeacherNameItem.text = subject.subject.teacherName
            txtYearNameItem.text = subject.subject.subYears
            subject.subject.subjectImage?.let { imgSubjectItem.setImageResource(it) }
            binding.txtQualificationNameItem.text = subject.subject.qualification
            hasQualification(subject)
        }
    }

    init {
        binding.apply {
//            childRoot.layoutTransition.tra
            subjectItemId.setOnClickListener {
                TransitionManager.beginDelayedTransition(childRoot, AutoTransition())

                onSubjectItemClick(true)

                if (extendCard) {
                    conExtendingCard.visibility = View.VISIBLE
                    extendCard = false
                } else {
                    conExtendingCard.visibility = View.GONE
                    extendCard = true
                }
            }

            btnEditSubject.setOnClickListener {
                extendCard = false
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onBtnEditSubjectClick(position)
                }
            }

            btnViewSubject.setOnClickListener {
                extendCard = false
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onBtnViewSubjectClick(position)
                }
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun hasQualification(subject: SubjectWithQualification) {

//        if (subject.qualification.isHasQualification) binding.txtQualificationNameItem.text =
//            subject.subject.qualification
//        else binding.txtQualificationNameItem.text =
//            subject.subject.qualification

//        if(subject.subject.qualificationOpenOrNot == true) {
        when (subject.qualification.subjectStatue) {
            RUNNING -> {
                binding.subjectItemId.isClickable = true
                binding.imgColorItem.setImageDrawable(
                    itemView.context.getDrawable(R.drawable.orange_fill_cicle)
                )
            }
            AVAILABLE -> {
                binding.subjectItemId.isClickable = true
                binding.imgColorItem.setImageDrawable(
                    itemView.context.getDrawable(R.drawable.green_fill_circle)
                )
            }
            SUCCESS -> {
                binding.subjectItemId.isClickable = true
                binding.imgColorItem.setImageDrawable(
                    itemView.context.getDrawable(R.drawable.success_correct)
                )
            }
            FAIL -> {
                binding.subjectItemId.isClickable = true
                binding.imgColorItem.setImageDrawable(
                    itemView.context.getDrawable(R.drawable.fail_retry)
                )
            }
            CLOSED -> {
                binding.subjectItemId.isClickable = false
                binding.imgColorItem.setImageDrawable(
                    itemView.context.getDrawable(R.drawable.red_fill_circle)
                )
            }
            else -> Log.d("IDon'tKnow", "I Don't Know")
        }
//        else{
//            binding.subjectItemId.isClickable = false
//            binding.imgColorItem.setImageDrawable(
//                itemView.context.getDrawable(R.drawable.red_fill_circle)
//            )
//        }
    }
}











