package capstone.com.verve.View.Adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import capstone.com.verve.View.Fragments.ForumHomeFragment
import capstone.com.verve.View.Fragments.ForumPopularFragment

class ForumPagerAdapter (fm: FragmentManager?, numOfTabs: Int) : FragmentPagerAdapter(fm) {

    private var numOfTabs: Int = numOfTabs
    private var followingFragment = ForumHomeFragment()
    private var popularFragment = ForumPopularFragment()

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return followingFragment
            1 -> return popularFragment
            else -> return null
        }    }

    override fun getCount(): Int {
        return numOfTabs
    }
}