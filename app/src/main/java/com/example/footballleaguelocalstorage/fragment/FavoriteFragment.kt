package com.example.footballleaguelocalstorage.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.footballleaguelocalstorage.R
import com.example.footballleaguelocalstorage.adapter.viewpager.ViewPagerAdapter
import com.example.footballleaguelocalstorage.fragment.favorite.FavoriteLastMatchFragment
import com.example.footballleaguelocalstorage.fragment.favorite.FavoriteNextMatchFragment
import kotlinx.android.synthetic.main.fragment_favorite.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {

    private val lastMatchFragment : FavoriteLastMatchFragment = FavoriteLastMatchFragment()
    private val nextMatchFragment : FavoriteNextMatchFragment = FavoriteNextMatchFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewPagerAdapter =
            ViewPagerAdapter(childFragmentManager)
        viewPagerAdapter.addFragment(lastMatchFragment, "Last Match")
        viewPagerAdapter.addFragment(nextMatchFragment, "Next Match")

        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

}
