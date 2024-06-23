package com.jans.organizations.tree.view.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.jans.organizations.tree.view.app.adapterOrganization.RootAdapterOrganization
import com.jans.organizations.tree.view.app.modelOrganization.OrganizationItems
import org.json.JSONObject
import java.io.InputStreamReader


class FirstScreenOrganization : AppCompatActivity() {

    //

    private lateinit var rvRootOrganization: RecyclerView
    private lateinit var rootItemListOrganization: List<OrganizationItems>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_screen_organization)
        rvRootOrganization = findViewById(R.id.root_rv_organization)

        rootItemListOrganization = Gson().fromJson(organizationRootArray(), Array<OrganizationItems>::class.java).toList()

        rvRootOrganization.layoutManager = LinearLayoutManager(this)
        val adapter = RootAdapterOrganization(rootItemListOrganization)
        rvRootOrganization.adapter = adapter


    }

    private fun getResultsFromRawJson(): InputStreamReader {
        val inputStream = resources.openRawResource(R.raw.data2)
        return InputStreamReader(inputStream)
    }

    private fun organizationRootArray(): String {
        val jsonStringOrganization = JSONObject(getResultsFromRawJson().readText())
        val itemObjectOrganization = jsonStringOrganization.getJSONObject("items")
        return itemObjectOrganization.getJSONArray("root").toString()
    }






}