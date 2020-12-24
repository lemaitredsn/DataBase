package ru.lemaitre.databasetest.presentation.viewpager

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_hosting_view_pager.*
import ru.lemaitre.databasetest.R
import ru.lemaitre.databasetest.adapters.ViewPagerAdapter

class HostingViewPagerFragment : Fragment(R.layout.fragment_hosting_view_pager) {

    private val args: HostingViewPagerFragmentArgs by navArgs()


    private val viewModel by viewModels<HostingViewModel>()
    private lateinit var adapter: ViewPagerAdapter


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        viewModel.getRelationByIdUniver(args.id)
        viewModel.univerWithRel.observe(viewLifecycleOwner){
            Log.d("TAG", "university with relation: " + it.toString())
            val institute = it[0].instituties
            val university = it[0].university
            adapter = ViewPagerAdapter(institute, requireActivity())
            viewPager.adapter = adapter


            initTabLayoutMediator()
            initToolbarAndMenu(university.name, args.id)
        }

        viewPager?.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.getTabAt(position)?.removeBadge()
            }
        })


    }

    private fun initTabLayoutMediator() {
        if (viewPager != null) {
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = "Кафедры"
                    1 -> tab.text = "Институты"
                    2 -> tab.text = "Все Студенты"
                }
            }.attach()
        }
    }

    private fun initToolbarAndMenu(name:String, id:Long) {
        toolbarHosting.setTitle(name)
        toolbarHosting.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.add_kaphedra ->{
                    openAddKaphedraFragment(id)
                    true
                }
                R.id.add_institute ->{
                    openAddInstituteFragment(id)
                    true
                }
                R.id.add_student ->{
                    openAddStudentFragment(id)
                    true
                }
                else -> false
            }
        }
    }

    private fun openAddKaphedraFragment(id: Long){
        val action = HostingViewPagerFragmentDirections.actionViewPagerScreensFragmentToAddKaphedraFragment(id)
        findNavController().navigate(action)
    }

    private fun openAddInstituteFragment(id: Long){
        val action = HostingViewPagerFragmentDirections.actionViewPagerScreensFragmentToAddInstituteFragment(id)
        findNavController().navigate(action)
    }

    private fun openAddStudentFragment(id: Long){
        val action = HostingViewPagerFragmentDirections.actionViewPagerScreensFragmentToAddStudentFragment(id)
        findNavController().navigate(action)
    }

}