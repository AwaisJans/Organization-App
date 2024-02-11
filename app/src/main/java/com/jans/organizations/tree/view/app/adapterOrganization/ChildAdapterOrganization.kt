package com.jans.organizations.tree.view.app.adapterOrganization

import android.content.Context
import android.content.Intent
import android.hardware.display.DisplayManager
import android.util.Log
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jans.organizations.tree.view.app.InfoScreenOrganization
import com.jans.organizations.tree.view.app.R
import com.jans.organizations.tree.view.app.modelOrganization.OrganizationItems
import java.util.Arrays


class ChildAdapterOrganization(private val newItemList: List<OrganizationItems>) :
    RecyclerView.Adapter<ViewHolder>() {
    companion object {
        private const val VIEW_TYPE_DEAL_MIDDLE_ITEM = 2
        private const val VIEW_TYPE_DEAL_LAST_ITEM = 3
    }
    private val falseList = List(itemCount) { false }

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
        val parentTextViewBox:RelativeLayout
        val parentNewScreenButton:LinearLayout
        val expandButton:ImageView
        val childrenList = item.children!!
        var isExpandParent = falseList[position]
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

        if (position == 0){
            val param = viewVertical.layoutParams as ViewGroup.MarginLayoutParams
            param.setMargins(12,20,10,0)
            viewVertical.layoutParams = param
        }




        if (isExpandParent) {
            parentRV.visibility = View.GONE
            expandButton.setImageResource(R.drawable.baseline_add_24)
        } else {
            parentRV.visibility = View.VISIBLE
            expandButton.setImageResource(R.drawable.baseline_remove_24)
        }


        if (childrenList.isEmpty()) {
            expandButton.visibility = View.GONE
        } else {
            expandButton.visibility = View.VISIBLE

            parentRV.layoutManager = LinearLayoutManager(context)
            val adapter = GrandChildAdapterOrganization(childrenList)
            parentRV.adapter = adapter

            parentTextViewBox.setOnClickListener {
                isExpandParent = !isExpandParent
                if (isExpandParent) {
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
        val parentTextViewBox: RelativeLayout = itemView.findViewById(R.id.parent_title_text_view_box)
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
        val parentTextViewBox: RelativeLayout = itemView.findViewById(R.id.parent_title_text_view_box)
        val parentNewScreenButton: LinearLayout = itemView.findViewById(R.id.root_new_page_button)
        val parentRecyclerView: RecyclerView = itemView.findViewById(R.id.rvParent)
        val expandButton: ImageView = itemView.findViewById(R.id.ivExpandedRoot)
    }
}