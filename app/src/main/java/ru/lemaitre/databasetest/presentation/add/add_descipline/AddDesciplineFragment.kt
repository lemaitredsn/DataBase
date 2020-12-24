package ru.lemaitre.databasetest.presentation.add.add_descipline

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_add_descipline.*
import ru.lemaitre.databasetest.R

class AddDesciplineFragment: Fragment(R.layout.fragment_add_descipline) {
    private val viewModel by viewModels<AddDesciplineViewModel>()
    private val args: AddDesciplineFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        buttonAddDescipline.setOnClickListener {
            if (editTextDesciplineName.text.isNullOrBlank()){
                Toast.makeText(requireContext(), "Введите данные", Toast.LENGTH_SHORT).show()
            }else {
                viewModel.addDescipline(
                    id = 0L,
                    name = editTextDesciplineName.text.toString(),
                    kaphedraId = args.kaphedraId
                )
                findNavController().popBackStack()
            }
        }
    }
}