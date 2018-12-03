package capstone.com.verve.View.Fragments

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import capstone.com.verve.API.Database
import capstone.com.verve.Interface.AcceptListener
import capstone.com.verve.Presenter.Posts

import capstone.com.verve.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.fragment_add_post.*
import org.jetbrains.anko.support.v4.find

class ForumAddPostFragment : DialogFragment(), AcceptListener {

    private var userRef: DatabaseReference? = null

    private var auth: FirebaseAuth? = null

    private var currUser: String? = null

    private var postTitle: EditText? = null

    private var descTitle: EditText? = null

    private var acceptButton: Button? = null

    private var cancelButton: Button? = null

    private var postRef: DatabaseReference? = null

    // private var postList: ArrayList<SampleData> = ArrayList()
    private var listener: OnFragmentInteractionListener? = null

    var posts: Posts = Posts()

    override fun onSubmit() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {


            //RecyclerView
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_add_post, container, false)

        postTitle = rootView.findViewById(R.id.txt_your_name)
        descTitle = rootView.findViewById(R.id.txt_your_num)
        acceptButton = rootView.findViewById(R.id.buttonAccept)
        cancelButton= rootView.findViewById(R.id.buttonCancel)

        userRef = FirebaseDatabase.getInstance().reference.child("Users")
        postRef = FirebaseDatabase.getInstance().reference.child("Posts")
        auth = FirebaseAuth.getInstance()

        acceptButton?.setOnClickListener {

            posts.savePosts(userRef, postRef, auth, postTitle, descTitle, activity!!.applicationContext)
            dismiss()
        }

        cancelButton?.setOnClickListener {
            dismiss()
        }

        return rootView
    }



    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    lateinit var acceptListen: AcceptListener
    fun setListener(accept: AcceptListener) {
        acceptListen = accept
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        fun newInstance(title: String): ForumAddPostFragment {
            val frag = ForumAddPostFragment()
            val args = Bundle()
            args.putString("title", title)
            frag.arguments = args
            return frag
        }
    }

}
