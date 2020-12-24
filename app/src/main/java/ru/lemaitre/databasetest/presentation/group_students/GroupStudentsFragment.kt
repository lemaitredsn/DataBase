package ru.lemaitre.databasetest.presentation.group_students

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_group_students.*
import kotlinx.android.synthetic.main.fragment_institute_details.*
import ru.lemaitre.databasetest.R
import ru.lemaitre.databasetest.adapters.EntitiesAdapter
import ru.lemaitre.databasetest.data.db.model.Entities
import ru.lemaitre.databasetest.presentation.institute_details.InstituteDetailsFragmentDirections

class GroupStudentsFragment : Fragment(R.layout.fragment_group_students) {
    private val args: GroupStudentsFragmentArgs by navArgs()
    private lateinit var studentsAdapter: EntitiesAdapter
    private val viewModel: GroupStudentsViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initList()
        bindViewModel()
        initMenu()

    }

    private fun bindViewModel() {
        viewModel.getStudentsByIdGroup(args.groupStudentsId)
        viewModel.students.observe(viewLifecycleOwner) { listStudents ->
            studentsAdapter.items = listStudents
        }

        viewModel.getGroupById(args.groupStudentsId)
        viewModel.group.observe(viewLifecycleOwner){
            group -> toolbarGroupStudent.title = group[0].name
        }
    }

    private fun initList() {
        studentsAdapter = EntitiesAdapter(::clickItem, ::deleteClick)
        with(studentsList) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = studentsAdapter
        }
    }

    private fun clickItem(position: Int) {

        val item = studentsAdapter.items[position] as Entities.Student
        val id = item.id
        val action = GroupStudentsFragmentDirections.actionGroupStudentsFragmentToStudentDetailFragment(id)
        findNavController().navigate(action)
    }
    private fun deleteClick(position: Int){
        val item = studentsAdapter.items[position] as Entities.Student
        val id = item.id
        viewModel.deleteStudentAndHisGradeById(id, args.groupStudentsId)
    }

    private fun initMenu() {
        //Add name to toolbar
        toolbarGroupStudent.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.add_student -> {
                    val action = GroupStudentsFragmentDirections.actionGroupStudentsFragmentToAddStudentFragment(args.groupStudentsId)
                    findNavController().navigate(action)
                    true
                }
                else -> false
            }
        }
    }
}