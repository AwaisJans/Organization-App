package com.jans.organizations.tree.view.app.adapter

import android.annotation.SuppressLint
import android.content.Context
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
import com.jans.organizations.tree.view.app.models.Child


class GrandChildrenAdapter(private val products: List<Child>) :
    RecyclerView.Adapter<GrandChildrenAdapter.ParentViewHolder>() {
    private var isRV = false
    private lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.grand_child_item,
                parent, false
            )
        return ParentViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        holder.titleTextView.text = products[position].title
        val isExpanded = products[position].expanded
        val children = products[position].children

        val relView = holder.relView
        context = holder.itemView.context
        val dashView = holder.itemView.findViewById<LinearLayout>(R.id.v)
//        val linearLayout = holder.itemView.findViewById<LinearLayout>(R.id.lin1)

        setUpRV(holder,children)

        if (isExpanded) {
            holder.imgExpanded.visibility = View.VISIBLE
            relView.setOnClickListener {

                isRV = !isRV
                if (isRV) {
                    dashView.visibility = View.INVISIBLE
                    val param = holder.relChildren.layoutParams as ViewGroup.MarginLayoutParams
                    param.setMargins(10,0,0,0)
                    holder.relChildren.layoutParams = param
                    holder.relChildren.visibility = View.VISIBLE
                    holder.imgExpanded.setImageResource(R.drawable.baseline_remove_24)
                } else {
                    holder.relChildren.visibility = View.GONE
                    dashView.visibility = View.VISIBLE
                    holder.imgExpanded.setImageResource(R.drawable.baseline_add_24)
                    notifyItemChanged(position)
                }

            }


        } else {
            holder.imgExpanded.visibility = View.GONE

        }


    }

    private fun setUpRV(holder: ParentViewHolder,children: List<Child>){
        holder.rvChildrenList.layoutManager =
            LinearLayoutManager(context)
        val adapter = GrandChildrenAdapter(children)
        holder.rvChildrenList.adapter = adapter
    }



    override fun getItemCount(): Int = products.size

    class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //        var v1 = itemView.findViewById(com.jans.organizations.tree.view.app.R.id.viewS) as TextView
        var titleTextView = itemView.findViewById(R.id.titleTextView) as TextView
        var imgExpanded = itemView.findViewById(R.id.ivExpanded) as ImageView
        var relView = itemView.findViewById(R.id.relView) as RelativeLayout

        var relChildren = itemView.findViewById(R.id.relChild) as LinearLayout
        var rvChildrenList = itemView.findViewById(R.id.rvChildrenSublist) as RecyclerView
    }


}