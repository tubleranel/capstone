package capstone.com.verve.View

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.RadioButton
import capstone.com.verve.API.FirebaseConnection
import capstone.com.verve.Presenter.Registration
import capstone.com.verve.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.ImageButton
import java.text.SimpleDateFormat
import java.util.*


class RegisterPatientActivity : AppCompatActivity() {

    private var auth: FirebaseAuth? = null
    internal var register: Registration? = null
    var etxt_firstname: EditText? = null
    var etxt_middlename: EditText? = null
    var etxt_lastname: EditText? = null
    var etxt_username: EditText? = null
    var etxt_password: EditText? = null
    var etxt_mobile: EditText? = null
    var etxt_email: EditText? = null
    var etxt_address: EditText? = null
    var etxt_birthdate: EditText? = null
    var rad_male: RadioButton? = null
    var rad_female: RadioButton? = null
    var btn_datepicker: ImageButton? = null
    private var user: FirebaseUser? = null
    internal var firebaseConnection = FirebaseConnection()
    private val myCalendar = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_register_patient)
        register = Registration()
        etxt_firstname = findViewById(R.id.et_firstname)
        etxt_middlename = findViewById(R.id.et_middlename)
        etxt_lastname = findViewById(R.id.et_lastname)
        etxt_username = findViewById(R.id.et_username)
        etxt_password = findViewById(R.id.et_password)
        etxt_mobile = findViewById(R.id.et_mobile)
        etxt_email = findViewById(R.id.et_email)
        etxt_address = findViewById(R.id.et_address)
        etxt_birthdate = findViewById(R.id.et_birthday)
        btn_datepicker = findViewById(R.id.btn_datepicker)

        rad_male = findViewById(R.id.radioMale);
        rad_female = findViewById(R.id.radioFemale)

        auth = firebaseConnection.firebaseAuth
        user = firebaseConnection.firebaseUser


        btn_datepicker?.setOnClickListener {
            DatePickerDialog(
                this@RegisterPatientActivity, datePickerListener, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    val datePickerListener = object:DatePickerDialog.OnDateSetListener {
        override fun onDateSet(view: DatePicker, year:Int, monthOfYear:Int,
                               dayOfMonth:Int) {

            var today = Calendar.getInstance()
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val myFormat = "MM/dd/yy"
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            etxt_birthdate?.setText(sdf.format(myCalendar.time))
        }
    }


    fun optionRegister(v: View) {
        val i: Intent? = null
        val chooser: Intent? = null

        if (v.id == R.id.btn_register) {
            register?.registerPatient(
                etxt_firstname,
                etxt_middlename,
                etxt_lastname,
                etxt_username,
                etxt_password,
                etxt_mobile,
                etxt_email,
                etxt_address,
                etxt_birthdate,
                rad_male,
                rad_female,
                auth,
                this@RegisterPatientActivity,
                user
            )
        }
    }


    fun option(v: View) {
        var i: Intent? = null
        val chooser: Intent? = null

        /*if (v.id == R.id.click_about) {
            i = Intent(this, AboutActivity::class.java)
            startActivity(i)
        }*/

        if (v.id == R.id.click_login) {
            i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
    }
}
