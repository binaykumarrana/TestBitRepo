package com.binay.testbitrepo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.binay.testbitrepo.data.local.AppDatabase
import com.binay.testbitrepo.data.model.RepoEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**

 * Created by Binay on 23/5/21.
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class AppDatabaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        )
            .allowMainThreadQueries().build()

    }

    @ExperimentalCoroutinesApi
    @Test
    fun testIsDatabaseNotOpen() {
        assertThat(database.isOpen).isFalse()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testIsDatabaseOpen() = runBlockingTest {
        executeDatabaseFunction()
        assertThat(database.isOpen).isTrue()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testDatabaseVersionIsCurrent() = runBlockingTest {
        executeDatabaseFunction()
        assertThat(database.openHelper.readableDatabase.version).isEqualTo(3)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testDatabasePathIsMemory() = runBlockingTest {
        executeDatabaseFunction()
        assertThat(database.openHelper.readableDatabase.path).isEqualTo(":memory:")
    }

    @After
    fun tearDown() {
        database.close()
    }

    @ExperimentalCoroutinesApi
    private fun executeDatabaseFunction() = runBlockingTest {
        val repoEntity = RepoEntity(
            "2014-11-16T23:19:16.674082+00:00",
            "allow_forks",
            "opensymphony/xwork", "Java",
            false,
            ""
        )
        database.repositoryDao().saveRepo(repoEntity)
    }
}