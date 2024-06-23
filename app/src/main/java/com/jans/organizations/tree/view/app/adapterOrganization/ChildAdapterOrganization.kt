package com.jans.organizations.tree.view.app.adapterOrganization

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jans.organizations.tree.view.app.activities.InfoScreenOrganization
import com.jans.organizations.tree.view.app.R
import com.jans.organizations.tree.view.app.modelOrganization.OrganizationItems
import kotlin.math.exp


class ChildAdapterOrganization(private val newItemList: List<OrganizationItems>) :
    RecyclerView.Adapter<ViewHolder>() {
    companion object {
        private const val VIEW_TYPE_DEAL_MIDDLE_ITEM = 2
        private const val VIEW_TYPE_DEAL_LAST_ITEM = 3
    }

    lateinit var context: Context


    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) {
            VIEW_TYPE_DEAL_LAST_ITEM
        } else {
            VIEW_TYPE_DEAL_MIDDLE_ITEM
        }
    }


    private fun codeBindViewHolder(position:Int,item: OrganizationItems,type: Int,holder: ViewHolder){
        val parentRV:RecyclerView
        val parentTextViewBox:LinearLayout
        val parentNewScreenButton:LinearLayout
        val expandButton:ImageView
        val childrenList = item.children!!
        var viewVertical : View

        if(type == VIEW_TYPE_DEAL_MIDDLE_ITEM){
            holder as MiddleDealHolder
            holder.titleTextView.text = item.title
            parentRV = holder.parentRecyclerView
            expandButton = holder.expandButton
            parentTextViewBox = holder.parentTextViewBox
            parentNewScreenButton = holder.parentNewScreenButton
            viewVertical = holder.viewVertical
        }
        else{
            holder as LastDealHolder
            parentRV = holder.parentRecyclerView
            expandButton = holder.expandButton
            parentTextViewBox = holder.parentTextViewBox
            parentNewScreenButton = holder.parentNewScreenButton
            viewVertical = holder.viewVertical
        }

        val activity = context as Activity
        activity.runOnUiThread {
            val layoutManager = LinearLayoutManager(context)
            layoutManager.isAutoMeasureEnabled = true
            parentRV.layoutManager = layoutManager
            val adapter = GrandChildAdapterOrganization(childrenList)
            parentRV.adapter = adapter
        }





        // check collapse value from Json
        var collapse = item.collapse
        if(collapse){
            parentRV.visibility = View.GONE
            expandButton.setImageResource(R.drawable.baseline_add_24)
        }
        else{
            parentRV.visibility = View.VISIBLE
            expandButton.setImageResource(R.drawable.baseline_remove_24)
        }



        if (childrenList.isEmpty()) {
            expandButton.visibility = View.GONE
        } else {
            expandButton.visibility = View.VISIBLE



            parentTextViewBox.setOnClickListener {
                collapse = !collapse
                if (collapse) {
                    parentRV.visibility = View.GONE
                    expandButton.setImageResource(R.drawable.baseline_add_24)
                } else {
                    parentRV.visibility = View.VISIBLE
                    expandButton.setImageResource(R.drawable.baseline_remove_24)
                }


            }
        }

        parentNewScreenButton.setOnClickListener {
            context.startActivity(Intent(context, InfoScreenOrganization::class.java))
        }

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = newItemList[position]
        context = holder.itemView.context
        when (holder.itemViewType) {
            VIEW_TYPE_DEAL_MIDDLE_ITEM -> {
                codeBindViewHolder(position,item,VIEW_TYPE_DEAL_MIDDLE_ITEM,holder as MiddleDealHolder)
            }

            VIEW_TYPE_DEAL_LAST_ITEM -> {
                codeBindViewHolder(position,item,VIEW_TYPE_DEAL_LAST_ITEM, holder as LastDealHolder)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View
        return  if (viewType == VIEW_TYPE_DEAL_MIDDLE_ITEM) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.child_item_layout_middle, parent, false)
            MiddleDealHolder(view)
        } else {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.child_item_layout_end, parent, false)
            LastDealHolder(view)
        }
    }
    override fun getItemCount(): Int {
        return newItemList.size
    }
    private class MiddleDealHolder internal constructor(itemView: View) :
        ViewHolder(itemView) {

        var dealProductImage: LinearLayout = itemView.findViewById(R.id.dealProductImage)

        val titleTextView: TextView = itemView.findViewById(R.id.parentTitleTextView)
        val parentTextViewBox: LinearLayout = itemView.findViewById(R.id.parent_title_text_view_box)
        val parentNewScreenButton: LinearLayout = itemView.findViewById(R.id.root_new_page_button)
        val parentRecyclerView: RecyclerView = itemView.findViewById(R.id.rvParent)
        val expandButton: ImageView = itemView.findViewById(R.id.ivExpandedRoot)
        val viewVertical: View = itemView.findViewById(R.id.viewVertical)

    }

    private class LastDealHolder(itemView: View) :
        ViewHolder(itemView) {
        var dealProductImage: LinearLayout = itemView.findViewById(R.id.dealProductImage)
        val viewVertical: View = itemView.findViewById(R.id.viewVertical)

        val titleTextView: TextView = itemView.findViewById(R.id.parentTitleTextView)
        val parentTextViewBox: LinearLayout = itemView.findViewById(R.id.parent_title_text_view_box)
        val parentNewScreenButton: LinearLayout = itemView.findViewById(R.id.root_new_page_button)
        val parentRecyclerView: RecyclerView = itemView.findViewById(R.id.rvParent)
        val expandButton: ImageView = itemView.findViewById(R.id.ivExpandedRoot)
    }
}