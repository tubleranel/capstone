package capstone.com.verve.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageButton
import capstone.com.verve.R

class FindActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_find)

        var imgFind = findViewById<ImageButton>(R.id.img_find)
        var img_reminders = findViewById<ImageButton>(R.id.img_reminders)
        var imgForum = findViewById<ImageButton>(R.id.img_home)
        var imgProfile = findViewById<ImageButton>(R.id.img_profile)
        var img_messages = findViewById<ImageButton>(R.id.img_messages)

        imgForum.setOnClickListener {
            showForum()
        }

        imgProfile.setOnClickListener {
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
