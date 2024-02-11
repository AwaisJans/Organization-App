package com.jans.organizations.tree.view.app.futureWork

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
import com.jans.organizations.tree.view.app.utilOrganization.SharedPreferencesManager

class AncestorEightAdapterOrganization(private val newItemList: List<OrganizationItems>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val falseList = List(itemCount) { false }

    lateinit var context:Context
    lateinit var sharedPref:SharedPreferencesManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.grand_child_item_layout_start, parent, false)
               return ParentViewHolder(view)
    }
    class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.parentTitleTextView)
        val parentTextViewBox: RelativeLayout = itemView.findViewById(R.id.parent_title_text_view_box)
        val grandChildItemView: RelativeLayout = itemView.findViewById(R.id.grand_child_box_item_view)
        val parentNewScreenButton: LinearLayout = itemView.findViewById(R.id.root_new_page_button)
        val view1: LinearLayout = itemView.findViewById(R.id.view1)
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
                val grandChildItemView = holder.grandChildItemView
                val v1 = holder.view1

                sharedPref = SharedPreferencesManager.getInstance(context)!!



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

                    val parentRVLayoutManager = LinearLayoutManager(context)
                    parentRV.layoutManager = parentRVLayoutManager
                    val adapter = AncestorEightAdapterOrganization(childrenList)
                    parentRV.adapter = adapter


                    if (position == itemCount - 1){


//                        v1.layoutParams.height = 400


//                        grandChildItemView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
//                            override fun onGlobalLayout() {
//                                // Remove the listener to prevent multiple calls
//                                grandChildItemView.viewTreeObserver.removeOnGlobalLayoutListener(this)
//
//                                // Get the height of the LinearLayout
//                                val heightItemView = grandChildItemView.height / 2
//
//                                v1.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
//                                    override fun onGlobalLayout() {
//                                        // Remove the listener to prevent multiple calls
//                                        v1.viewTreeObserver.removeOnGlobalLayoutListener(this)
//
//                                        // Get the height of the LinearLayout
//                                        val heightLine = v1.height - heightItemView
//
//                                        val newLine = heightLine - 175
//
//                                        sharedPref.putString("mystring",newLine.toString())
//
////                                        val newLastLine = heightLine - heightItemView
////                                      Handler().postDelayed(
////                                          {
////                                              v1.layoutParams.height = newLine
////                                          },200)
//                                    }
//                                })
//                            }
//                        })

//                        Log.d("merastring1234",sharedPref.getString("mystring","")!!)


                    }







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