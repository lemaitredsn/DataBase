package ru.lemaitre.databasetest.presentation.add.add_institute

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_add_institute.*
import ru.lemaitre.databasetest.R
import ru.lemaitre.databasetest.presentation.add.add_university.AddUniversityFragmentArgs

class AddInstituteFragment: Fragment(R.layout.fragment_add_institute) {

    private val viewModel by viewModels<AddInstituteViewModel>()
    private val args: AddInstituteFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        buttonAddInstitute.setOnClickListener {
            viewModel.addInstitute(
                id = 0L,
                name = editTextInstituteName.text.toString(),
                universityId = args.id
            )
            findNavController().popBackStack()
        }

    }
}