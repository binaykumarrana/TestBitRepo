package com.binay.testbitrepo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.binay.testbitrepo.data.model.RepoEntity

/**

 *
 * Created by Binay on 23/5/21.
 */
@Database(entities = [RepoEntity::class],version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repositoryDao(): RepoDao
}