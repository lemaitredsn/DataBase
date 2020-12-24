package ru.lemaitre.databasetest.presentation.add.add_teacher

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_add_teacher.*
import ru.lemaitre.databasetest.R

class AddTeacherFragment : Fragment(R.layout.fragment_add_teacher) {
    private val viewModel by viewModels<AddTeacherViewModel>()
    private val args: AddTeacherFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        buttonAddTeacher.setOnClickListener {
            if (editTexTeacherName.text.isNullOrBlank() || editTextTeacherAge.text.isNullOrBlank()) {
                Toast.makeText(requireContext(), "Введите данные", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addTeacher(
                    id = 0L,
                    kaphedraId = args.idKaphedra,
                    name = editTexTeacherName.text.toString(),
                    age = editTextTeacherAge.text.toString().toInt()
                )
                findNavController().popBackStack()
            }
        }
    }

}