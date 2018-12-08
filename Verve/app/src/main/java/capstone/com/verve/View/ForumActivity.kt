package capstone.com.verve.View

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import capstone.com.verve.Interface.AcceptListener
import capstone.com.verve.Presenter.Posts
import capstone.com.verve.R
import capstone.com.verve.R.id.img_profile
import capstone.com.verve.View.Adapters.ForumPagerAdapter
import capstone.com.verve.View.Fragments.ForumAddPostFragment
import kotlinx.android.synthetic.main.activity_forum.*
import android.text.Editable
import android.text.TextWatcher
import android.view.View.OnTouchListener


class ForumActivity : BaseView(), AcceptListener {
    var searchBar: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forum)

        var imgFind = findViewById<ImageButton>(R.id.img_find)
        var img_reminders = findViewById<ImageButton>(R.id.img_reminders)
        var img_home = findViewById<ImageButton>(R.id.img_home)
        var imgProfile = findViewById<ImageButton>(R.id.img_profile)
        var img_messages = findViewById<ImageButton>(R.id.img_messages)
        var tabLayout = findViewById<View>(R.id.tabLayout)
        searchBar = findViewById(R.id.searchBar)

        bindViewAndAdapter()

        click_fab.setOnClickListener {
            showPostDialog()
        }

        //BOTTOM NAVIGATION REGION
        imgProfile.setOnClickListener {
            showProfile()
        }

        imgFind.setOnClickListener {
            showFind()
        }
        //BOTTOM NAVIGATION REGION

        //SEARCH REGION
        btn_search.setOnClickListener {
            showSearchBar()
        }
        showSearchBarResults()
        closeSearchBar()
        //SEARCH REGION

    }

    private fun bindViewAndAdapter() {
        val pageAdapter = ForumPagerAdapter(supportFragmentManager, 2)
        viewpager.adapter = pageAdapter
        viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
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

    private fun showFind() {
        val intent = Intent(this@ForumActivity, FindActivity::class.java)
        startActivity(intent)
    }

    private fun showSearchBar() {
        searchBar?.visibility = View.VISIBLE
        btn_sidemenu.visibility = View.INVISIBLE
        txt_vervetop.visibility = View.INVISIBLE
        btn_search.visibility = View.INVISIBLE
    }

    private fun showSearchBarResults() {
        searchBar?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
              //  forumAdapter?.getFilter()?.filter(editable.toString())
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun closeSearchBar() {
        searchBar?.setOnTouchListener(OnTouchListener { v, event ->
            val DRAWABLE_LEFT = 0
            val DRAWABLE_TOP = 1
            val DRAWABLE_RIGHT = 2
            val DRAWABLE_BOTTOM = 3

            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= searchBar?.right!! - searchBar?.compoundDrawables!![DRAWABLE_RIGHT].bounds.width()) {
                    searchBar?.visibility = View.INVISIBLE
                    searchBar?.hideSoftKeyboardOnFocusLostEnabled(true)
                    btn_sidemenu.visibility = View.VISIBLE
                    txt_vervetop.visibility = View.VISIBLE
                    btn_search.visibility = View.VISIBLE

                    return@OnTouchListener true
                }
            }
            false
        })
    }

    override fun onSubmit() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
