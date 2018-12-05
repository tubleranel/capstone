package capstone.com.verve.View.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import capstone.com.verve.Presenter.Posts
import capstone.com.verve.Presenter.UserPosts
import capstone.com.verve.Presenter.Users

import capstone.com.verve.R
import capstone.com.verve.View.Adapters.ForumPagerAdapter
import capstone.com.verve.View.Adapters.ForumRecyclerViewAdapter
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_forum_following.*
import kotlinx.android.synthetic.main.item_post_forum.view.*
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ForumFollowingFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ForumFollowingFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ForumFollowingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    lateinit var mPostsViewHolder: FirebaseRecyclerAdapter<UserPosts, PostsViewHolder>
    lateinit var mRecyclerView: RecyclerView
    lateinit var mDatabase: DatabaseReference
    private var postRef: DatabaseReference? = null
    private var userRef: DatabaseReference? = null
    private var auth: FirebaseAuth? = null
    internal var posts = Posts()

    private var editComment: EditText? = null

    var commentImage: ImageButton? = null

    private var postId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_forum_following, container, false)
        mRecyclerView = rootView.findViewById(R.id.followingRecyclerView)


        return rootView
    }

    override fun onStart() {
        super.onStart()
        mPostsViewHolder.startListening()
    }

    override fun onStop() {
        super.onStop()
        mPostsViewHolder.stopListening()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mRecyclerView.hasFixedSize()
        var layoutManager = LinearLayoutManager(context)
        mRecyclerView.layoutManager = layoutManager

        /* for (i in 1..10) {
             var postsData = ForumData()

             postsData.setUname("John Ranel Tuble $i")
             postsData.setUdate("11/25/2018")
             postsData.setUtime("12:15 PM")
             postsData.setcomments("6")
             postsData.sethearts("15")
             postsData.setPosttitle("I SURVIVED!")
             postsData.setPostdetails("I want to express my deepest gratitude to my family and friends for being with me during this challenging journey")
             postsData.setdateComment("11/25/2018")
             postsData.settimeComment("11:25 PM")
             postsData.setlastPerson("Syuujie Yoshino")
             postsData.setLastcomment("Wow. Congratulations! Can't wait to see you again.")

             postList.add(postsData)
         }
 */

        //firebase recyclerview
        mDatabase = FirebaseDatabase.getInstance().reference
        val postsQuery = mDatabase.child("Posts")
        val postsOptions =
            FirebaseRecyclerOptions.Builder<UserPosts>().setQuery(postsQuery, UserPosts::class.java).build()


        mPostsViewHolder = object : FirebaseRecyclerAdapter<UserPosts, PostsViewHolder>(postsOptions) {
            override fun onBindViewHolder(holder: PostsViewHolder, position: Int, model: UserPosts) {
                holder.bind(getItem(position))

                postId = getRef(position).key
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_post_forum, parent, false)

                editComment = view.findViewById(R.id.editComment)
                commentImage = view.findViewById(R.id.btnCommentPost)

                userRef = FirebaseDatabase.getInstance().reference.child("Users")
                postRef = FirebaseDatabase.getInstance().reference.child("Posts")
                auth = FirebaseAuth.getInstance()

                commentImage?.setOnClickListener {
                    posts.saveComment(userRef, auth, postId, postRef, activity!!.applicationContext, editComment)
                }
                return PostsViewHolder(view)
            }

        }

        mRecyclerView.adapter = mPostsViewHolder
    }

    class PostsViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {

        fun bind(post: UserPosts) = with(itemView) {
            txtDate?.text = post.datePost


            if (post.middlename.isEmpty()) {
                txtName?.text = post.firstname.plus(" " + post.lastname)
            } else {
                txtName?.text = post.firstname.plus(" " + post.middlename.get(0) + ".").plus(" " + post.lastname)
            }
            txtPostDetails?.text = post.postDescription
            txtPostTitle?.text = post.postTitle
            txtTime?.text = post.timePost
            /* txtHearts?.text = post.hearts
             txtComments?.text = post.comments*/


            /* txtLastPerson?.text = post.lastPerson
             txtLastComment?.text = post.lastComment
             txtDateComment?.text = post.dateComment
             txtTimeComment?.text = post.timeComment*/

        }
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
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ForumFollowingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ForumFollowingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun option(v: View) {
        if (v.id == R.id.btnCommentPost) {

        }
    }
}
