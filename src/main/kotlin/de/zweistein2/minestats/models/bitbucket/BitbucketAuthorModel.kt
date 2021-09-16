package de.zweistein2.minestats.models.bitbucket

data class BitbucketAuthorModel(
    val name: String,
    val emailAddress: String,
    var hash: String,
    val id: Int,
    val displayName: String,
    val active: Boolean,
    val slug: String,
    val type: String,
)