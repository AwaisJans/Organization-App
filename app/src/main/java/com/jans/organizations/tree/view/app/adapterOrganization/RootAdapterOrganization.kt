package com.jans.organizations.tree.view.app.adapterOrganization

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jans.organizations.tree.view.app.R
import com.jans.organizations.tree.view.app.activities.InfoScreenOrganization
import com.jans.organizations.tree.view.app.modelOrganization.OrganizationItems

class RootAdapterOrganization(private val rootItemList: List<OrganizationItems>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var contextRootAdapter:Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.root_item_layout, parent, false)
        return RootViewHolder(view)
    }
    class RootViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextViewRoot: TextView = itemView.findViewById(R.id.rootTitleTextView)
        val expandButtonRoot: ImageView = itemView.findViewById(R.id.ivExpandedRoot)
        val rootTextViewBox: RelativeLayout = itemView.findViewById(R.id.root_title_text_view_box)
        val newScreenButtonRoot: LinearLayout = itemView.findViewById(R.id.root_new_page_button)
        val parentRecyclerView: RecyclerView = itemView.findViewById(R.id.rvParent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemRoot = rootItemList[position]
        contextRootAdapter = holder.itemView.context

        when (holder) {
            is RootViewHolder -> {
                // defining some variables to use without holder
                holder.titleTextViewRoot.text = itemRoot.title
                val parentList = itemRoot.children!!
                val expandButtonRoot = holder.expandButtonRoot
                val parentRV = holder.parentRecyclerView


                // check collapse value from Json
                var collapse = itemRoot.collapse
                if(!collapse){
                    parentRV.visibility = GONE
                    expandButtonRoot.setImageResource(R.drawable.baseline_add_24)
                }
                else{
                    parentRV.visibility = VISIBLE
                    parentRV.layoutManager = LinearLayoutManager(contextRootAdapter)
                    parentRV.adapter = ParentAdapterOrganization(parentList!!)
                    expandButtonRoot.setImageResource(R.drawable.baseline_remove_24)
                }

                // check if parent item is empty or not
                if(parentList.isEmpty()){
                    expandButtonRoot.visibility = GONE
                }
                else{
                    // if not empty then make click listener activate
                    expandButtonRoot.visibility = VISIBLE
                    holder.rootTextViewBox.setOnClickListener{
                        collapse = !collapse
                        // code to collapse or expand item
                        if(!collapse){
                            parentRV.visibility = GONE
                            expandButtonRoot.setImageResource(R.drawable.baseline_add_24)
                        }
                        else{
                            parentRV.visibility = VISIBLE
                            expandButtonRoot.setImageResource(R.drawable.baseline_remove_24)
                        }
                    }
                }

                // code to go on the next screen
                holder.newScreenButtonRoot.setOnClickListener{
                   contextRootAdapter.startActivity(Intent(contextRootAdapter, InfoScreenOrganization::class.java))
                }

            }
        }
    }
    override fun getItemCount(): Int {
        return rootItemList.size
    }
}