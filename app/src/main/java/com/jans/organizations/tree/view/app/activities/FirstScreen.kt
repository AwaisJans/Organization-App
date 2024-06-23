package com.jans.organizations.tree.view.app.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jans.organizations.tree.view.app.databinding.ActivityFirstScreenBinding

class FirstScreen : AppCompatActivity() {

    private lateinit var b: ActivityFirstScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnOrganization.setOnClickListener {
            startActivity(Intent(this, OrganizationScreen::class.java))
        }

    }


}