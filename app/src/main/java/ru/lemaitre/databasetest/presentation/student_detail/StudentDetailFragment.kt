package ru.lemaitre.databasetest.presentation.student_detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.fragment_teachers.*
import ru.lemaitre.databasetest.R
import ru.lemaitre.databasetest.data.db.model.Entities
import ru.lemaitre.databasetest.presentation.list_teachers.ListTeachersFragmentDirections

class StudentDetailFragment: Fragment(R.layout.fragment_student_detail) {
    private val args: StudentDetailFragmentArgs by navArgs()
    private val viewModel:StudentDetailViewModel by viewModels()
    private var text = ""

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getStudentsById(args.studentId)
        viewModel.students.observe(viewLifecycleOwner){
            toolbarStudentDetail.title = it[0].name
        }

        viewModel.getNameGrades(args.studentId)
        viewModel.desciplineName.observe(viewLifecycleOwner){
            studentDesciplineTextView.text = it.toString()
        }

        toolbarStudentDetail.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.add_grade_student -> {
                    findNavController()
                            .navigate(StudentDetailFragmentDirections
                                    .actionStudentDetailFragmentToAddGradeFragment(args.studentId))
                    true
                }
                else -> false
            }
        }
    }
}