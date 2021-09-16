package de.zweistein2.minestats.models.bitbucket

data class BitbucketResponseModel(
    val values: Array<BitbucketCommitModel>,
    val size: Int,
    val isLastPage: Boolean,
    val start: Int,
    val limit: Int,
    val nextPageStart: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BitbucketResponseModel

        if (!values.contentEquals(other.values)) return false
        if (size != other.size) return false
        if (isLastPage != other.isLastPage) return false
        if (start != other.start) return false
        if (limit != other.limit) return false
        if (nextPageStart != other.nextPageStart) return false

        return true
    }

    override fun hashCode(): Int {
        var result = values.contentHashCode()
        result = 31 * result + size
        result = 31 * result + isLastPage.hashCode()
        result = 31 * result + start
        result = 31 * result + limit
        result = 31 * result + nextPageStart
        return result
    }
}