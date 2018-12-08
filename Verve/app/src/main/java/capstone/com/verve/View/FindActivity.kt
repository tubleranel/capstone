package capstone.com.verve.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageButton
import capstone.com.verve.R

class FindActivity : AppCompatActivity() {

    var imgForum: ImageButton? = null
    var imgProfile: ImageButton? = null
    var imgReminders: ImageButton? = null
    var imgChat: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_find)

        imgReminders = findViewById(R.id.img_reminders)
        imgForum = findViewById(R.id.img_home)
        imgProfile = findViewById(R.id.img_profile)
        imgChat = findViewById(R.id.img_messages)

        imgForum?.setOnClickListener {
            showForum()
        }

        imgProfile?.setOnClickListener {
            showProfile()
        }
    }

    private fun showForum() {
        val intent = Intent(this@FindActivity, ForumActivity::class.java)
        startActivity(intent)
    }

    private fun showProfile() {
        val intent = Intent(this@FindActivity, ProfileActivity::class.java)
        startActivity(intent)
    }
}
