package ru.lemaitre.databasetest.adapters

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.lemaitre.databasetest.data.db.model.University


class UniversityAdapter(
    onItemClick: (position:Int) -> Unit,
    deleteItem: (position: Int) -> Unit,
    changeItem: (position: Int) -> Unit
): AsyncListDifferDelegationAdapter<University>(ContactDiffUtilCallBack()) {

    init {
        delegatesManager.addDelegate(ListUniversityDelegateAdapter(onItemClick, deleteItem, changeItem))
    }

    class ContactDiffUtilCallBack: DiffUtil.ItemCallback<University>(){
        override fun areItemsTheSame(oldItem: University, newItem: University): Boolean {
            return oldItem.name == newItem.name && oldItem.address == newItem.address
        }

        override fun areContentsTheSame(oldItem: University, newItem: University): Boolean {
            return oldItem == newItem
        }
    }
}