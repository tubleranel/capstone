package capstone.com.verve.View.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import capstone.com.verve.Interface.iUserClickListener
import capstone.com.verve.Model.ForumData
import capstone.com.verve.R
import kotlinx.android.synthetic.main.item_post_forum.view.*

class ForumRecyclerViewAdapter (var items: MutableList<ForumData>) : RecyclerView.Adapter<ForumRecyclerViewAdapter.ViewHolder>(){
    private var context: Context? = null
    var itemsList: MutableList<ForumData> = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_post_forum, parent, false)
        context = itemView.context

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return itemsList.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemsList?.get(position))

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(post: ForumData) = with(itemView) {
            txtName?.text = post.uName
            txtDate?.text = post.uDate
            txtTime?.text = post.uTime
            txtHearts?.text = post.hearts
            txtComments?.text = post.comments
            txtPostTitle?.text = post.postTitle
            txtPostDetails?.text = post.postDetails
            txtLastPerson?.text = post.lastPerson
            txtLastComment?.text = post.lastComment
            txtDateComment?.text = post.dateComment
            txtTimeComment?.text = post.timeComment
        }
    }


}