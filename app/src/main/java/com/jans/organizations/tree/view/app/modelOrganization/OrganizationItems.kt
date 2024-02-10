package com.jans.organizations.tree.view.app.modelOrganization

data class OrganizationItems(
    val id: Int,
    val title: String,
    val url: String,
    val urlType: String,
    val children: List<OrganizationItems>?
)