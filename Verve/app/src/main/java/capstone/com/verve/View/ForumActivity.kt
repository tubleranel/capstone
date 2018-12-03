package capstone.com.verve.View

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import capstone.com.verve.Interface.AcceptListener
import capstone.com.verve.Presenter.Posts
import capstone.com.verve.R
import capstone.com.verve.R.id.img_profile
import capstone.com.verve.View.Adapters.ForumPagerAdapter
import capstone.com.verve.View.Fragments.ForumAddPostFragment
import kotlinx.android.synthetic.main.activity_forum.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.jetbrains.anko.find

class ForumActivity : BaseView(), AcceptListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forum)

        var img_find = findViewById<ImageButton>(R.id.img_find)
        var img_reminders = findViewById<ImageButton>(R.id.img_reminders)
        var img_home = findViewById<ImageButton>(R.id.img_home)
        var imgProfile = findViewById<ImageButton>(R.id.img_profile)
        var img_messages = findViewById<ImageButton>(R.id.img_messages)
        var tabLayout = findViewById<View>(R.id.tabLayout)



        click_fab.setOnClickListener {
            showPostDialog()
        }

        imgProfile.setOnClickListener {
            showProfile()
        }


        val pageAdapter = ForumPagerAdapter(supportFragmentManager, 2)
        viewpager.adapter = pageAdapter
        viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout as TabLayout?))

    }



    private fun showPostDialog() {
        val fm = supportFragmentManager
        var editNameDialogFragment = ForumAddPostFragment.newInstance("What's Up?")
        editNameDialogFragment.setListener(this@ForumActivity)
        editNameDialogFragment.show(fm, "fragment_edit_name")
    }

    private fun showProfile() {
            val intent = Intent(this@ForumActivity, ProfileActivity::class.java)
            startActivity(intent)
    }

    override fun onSubmit() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
  /*  fun onClickFind() {
        img_find!!.setOnClickListener {
            img_find?.setImageResource(R.drawable.findicongreen)
            img_reminders?.setImageResource(R.mipmap.ic_medicine_black)
            img_home?.setImageResource(R.drawable.vervelogoblack)
            img_profile?.setImageResource(R.drawable.profileicon2)
            img_messages?.setImageResource(R.drawable.enveiconblack)

        }
    }

    fun onClickReminders() {
        img_reminders!!.setOnClickListener {
            img_find?.setImageResource(R.mipmap.ic_find_black)
            img_reminders?.setImageResource(R.drawable.medicineicongreen)
            img_home?.setImageResource(R.drawable.vervelogoblack)
            img_profile?.setImageResource(R.drawable.profileicon2)
            img_messages?.setImageResource(R.drawable.enveiconblack)

        }
    }

    fun onClickHome() {
        img_home!!.setOnClickListener {
            img_find?.setImageResource(R.mipmap.ic_find_black)
            img_reminders?.setImageResource(R.mipmap.ic_medicine_black)
            img_home?.setImageResource(R.drawable.vervelogo)
            img_profile?.setImageResource(R.drawable.profileicon2)
            img_messages?.setImageResource(R.drawable.enveiconblack)

        }
    }

    fun onClickProfile() {
        img_profile!!.setOnClickListener {
            img_find?.setImageResource(R.mipmap.ic_find_black)
            img_reminders?.setImageResource(R.mipmap.ic_medicine_black)
            img_home?.setImageResource(R.drawable.vervelogoblack)
            img_profile?.setImageResource(R.drawable.profileicongren)
            img_messages?.setImageResource(R.drawable.enveiconblack)

            var i = Intent(this, ProfileActivity::class.java)
            startActivity(i)
        }
    }

    fun onClickMessages() {
        img_messages!!.setOnClickListener {
            img_find?.setImageResource(R.mipmap.ic_find_black)
            img_reminders?.setImageResource(R.mipmap.ic_medicine_black)
            img_home?.setImageResource(R.drawable.vervelogoblack)
            img_profile?.setImageResource(R.drawable.profileicon2)
            img_messages?.setImageResource(R.drawable.enveicongreen)

        }
    }*/
