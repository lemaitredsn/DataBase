package ru.lemaitre.databasetest.presentation.add.add_group_students

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_add_group_students.*
import ru.lemaitre.databasetest.R


class AddGroupStudentsFragment: Fragment(R.layout.fragment_add_group_students) {
    private val viewModel by viewModels<AddGroupStudentsViewModel>()
    private val args: AddGroupStudentsFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        buttonAddGroupStudents.setOnClickListener {
            if(editTextGroupStudentsName.text.isNullOrBlank()){
                Toast.makeText(requireContext(), "Введите данные", Toast.LENGTH_SHORT).show()
            }else {
                viewModel.addGroupStudent(
                    id = 0L,
                    name = editTextGroupStudentsName.text.toString(),
                    instituteId = args.instituteId
                )
                findNavController().popBackStack()
            }}

    }
}