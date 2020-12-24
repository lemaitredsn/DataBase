package ru.lemaitre.databasetest.presentation.add.add_kaphedra

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_add_kaphedra.*
import ru.lemaitre.databasetest.R


class AddKaphedraFragment: Fragment(R.layout.fragment_add_kaphedra) {
    private val viewModel by viewModels<AddkaphedraViewModel>()
    private val args: AddKaphedraFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        buttonAddKaphedra.setOnClickListener {
            viewModel.addKaphedras(
                id = 0L,
                name = editTextKaphedraName.text.toString(),
                universityId = args.id
            )
            findNavController().popBackStack()
        }

    }
}