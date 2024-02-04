package com.jans.organizations.tree.view.app.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.marginEnd
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jans.organizations.tree.view.app.R
import com.jans.organizations.tree.view.app.models.Child

class ChildrenAdapter(private val products: List<Child>) :
    RecyclerView.Adapter<ChildrenAdapter.ChildrenViewHolder>() {
    private var isRV = false
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildrenViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.child_item,
                parent, false
            )
        return ChildrenViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ChildrenViewHolder, position: Int) {
        holder.titleTextView.text = products[position].title
        context = holder.itemView.context

        val isExpanded = products[position].expanded
        val children = products[position].children
        val relView = holder.relView
        val dashView = holder.itemView.findViewById<LinearLayout>(R.id.v)
        val myRV = holder.itemView.findViewById<LinearLayout>(R.id.relChild1)
        setUpRV(holder,children)
        if (isExpanded) {
            holder.imgExpanded.visibility = VISIBLE
            relView.setOnClickListener {
                isRV = !isRV
                if (isRV) {
                    dashView.visibility = INVISIBLE
                    myRV.visibility = VISIBLE
                    holder.imgExpanded.setImageResource(R.drawable.baseline_remove_24)
                } else {
                    myRV.visibility = GONE
                    dashView.visibility = VISIBLE
                    holder.imgExpanded.setImageResource(R.drawable.baseline_add_24)
                    notifyItemChanged(position)
                }
            }

        } else {
            holder.imgExpanded.visibility = GONE
        }


    }
    private fun setUpRV(holder: ChildrenViewHolder, children: List<Child>){
        holder.rvChildrenList.layoutManager =
            LinearLayoutManager(context)
        val adapter = GrandChildrenAdapter(children)
        holder.rvChildrenList.adapter = adapter
    }
    override fun getItemCount(): Int = products.size

    class ChildrenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //                var v1 = itemView.findViewById(R.id.view1) as View
        var relView = itemView.findViewById(R.id.relChild) as RelativeLayout

        var titleTextView = itemView.findViewById(R.id.titleTextView) as TextView
        var imgExpanded = itemView.findViewById(R.id.ivExpanded) as ImageView
        var rvChildrenList = itemView.findViewById(R.id.rvChildrenSublist) as RecyclerView
    }


}