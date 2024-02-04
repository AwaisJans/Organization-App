package com.jans.organizations.tree.view.app.utils

import android.util.Log
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException



class GetDataUsingApiUtil{
    private fun getResults(jsonData: String) {
        try {
            val gson = Gson()
            val data = gson.fromJson(jsonData, Data::class.java)
            // Accessing items
            val itemsMap = data.items
            for ((key, item) in itemsMap) {
//                Log.d("Item123", "Key: $key\n Item: $item")
                // Access other properties of the item as needed


//            rvAdapter = MainActivity.TreeViewAdapter(itemsMap)
//            recyclerView.layoutManager = LinearLayoutManager(this)
//            recyclerView.adapter = rvAdapter




                Log.d(
                    "Item123", "---------------------------------\n" +
                            "Title: ${item.title}\n" +
                            "URL: ${item.url}\n" +
                            "UID: ${item.uid}\n" +
                            "URLType: ${item.urlType}\n"
                )


                // Accessing children
                val childrenMap = item.children
                for ((childKey, childItem) in childrenMap) {
//                    Log.d("ChildItem", "Key: $childKey, Item: $childItem")
                    // Access other properties of the child item as needed
                    Log.d(
                        "Item123", "----------------------------------------\n" +
                                "Parent - ChildrenList\nTitle: ${childItem.title}\n" +
                                " URL: ${childItem.url}\n" +
                                "UID: ${childItem.uid}\n" +
                                "URLType: ${childItem.urlType}\n"
                    )


//                    val childrenMap1 = childItem.children
//                    for ((childKey1, childItem1) in childrenMap1) {
////                    Log.d("ChildItem", "Key: $childKey, Item: $childItem")
//                        // Access other properties of the child item as needed
//                        Log.d("Item123", "----------------------------------------\n" +
//                                "Child - ChildrenList\nTitle: ${childItem1.title}\n" +
//                                " URL: ${childItem1.url}\n" +
//                                "UID: ${childItem1.uid}\n" +
//                                "URLType: ${childItem1.urlType}\n"+
//                                "Children: ${childItem1.children}\n")
//                    }


                }


            }
        } catch (e: Exception) {
            Log.e("JsonData", "Error parsing JSON: ${e.message}")
        }
    }
    data class Data(
        val items: Map<String, Item>,
        val status: String
    )
    data class Item(
        val uid: Int,
        val title: String,
        val url: String,
        val urlType: String,
        val children: Map<String, Item>
    )

    /*
    Main Activity Code


     fetchJsonData {
                getResults(it!!)
            }

     */



    private fun fetchJsonData(callback: (String?) -> Unit) {
        Thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("https://test-1.hedwig2-hirsch-woelfl.de/app-connection?action=getSBWOrganisationItems")
                    .build()

                val response: Response = client.newCall(request).execute()
                val jsonData: String? = response.body?.string()
                callback(jsonData)
            } catch (e: IOException) {
                e.printStackTrace()
                callback(null)
            }
        }.start()
    }
}

