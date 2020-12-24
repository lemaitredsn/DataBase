package ru.lemaitre.databasetest.presentation.list_descipline

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_descipline.*
import ru.lemaitre.databasetest.R
import ru.lemaitre.databasetest.adapters.EntitiesAdapter
import ru.lemaitre.databasetest.presentation.list_teachers.ListTeachersFragmentDirections


class DesciplineFragment: Fragment(R.layout.fragment_descipline) {
    private val args: DesciplineFragmentArgs by navArgs()
    private val viewModel by viewModels<DesciplineViewModel>()
    private lateinit var adapterListDescipline: EntitiesAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initToolbar()
        initList()
        bindLiveData()
    }

    private fun initList(){
        adapterListDescipline = EntitiesAdapter(::onItemClick, ::deleteClick)
        with(listDesciplineKaphedra){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = adapterListDescipline
        }
    }

    private fun bindLiveData(){
        viewModel.teachers.observe(viewLifecycleOwner){
            adapterListDescipline.items = it
        }
        viewModel.getDedsciplines(args.kaphedraId)
    }

    private fun onItemClick(position: Int){
        // add action if need
    }
    private fun deleteClick(position: Int){}

    private fun initToolbar(){
        toolbarDescipline.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.add_descipline -> {
                    findNavController()
                            .navigate(DesciplineFragmentDirections.actionDesciplineFragmentToAddDesciplineFragment(args.kaphedraId))
                    true
                }
                else -> false
            }
        }

    }
}