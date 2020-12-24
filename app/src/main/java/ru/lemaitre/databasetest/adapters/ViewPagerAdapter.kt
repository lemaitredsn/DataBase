package ru.lemaitre.databasetest.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.lemaitre.databasetest.data.db.model.Entities
import ru.lemaitre.databasetest.presentation.viewpager.ItemViewPagerFragment


class ViewPagerAdapter(
    private val list: List<Entities.Institute>,
    activity: FragmentActivity
) : FragmentStateAdapter(activity) {



    //TODO тут внимательно
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ItemViewPagerFragment.newInstance(emptyList())
            1 -> ItemViewPagerFragment.newInstance(list)
            2 -> ItemViewPagerFragment.newInstance(emptyList())
            else -> throw Exception("ошибка количества вьюпэйджер")
        }
    }
}
