package ru.lemaitre.databasetest.adapters

import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_university.*
import ru.lemaitre.databasetest.R

abstract class UniversityBaseHolder(
    view: View,
    onItemClick:(position: Int) -> Unit,
    deleteItem: (position: Int) -> Unit,
    changeItem: (position: Int) -> Unit
) : RecyclerView.ViewHolder(view), LayoutContainer {

    init{
        view.setOnClickListener {
            onItemClick(adapterPosition)
        }

        view.findViewById<ImageButton>(R.id.deleteUniversityBtn).setOnClickListener{
            deleteItem(adapterPosition)
        }

        view.findViewById<ImageButton>(R.id.changeUniversityBtn).setOnClickListener {
            changeItem(adapterPosition)
        }
    }

    protected fun bindBase(
        name: String,
        avatar: String?,
        address: String
    ) {
        nameUniversityTv.text = name
        Glide.with(itemView)
            .load(avatar)
            .placeholder(R.drawable.ic_baseline_account_box_24)
            .into(avatarUniversityIv)
        addressUniversityTv.text = address
    }
}