package com.jans.organizations.tree.view.app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.marginEnd
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jans.organizations.tree.view.app.R
import com.jans.organizations.tree.view.app.models.Child

class ChildrenAdapter(private val products: List<Child>) :
            RecyclerView.Adapter<ChildrenAdapter.ChildrenViewHolder>() {
            private var isRV = false

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

                val isExpanded = products[position].expanded
                val children = products[position].children

                if (isExpanded) {
                    holder.imgExpanded.visibility = View.VISIBLE

                    holder.itemView.setOnClickListener{

                        isRV = !isRV
                        if(isRV){
//                            holder.v1.visibility= View.INVISIBLE
                            holder.rvChildrenList.visibility = View.VISIBLE

                            holder.imgExpanded.setImageResource(R.drawable.baseline_remove_24)
                            holder.rvChildrenList.layoutManager =
                                LinearLayoutManager(holder.itemView.context)
                            val adapter = ChildrenAdapter(children)
                            holder.rvChildrenList.adapter = adapter
                        }
                        else{
//                            holder.v1.visibility= View.VISIBLE

                            holder.rvChildrenList.visibility = View.GONE
                            holder.imgExpanded.setImageResource(R.drawable.baseline_add_24)
                            notifyItemChanged(position)
                        }

                    }

                } else {
                    holder.imgExpanded.visibility = View.GONE
                }


            }

            override fun getItemCount(): Int = products.size

            class ChildrenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//                var v1 = itemView.findViewById(R.id.view1) as View

                var titleTextView = itemView.findViewById(R.id.titleTextView) as TextView
                var imgExpanded = itemView.findViewById(R.id.ivExpanded) as ImageView
                var rvChildrenList = itemView.findViewById(R.id.rvChildrenSublist) as RecyclerView
            }


        }