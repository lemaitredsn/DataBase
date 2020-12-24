package ru.lemaitre.databasetest.presentation.viewpager

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_item_view_pager.*
import ru.lemaitre.databasetest.R
import ru.lemaitre.databasetest.adapters.EntitiesAdapter
import ru.lemaitre.databasetest.data.db.model.Entities
import java.util.ArrayList

class ItemViewPagerFragment: Fragment(R.layout.fragment_item_view_pager) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val screenAdapter = EntitiesAdapter(::getPostition)
        val listS = requireArguments().getParcelableArrayList<Entities.Institute>(KEY_LIST)
        screenAdapter.items = listS as List<Entities>?

        with(listOnBoarding){
            adapter = screenAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }


    }

    private fun getPostition(position:Int){
        Toast.makeText(requireContext(), position.toString(), Toast.LENGTH_SHORT).show()
    }

    companion object{
        private const val KEY_LIST = "LIST DATA"

        fun newInstance(list: List<Entities.Institute>): ItemViewPagerFragment{
            val frag = ItemViewPagerFragment()

            val args = Bundle().apply {
                putParcelableArrayList(KEY_LIST, list.toCollection(ArrayList<Entities.Institute>()))
            }
            frag.arguments = args
            return frag
        }
    }
}