package capstone.com.verve.View.Adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import capstone.com.verve.View.Fragments.*


class ProfilePagerAdapter (fm: FragmentManager?, numOfTabs: Int) : FragmentPagerAdapter(fm) {

    private var numOfTabs: Int = numOfTabs
    private var postsFragment = ProfilePostsFragment()
    private var medicalhistoryFragment = ProfileMedicalHistoryFragment()
    private var consultdoctorsFragment = ProfileConsultingDoctorsFragment()


    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return postsFragment
            1 -> return medicalhistoryFragment
            2 -> return consultdoctorsFragment
            else -> return null
        }    }

    override fun getCount(): Int {
        return numOfTabs
    }

}