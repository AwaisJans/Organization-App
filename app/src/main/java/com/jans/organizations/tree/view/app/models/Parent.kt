package com.jans.organizations.tree.view.app.models

data class Parent(
    val id: Int,
    val title: String,
    val url: String,
    val urlType: String,
    val expanded: Boolean,
    val children: List<Child>
)



