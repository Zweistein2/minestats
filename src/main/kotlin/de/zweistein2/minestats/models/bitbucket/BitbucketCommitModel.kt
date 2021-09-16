package de.zweistein2.minestats.models.bitbucket

import java.time.LocalDateTime

data class BitbucketCommitModel(
    val id: String,
    val displayId: String,
    val author: BitbucketAuthorModel,
    val authorTimestamp: LocalDateTime,
    val committer: BitbucketAuthorModel,
    val committerTimestamp: LocalDateTime,
    val message: String,
    val parents: Array<BitbucketCommitParentModel>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BitbucketCommitModel

        if (id != other.id) return false
        if (displayId != other.displayId) return false
        if (author != other.author) return false
        if (authorTimestamp != other.authorTimestamp) return false
        if (committer != other.committer) return false
        if (committerTimestamp != other.committerTimestamp) return false
        if (message != other.message) return false
        if (!parents.contentEquals(other.parents)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + displayId.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + authorTimestamp.hashCode()
        result = 31 * result + committer.hashCode()
        result = 31 * result + committerTimestamp.hashCode()
        result = 31 * result + message.hashCode()
        result = 31 * result + parents.contentHashCode()
        return result
    }
}