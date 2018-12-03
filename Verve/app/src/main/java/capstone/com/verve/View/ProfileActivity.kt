package capstone.com.verve.View

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TabWidget
import android.widget.TextView
import capstone.com.verve.API.FirebaseConnection
import capstone.com.verve.Presenter.UserDetails
import capstone.com.verve.R
import capstone.com.verve.View.Adapters.ProfilePagerAdapter
import kotlinx.android.synthetic.main.activity_profile.*
import org.jetbrains.anko.find
import org.w3c.dom.Text

class ProfileActivity : AppCompatActivity() {

    var txt_name: TextView? = null
    var txt_address: TextView? = null
    var txt_age: TextView? = null
    var txt_birthday: TextView? = null
    var txt_email: TextView? = null
    var btn_logout: ImageButton? = null
    var txt_username : TextView? = null
    var tabs: TabLayout? = null
    var home: ImageButton? = null
    internal var userDetails = UserDetails()
    internal var firebaseConnection = FirebaseConnection()

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_profile)

        txt_username = findViewById(R.id.txt_username)
        txt_name = findViewById(R.id.txt_name)
        txt_address = findViewById(R.id.txt_address)
        txt_age = findViewById(R.id.txt_age)
        txt_birthday = findViewById(R.id.txt_birthday)
        txt_email = findViewById(R.id.txt_email)
        btn_logout = findViewById(R.id.btn_logout)
        tabs = findViewById(R.id.tabLayout)
        home = findViewById(R.id.img_home)

        home?.setOnClickListener {
            showForum()
        }


        userDetails.getUserProfile(firebaseConnection.getProfileReference("Users"), txt_username, txt_name, txt_email, txt_birthday, txt_address)


        tabs?.setTabTextColors(resources.getColor(R.color.LightGray), resources.getColor(R.color.White))
        for (i in 0 until tabs?.tabCount!!) {
            val tab = (tabs?.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(10, 0, 10, 0)
            tab.requestLayout()
        }

        val pageAdapter = ProfilePagerAdapter(supportFragmentManager, 3)
        viewpager.adapter = pageAdapter
        viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
    }

    private fun showForum() {
        val intent = Intent(this@ProfileActivity, ForumActivity::class.java)
        startActivity(intent)
    }

    fun option(v: View){
        if (v.id == R.id.btn_logout){
            firebaseConnection.firebaseAuth.signOut()
            val intent = Intent(this@ProfileActivity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
    }
}
