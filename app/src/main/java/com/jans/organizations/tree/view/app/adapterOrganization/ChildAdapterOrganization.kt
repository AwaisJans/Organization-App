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
import com.jans.organizations.tree.view.app.InfoScreenOrganization
import com.jans.organizations.tree.view.app.R
import com.jans.organizations.tree.view.app.modelOrganization.OrganizationItems


class ChildAdapterOrganization(private val newItemList: List<OrganizationItems>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val falseList = List(itemCount) { false }


    var height = 0

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.child_item_layout, parent, false)
        return ParentViewHolder(view)
    }

    class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.parentTitleTextView)
        val parentTextViewBox: RelativeLayout =
            itemView.findViewById(R.id.parent_title_text_view_box)
        val itemCardChild: LinearLayout = itemView.findViewById(R.id.itemCardChild)
        val parentNewScreenButton: LinearLayout = itemView.findViewById(R.id.root_new_page_button)
        val halfLine1: LinearLayout = itemView.findViewById(R.id.halfLine1)
        val halfLine2: LinearLayout = itemView.findViewById(R.id.halfLine2)
        val parentRecyclerView: RecyclerView = itemView.findViewById(R.id.rvParent)
        val expandButton: ImageView = itemView.findViewById(R.id.ivExpandedRoot)
    }

    private var deviceWidth: Int = 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = newItemList[position]

        context = holder.itemView.context
        val listHeightMainItemView = ArrayList<Int>()
        val listHeightRV = ArrayList<Int>()
        when (holder) {
            is ParentViewHolder -> {
                holder.titleTextView.text = item.title
                val childrenList = item.children!!
                val parentRV = holder.parentRecyclerView
                var isExpandParent = falseList[position]
                val expandButton = holder.expandButton
                val halfLine1 = holder.halfLine1
                val halfLine2 = holder.halfLine2
                val itemCardChild = holder.itemCardChild


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
//                    val adapter = GrandChildAdapterOrganization(childrenList)
//                    parentRV.adapter = adapter


                    holder.parentTextViewBox.setOnClickListener {
                        isExpandParent = !isExpandParent
                        if (isExpandParent) {
                            parentRV.visibility = View.GONE
                            halfLine1.layoutParams.height = (listHeightMainItemView[position]/2) - listHeightRV[position]
                            halfLine2.layoutParams.height = (listHeightMainItemView[position]/2) - listHeightRV[position]
                            expandButton.setImageResource(R.drawable.baseline_add_24)
                        } else {
                            parentRV.visibility = View.VISIBLE
                            expandButton.setImageResource(R.drawable.baseline_remove_24)
                        }

//                        val list1 = ArrayList<Int>()
//                        val display = context.getSystemService<DisplayManager>()?.getDisplay(Display.DEFAULT_DISPLAY)
//                        val deviceWidth = display!!.width
//                        for (i in 0 until itemCount) {
//                            itemCardChild.let {
//                                val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(deviceWidth, View.MeasureSpec.AT_MOST)
//                                val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
//                                it.measure(widthMeasureSpec, heightMeasureSpec)
//                                list1.add(it.measuredHeight)
//
//                            }
//                            Log.d("tsg123", list1.toString())
//
//
//                            if(position == itemCount - 1){
//                                halfLine1.layoutParams.height = (list1[i]/2)
//                                halfLine2.layoutParams.height = 0
//                            }
//                            else{
//                                halfLine1.layoutParams.height = (list1[i]/2)
//                                halfLine2.layoutParams.height = (list1[i]/2)
//                            }
//                        }


                    }
                }



                val display =
                    context.getSystemService<DisplayManager>()?.getDisplay(Display.DEFAULT_DISPLAY)
                val deviceWidth = display!!.width // Get the width of the display
                for (i in 0 until itemCount) {
                    // Measure the height of the view at the current position
                    itemCardChild.let {
                        val widthMeasureSpec =
                            View.MeasureSpec.makeMeasureSpec(deviceWidth, View.MeasureSpec.AT_MOST)
                        val heightMeasureSpec =
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                        it.measure(widthMeasureSpec, heightMeasureSpec)
                        listHeightMainItemView.add(it.measuredHeight)

                    }


//                    parentRV.let {
//                        val widthMeasureSpec =
//                            View.MeasureSpec.makeMeasureSpec(deviceWidth, View.MeasureSpec.AT_MOST)
//                        val heightMeasureSpec =
//                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
//                        it.measure(widthMeasureSpec, heightMeasureSpec)
//                        listHeightRV.add(it.measuredHeight)
//
//                    }


                    Log.d("tsg123", listHeightMainItemView.toString())


                    if(position == itemCount - 1){
                        halfLine1.layoutParams.height = (listHeightMainItemView[i]/2)
//                        halfLine2.layoutParams.height = 0
                        halfLine2.layoutParams.height = 6.toInt()
                    }
                    else{
                        halfLine1.layoutParams.height = (listHeightMainItemView[i]/2)
                        halfLine2.layoutParams.height = (listHeightMainItemView[i]/2)
                    }
                }



//                android.os.Handler().postDelayed({

//                val display = context.getSystemService<DisplayManager>()?.getDisplay(Display.DEFAULT_DISPLAY)
//                deviceWidth = display!!.width
//                val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(deviceWidth, View.MeasureSpec.AT_MOST)
//                val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
//                itemCardChild.measure(widthMeasureSpec, heightMeasureSpec)
//
//                list.add(itemCardChild.measuredHeight)

//                Log.d("tsg123", list.toString())


//                    Log.d("myList12",list.size.toString())

//
//                },2000)


                holder.parentNewScreenButton.setOnClickListener {
                    context.startActivity(Intent(context, InfoScreenOrganization::class.java))
                }


            }
        }
    }

    override fun getItemCount(): Int {
        return newItemList.size
    }

}