package com.jans.organizations.tree.view.app.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.jans.organizations.tree.view.app.R
import com.jans.organizations.tree.view.app.adapterOrganization.RootAdapterOrganization
import com.jans.organizations.tree.view.app.databinding.OrganizationScreenBinding
import com.jans.organizations.tree.view.app.modelOrganization.OrganizationItems
import com.jans.organizations.tree.view.app.modelOrganization.Root
import java.io.InputStreamReader


class OrganizationScreen : AppCompatActivity() {

    private lateinit var rvRootOrganization: RecyclerView
    private lateinit var b:OrganizationScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = OrganizationScreenBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnBack.setOnClickListener { finish() }
        rvRootOrganization = b.rootRvOrganization
        b.idLoader.visibility = View.VISIBLE
        b.rootRvOrganization.visibility = View.GONE
        Handler(Looper.getMainLooper()).postDelayed({
            b.idLoader.visibility = View.GONE
            b.rootRvOrganization.visibility = View.VISIBLE


            val layoutManager = LinearLayoutManager(this)
            layoutManager.isAutoMeasureEnabled = true
            rvRootOrganization.layoutManager = layoutManager
            val adapter = RootAdapterOrganization(rootItemListOrganization())
            rvRootOrganization.adapter = adapter

        },50)
    }

    private fun rootItemListOrganization(): List<OrganizationItems> {
        val gson = Gson()
        return gson.fromJson(gson.toJson(gson.fromJson(InputStreamReader(resources.openRawResource(R.raw.data2)).readText(),
            Root::class.java).items.root), Array<OrganizationItems>::class.java).toList()
    }


}