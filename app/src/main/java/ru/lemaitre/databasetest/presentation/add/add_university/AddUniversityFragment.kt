package ru.lemaitre.databasetest.presentation.add.add_university

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_add_university.*
import ru.lemaitre.databasetest.R

class AddUniversityFragment : Fragment(R.layout.fragment_add_university) {

    private val viewModel by viewModels<AddUniversityViewModel>()
    private val args: AddUniversityFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Загружаю для изменений
        viewModel.loadUniversity(args.id)
        viewModel.existingUserLiveData.observe(viewLifecycleOwner){
            editTextUniversityName.setText(it.name)
            editTextUniversityAddress.setText(it.address)
            editTextAvatar.setText(it.avatar)
        }
        //добавляю новый
        buttonAddUniversity.setOnClickListener {
            viewModel.addUniversity(
                id = args.id,
                name = editTextUniversityName.text.toString(),
                address = editTextUniversityAddress.text.toString(),
                avatar = editTextAvatar.text.toString()
            )
            findNavController().popBackStack()
        }

    }
}