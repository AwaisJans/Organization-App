package com.jans.organizations.tree.view.app

import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.jans.organizations.tree.view.app.adapter.ParentAdapter
import com.jans.organizations.tree.view.app.models.Item
import com.jans.organizations.tree.view.app.models.Items
import com.jans.organizations.tree.view.app.models.Parent
import java.io.InputStreamReader


class MainActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.my_recycler_view)
        try {
            val jsonString: Item = Gson().fromJson(getResultsFromRawJson(), Item::class.java)
            val items: Items = jsonString.items
            val parents: List<Parent> = items.Parents
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = ParentAdapter(parents)
            recyclerView.hasFixedSize()
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
            Log.e("MainActivity", "Error parsing JSON data: ${e.message}")
        }

    }

    private fun getResultsFromRawJson(): InputStreamReader {
        val inputStream = resources.openRawResource(R.raw.data)
        return InputStreamReader(inputStream)
    }




}