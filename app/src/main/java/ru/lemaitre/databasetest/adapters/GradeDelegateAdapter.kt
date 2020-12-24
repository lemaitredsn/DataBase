package ru.lemaitre.databasetest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import ru.lemaitre.databasetest.R
import ru.lemaitre.databasetest.data.db.model.Entities

class GradeDelegateAdapter(
        private val onItemClick: (position: Int) -> Unit
) :
        AbsListItemAdapterDelegate<Entities.Grades, Entities, GradeDelegateAdapter.EntitiesHolder>() {


    override fun isForViewType(item: Entities, items: MutableList<Entities>, position: Int): Boolean {
        return item is Entities.Grades
    }

    override fun onCreateViewHolder(parent: ViewGroup): EntitiesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_data, parent, false)
        return EntitiesHolder(view, onItemClick)
    }

    override fun onBindViewHolder(
            item: Entities.Grades,
            holder: EntitiesHolder,
            payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }


    class EntitiesHolder(
            override val containerView: View,
            onItemClick: (position: Int) -> Unit
    ) : EntitiesBaseHolder(containerView, onItemClick) {
        fun bind(screen: Entities.Grades) {
            bindBase(screen.name)
        }
    }
}