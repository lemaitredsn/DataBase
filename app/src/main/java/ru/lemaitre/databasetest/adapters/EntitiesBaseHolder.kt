package ru.lemaitre.databasetest.adapters

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_data.*
import ru.lemaitre.databasetest.R

abstract class EntitiesBaseHolder(
    override val containerView: View?,
    onItemClick: (position: Int) -> Unit
) : RecyclerView.ViewHolder(containerView!!), LayoutContainer {

    init {
        itemView.setOnClickListener {
            onItemClick(adapterPosition)
        }
    }

    protected fun bindBase(
        dataScreen: String?
    ) {
        nameItemScreen.text = dataScreen ?: "не прикрепилось"
    }
}