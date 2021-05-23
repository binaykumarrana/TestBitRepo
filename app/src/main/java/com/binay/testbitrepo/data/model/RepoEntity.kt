package com.binay.testbitrepo.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**

 * Created by Binay on 23/5/21.
 */
@Entity(tableName = "repoTable")
data class RepoEntity(
    @PrimaryKey
    val repoId: String,
    @ColumnInfo(name = "fork_policy")
    val forkPolicy: String = "",
    @ColumnInfo(name = "full_name")
    val name: String = "",
    @ColumnInfo(name = "language")
    val language: String = "",
    @ColumnInfo(name = "is_private")
    val isPrivate: Boolean = false,
    @ColumnInfo(name = "description")
    val description: String = ""
)