package capstone.com.verve.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import capstone.com.verve.R

class ForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_forgot_password)
    }

    fun option(v: View) {
        var i: Intent? = null
        val chooser: Intent? = null

        if (v.id == R.id.click_signup) {
            i = Intent(this, RegisterPatientActivity::class.java)
            startActivity(i)
        }

        if (v.id == R.id.click_login) {
            i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
    }
}
