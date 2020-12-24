package ru.lemaitre.databasetest.adapters

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.lemaitre.databasetest.data.db.model.Entities

class EntitiesAdapter(private val onItemClick: (position: Int) -> Unit): AsyncListDifferDelegationAdapter<Entities>(ScreenDiffUtilCallBack()) {

    init {
        delegatesManager.addDelegate(InstituteDelegateAdapter(onItemClick))
    }

    class ScreenDiffUtilCallBack: DiffUtil.ItemCallback<Entities>(){
        override fun areItemsTheSame(oldItem: Entities, newItem: Entities): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Entities, newItem: Entities): Boolean {
            return oldItem == newItem
        }
    }
}