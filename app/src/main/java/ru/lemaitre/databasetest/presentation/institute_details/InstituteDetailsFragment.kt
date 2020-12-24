package ru.lemaitre.databasetest.presentation.institute_details

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_institute_details.*
import kotlinx.android.synthetic.main.fragment_list_university.*
import ru.lemaitre.databasetest.R
import ru.lemaitre.databasetest.adapters.EntitiesAdapter
import ru.lemaitre.databasetest.data.db.model.Entities
import ru.lemaitre.databasetest.presentation.list_university.ListUniversityFragmentDirections

class InstituteDetailsFragment : Fragment(R.layout.fragment_institute_details) {
    private val args: InstituteDetailsFragmentArgs by navArgs()
    private val viewModel by viewModels<InstituteDetailsViewModel>()
    private lateinit var adapterListGroupStudents: EntitiesAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initList()
        bindLiveData()
        setMenuListener()

    }

    private fun setMenuListener() {
        toolbarInstituteDetail.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.add_group_students -> {
                    findNavController()
                            .navigate(InstituteDetailsFragmentDirections.actionInstituteDetailsFragmentToAddGroupStudentsFragment(args.instituteId))
                    true
                }
                else -> false
            }
        }
    }

    private fun bindLiveData() {
        viewModel.getGroupStudentByInstituteId(args.instituteId)
        viewModel.listGroupLiveData.observe(viewLifecycleOwner) { listGroupStudents ->
            adapterListGroupStudents.items = listGroupStudents
        }
        viewModel.getInsititute(args.instituteId)
        viewModel.institute.observe(viewLifecycleOwner) { institute ->
            toolbarInstituteDetail.title = institute[0].name
        }
    }


    private fun initList() {
        adapterListGroupStudents = EntitiesAdapter(::clickItem, ::deleteClick)
        with(listGroupStudentsInInstitute) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = adapterListGroupStudents
        }
    }

    private fun clickItem(position: Int) {
        val groupStudent: Entities.GroupStudents = adapterListGroupStudents.items[position] as Entities.GroupStudents
        val id = groupStudent.id
        val action = InstituteDetailsFragmentDirections.actionInstituteDetailsFragmentToGroupStudentsFragment(id)
        findNavController().navigate(action)
    }
    private fun deleteClick(position: Int){}
}