package capstone.com.verve.View

import android.app.ProgressDialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import org.jetbrains.anko.indeterminateProgressDialog

abstract class BaseView : AppCompatActivity() {

    lateinit var progressDialog : ProgressDialog

    fun showProgressDialog () {
        progressDialog = indeterminateProgressDialog("Loading...")
        progressDialog.show()
    }

    fun hideProgressDialog () {
        progressDialog.dismiss()
    }

    fun EditText.hideSoftKeyboardOnFocusLostEnabled(enabled: Boolean) {
        val listener = if (enabled)
            OnFocusLostListener()
        else
            null
        onFocusChangeListener = listener
    }

    class OnFocusLostListener: View.OnFocusChangeListener {
        override fun onFocusChange(v: View, hasFocus: Boolean) {
            if (!hasFocus) {
                val imm = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
            }
        }
    }

}