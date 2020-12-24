package ru.lemaitre.databasetest.presentation.list_university

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list_university.*
import ru.lemaitre.databasetest.R
import ru.lemaitre.databasetest.adapters.UniversityAdapter
import ru.lemaitre.databasetest.utils.AutoClearedValue

class ListUniversityFragment : Fragment(R.layout.fragment_list_university) {

    private val viewModel by viewModels<UniversitiesViewModel>()
    var universityAdapter: UniversityAdapter by AutoClearedValue(this)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setMenuListener()
        initList()
        bindViewModel()
    }

    private fun bindViewModel() {
        viewModel.universitiesLD.observe(viewLifecycleOwner) { universities ->
            universityAdapter.items = universities
        }
        viewModel.getAllUniversities()
    }

    private fun initList() {
        universityAdapter = UniversityAdapter(::clickedItem, ::deleteItem, ::changeItem)
        with(listUniversities) {
            adapter = universityAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun changeItem(position: Int){
        val idPosition = universityAdapter.items[position].id
        val action = ListUniversityFragmentDirections.actionListUniversityFragmentToAddUniversityFragment(idPosition)
        findNavController().navigate(action)
    }

    private fun deleteItem(position: Int){
        val idPosition = universityAdapter.items[position].id
        viewModel.deleteUniversityById(idPosition)
        viewModel.getAllUniversities()
    }

    private fun clickedItem(position: Int) {
        val idPosition = universityAdapter.items[position].id
        val action =
            ListUniversityFragmentDirections.actionListUniversityFragmentToViewPagerScreensFragment(
                idPosition
            )
        findNavController().navigate(action)
    }

    private fun setMenuListener() {
        searchMode()
        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.add_university ->{
                    openAddUniversityFragment()
                    true
                }
                else -> false
            }
        }
    }

    private fun openAddUniversityFragment() {
        findNavController().navigate(R.id.action_listUniversityFragment_to_addUniversityFragment)
    }

    private fun searchMode() {
        //TODO перенести в viewModel
        val searchItem = toolbar.menu.findItem(R.id.action_search)
        (searchItem.actionView as SearchView).setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(context, query.toString(), Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Toast.makeText(context, newText.toString(), Toast.LENGTH_SHORT).show()
                return true
            }
        })
    }
}