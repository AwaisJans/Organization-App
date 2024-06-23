package com.jans.organizations.tree.view.app.adapterOrganization

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jans.organizations.tree.view.app.R
import com.jans.organizations.tree.view.app.activities.InfoScreenOrganization
import com.jans.organizations.tree.view.app.modelOrganization.OrganizationItems


class ParentAdapterOrganization(private val parentItemList: List<OrganizationItems>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.parent_item_layout, parent, false)
        return ParentViewHolder(view)
    }

    class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val parentTitleTextView: TextView = itemView.findViewById(R.id.parentTitleTextView)
        val parentTextViewBox: RelativeLayout =
            itemView.findViewById(R.id.parent_title_text_view_box)
        val parentNewScreenButton: LinearLayout = itemView.findViewById(R.id.parent_new_page_button)
        val childRecyclerView: RecyclerView = itemView.findViewById(R.id.rvChild)
        val parentExpandButton: ImageView = itemView.findViewById(R.id.ivExpandedParent)

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val parentItem = parentItemList[position]

        context = holder.itemView.context

        when (holder) {
            is ParentViewHolder -> {
                holder.parentTitleTextView.text = parentItem.title
                val childList = parentItem.children!!
                val childRV = holder.childRecyclerView
                val parentExpandButton = holder.parentExpandButton

                // check collapse value from Json
                var collapse = parentItem.collapse
                if (collapse) {
                    childRV.visibility = GONE
                    parentExpandButton.setImageResource(R.drawable.baseline_add_24)
                } else {
                    childRV.visibility = View.VISIBLE
                    parentExpandButton.setImageResource(R.drawable.baseline_remove_24)
                }

                if (childList.isEmpty()) {
                    parentExpandButton.visibility = GONE
                } else {
                    parentExpandButton.visibility = View.VISIBLE
                    holder.parentTextViewBox.setOnClickListener {

                        collapse = !collapse

                        if (collapse) {
                            childRV.visibility = GONE
                            parentExpandButton.setImageResource(R.drawable.baseline_add_24)
                        } else {
                            childRV.visibility = View.VISIBLE
                            parentExpandButton.setImageResource(R.drawable.baseline_remove_24)
                        }
                    }
                }


                // code will start from here
                childRV.layoutManager = LinearLayoutManager(context)
                childRV.adapter = ChildAdapterOrganization(childList)
                holder.parentNewScreenButton.setOnClickListener {
                    context.startActivity(Intent(context, InfoScreenOrganization::class.java))
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return parentItemList.size
    }

}