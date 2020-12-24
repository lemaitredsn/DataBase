package ru.lemaitre.databasetest.presentation.add.add_grade

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_add_grade.*
import kotlinx.android.synthetic.main.spinner_item.view.*
import ru.lemaitre.databasetest.R
import ru.lemaitre.databasetest.data.db.model.Entities

class AddGradeFragment : Fragment(R.layout.fragment_add_grade), AdapterView.OnItemSelectedListener {
    private val viewModel by viewModels<AddGradeViewModel>()
    private val args by navArgs<AddGradeFragmentArgs>()
    var nameDesciplineChooesed: String? = null
    private lateinit var listDesciplines: List<Entities.Descipline>
    private lateinit var adapter: ArrayAdapter<String>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        viewModel.loadDescipline()
        viewModel.desciplines.observe(viewLifecycleOwner) { listDescipline ->
            listDesciplines = listDescipline
            adapter = ArrayAdapter<String>(requireContext(),
                    R.layout.spinner_item,
                    listToArrayString(listDescipline)
            )
            spinnerDiscipline.adapter = adapter
            spinnerDiscipline.onItemSelectedListener = this
        }

        buttonAddGrade.setOnClickListener {
            if(nameDesciplineChooesed!= null && !editTextGrade.text.isNullOrBlank()){
                viewModel.addGrade(args.idStudent, getIdByName(nameDesciplineChooesed!!), editTextGrade.text.toString())
            }
            findNavController().popBackStack()
        }
    }


    private fun listToArrayString(list: List<Entities.Descipline>): Array<String> {
        val s = Array<String>(size = list.size, init = { i -> "i" })
        for (i in list.indices) {
            s[i] = list[i].name
        }
        return s
    }

    private fun getIdByName(name:String): Long{
        val filtered = listDesciplines.filter { it.name == name }
        return filtered[0].id
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        val item = p0?.getItemIdAtPosition(position)
        nameDesciplineChooesed = item?.toInt()?.let { adapter.getItem(it) }

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Toast.makeText(requireContext(), "nothing select", Toast.LENGTH_SHORT).show()
    }
}