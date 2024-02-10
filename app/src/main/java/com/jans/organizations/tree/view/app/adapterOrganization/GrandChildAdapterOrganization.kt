package com.jans.organizations.tree.view.app.adapterOrganization

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
import com.jans.organizations.tree.view.app.R
import com.jans.organizations.tree.view.app.InfoScreenOrganization
import com.jans.organizations.tree.view.app.modelOrganization.OrganizationItems

class GrandChildAdapterOrganization(private val newItemList: List<OrganizationItems>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val falseList = List(itemCount) { false }

    lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.grand_child_item_layout, parent, false)
               return ParentViewHolder(view)
    }
    class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.parentTitleTextView)
        val parentTextViewBox: RelativeLayout = itemView.findViewById(R.id.parent_title_text_view_box)
        val parentNewScreenButton: LinearLayout = itemView.findViewById(R.id.root_new_page_button)
        val parentRecyclerView: RecyclerView = itemView.findViewById(R.id.rvParent)
        val expandButton: ImageView = itemView.findViewById(R.id.ivExpandedRoot)

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = newItemList[position]

        context = holder.itemView.context

        when (holder) {
            is ParentViewHolder -> {
                holder.titleTextView.text = item.title
                val childrenList = item.children!!

                val parentRV = holder.parentRecyclerView
                var isExpandParent = falseList[position]
                val expandButton = holder.expandButton


                if(isExpandParent){
                    parentRV.visibility = View.GONE
                    expandButton.setImageResource(R.drawable.baseline_add_24)
                }
                else{
                    parentRV.visibility = View.VISIBLE
                    expandButton.setImageResource(R.drawable.baseline_remove_24)
                }


                if(childrenList.isEmpty()){
                    expandButton.visibility = View.GONE
                }
                else{
                    expandButton.visibility = View.VISIBLE

                    parentRV.layoutManager = LinearLayoutManager(context)
                    val adapter = GrandChildAdapterOrganization(childrenList)
                    parentRV.adapter = adapter



                    holder.parentTextViewBox.setOnClickListener{
                        isExpandParent = !isExpandParent
                        if(isExpandParent){
                            parentRV.visibility = View.GONE
                            expandButton.setImageResource(R.drawable.baseline_add_24)
                        }
                        else{
                            parentRV.visibility = View.VISIBLE
                            expandButton.setImageResource(R.drawable.baseline_remove_24)

                        }
                    }
                }


                holder.parentNewScreenButton.setOnClickListener{
                   context.startActivity(Intent(context, InfoScreenOrganization::class.java))
                }







            }
        }
    }

    override fun getItemCount(): Int {
        return newItemList.size
    }

}