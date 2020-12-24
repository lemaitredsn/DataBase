package ru.lemaitre.databasetest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import ru.lemaitre.databasetest.R
import ru.lemaitre.databasetest.data.db.model.University

class ListUniversityDelegateAdapter(
    private val onItemClick: (position:Int) -> Unit,
    private val deleteItem: (position: Int) -> Unit,
    private val changeItem: (position: Int) -> Unit
) :
    AbsListItemAdapterDelegate<University, University, ListUniversityDelegateAdapter.UniversityHolder>() {


    override fun isForViewType(item: University, items: MutableList<University>, position: Int): Boolean {
        return item is University
    }

    override fun onCreateViewHolder(parent: ViewGroup): UniversityHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_university, parent, false)
        return UniversityHolder(view, onItemClick, deleteItem, changeItem)
    }

    override fun onBindViewHolder(
        item: University,
        holder: UniversityHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }


    class UniversityHolder(
        override val containerView: View,
        onItemClick: (position:Int) -> Unit,
        deleteItem: (position: Int) -> Unit,
        changeItem: (position: Int) -> Unit
    ) : UniversityBaseHolder(containerView, onItemClick, deleteItem, changeItem) {
        fun bind(university: University) {
            bindBase(name = university.name,avatar = university.avatar, address = university.address)
        }
    }
}