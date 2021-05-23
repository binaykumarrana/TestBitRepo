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
    val cocktailList: List<Repository> = listOf(),
    @SerializedName("next")
    val next: String = ""
)