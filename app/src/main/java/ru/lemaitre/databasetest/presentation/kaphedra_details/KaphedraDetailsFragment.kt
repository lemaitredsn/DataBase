package ru.lemaitre.databasetest.presentation.kaphedra_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_kaphedra_detail.*
import ru.lemaitre.databasetest.R
import ru.lemaitre.databasetest.adapters.EntitiesAdapter
import ru.lemaitre.databasetest.data.db.model.Entities
import ru.lemaitre.databasetest.presentation.list_teachers.ListTeachersFragmentDirections

class KaphedraDetailsFragment : Fragment(R.layout.fragment_kaphedra_detail) {
    private val args: KaphedraDetailsFragmentArgs by navArgs()
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        teachersButton.setOnClickListener {
            findNavController().navigate(KaphedraDetailsFragmentDirections.actionKaphedraDetailsFragmentToListTeachersFragment(args.kaphedraId))
        }
        initToolbar()

        desciplineButton.setOnClickListener {
            findNavController().navigate(KaphedraDetailsFragmentDirections.actionKaphedraDetailsFragmentToDesciplineFragment(args.kaphedraId))
        }
    }

    private fun initToolbar() {
        toolbarKaphedraDetail.title = args.kaphedraName
    }

}