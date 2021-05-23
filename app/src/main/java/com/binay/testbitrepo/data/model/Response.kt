package com.binay.testbitrepo.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**

 *
 * Created by Binay on 23/5/21.
 */
@Parcelize
data class Repository(
    @SerializedName("updated_on")
    val updated_on: String = "",
    @SerializedName("fork_policy")
    val fork_policy: String = "",
    @SerializedName("full_name")
    val full_name: String = "",
    @SerializedName("language")
    val language: String = "",
    @SerializedName("is_private")
    val is_private: Boolean = false,
    @SerializedName("description")
    val description: String = "",
) : Parcelable

data class RepositoryList(
    @SerializedName("values")
    val repositoryList: List<Repository> = listOf(),
    @SerializedName("next")
    val next: String = ""
)

fun List<RepoEntity>.asRepoList(): List<Repository> = this.map {
    Repository(it.repoId, it.forkPolicy, it.name, it.language, it.isPrivate, it.description)
}

fun Repository.asRepoEntity(): RepoEntity =
    RepoEntity(
        this.updated_on,
        this.fork_policy,
        this.full_name,
        this.language,
        this.is_private,
        this.description
    )