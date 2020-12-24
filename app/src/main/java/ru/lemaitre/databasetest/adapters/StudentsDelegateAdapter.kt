package ru.lemaitre.databasetest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import ru.lemaitre.databasetest.R
import ru.lemaitre.databasetest.data.db.model.Entities


class StudentsDelegateAdapter (
        private val onItemClick: (position: Int) -> Unit,
        private val deleteClick:(position: Int)-> Unit
) :
        AbsListItemAdapterDelegate<Entities.Student, Entities, StudentsDelegateAdapter.EntitiesHolder>() {


    override fun isForViewType(item: Entities, items: MutableList<Entities>, position: Int): Boolean {
        return item is Entities.Student
    }

    override fun onCreateViewHolder(parent: ViewGroup): EntitiesHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_students, parent, false)
        return EntitiesHolder(view, onItemClick, deleteClick)
    }

    override fun onBindViewHolder(
            item: Entities.Student,
            holder: EntitiesHolder,
            payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }


    class EntitiesHolder(
            override val containerView: View,
            onItemClick: (position: Int) -> Unit,
            deleteClick: (position: Int) -> Unit
    ) : EntitiesBaseHolder(containerView, onItemClick) {

        init {
            containerView.findViewById<ImageButton>(R.id.deleteStudentBtn).setOnClickListener{
                deleteClick(adapterPosition)
            }
        }

        fun bind(screen: Entities.Student) {
            bindBase(screen.name)
        }
    }
}