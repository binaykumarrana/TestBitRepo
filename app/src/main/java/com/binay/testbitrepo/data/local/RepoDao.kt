package com.binay.testbitrepo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.binay.testbitrepo.data.model.RepoEntity

/**
 * Created by Binay on 23/5/21.
 */
@Dao
interface RepoDao {
    @Query("SELECT * FROM repoTable")
    suspend fun getRepositories(): List<RepoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRepo(repoEntity: RepoEntity)
}