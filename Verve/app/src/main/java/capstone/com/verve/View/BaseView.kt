package capstone.com.verve.View

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.indeterminateProgressDialog

abstract class BaseView : AppCompatActivity() {

    lateinit var progressDialog : ProgressDialog


    fun showProgressDialog () {
        progressDialog = indeterminateProgressDialog("Loading...")
        progressDialog.show()
    }
}