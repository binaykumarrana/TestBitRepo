package com.binay.testbitrepo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.binay.testbitrepo.data.local.AppDatabase
import com.binay.testbitrepo.data.local.RepoDao
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
 * Copyright 2021 Payed Pvt. Ltd.
 *
 * Created by Binay on 23/5/21.
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class RepoDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: RepoDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        )
            .allowMainThreadQueries().build()
        dao = database.repositoryDao()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetRepos() = runBlockingTest {

        val repoEntity = RepoEntity(
            "2014-11-16T23:19:16.674082+00:00",
            "allow_forks",
            "opensymphony/xwork", "Java",
            false,
            ""
        )
        dao.saveRepo(repoEntity)

        val repos = dao.getRepositories()

        assertThat(repos[0]).isNotNull()
        assertThat(repos[0]).isEqualTo(repoEntity)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testSaveRepo() = runBlockingTest {
        val repoEntity = RepoEntity(
            "2014-11-16T23:19:16.674082+00:00",
            "allow_forks",
            "opensymphony/xwork", "Java",
            false,
            ""
        )
        dao.saveRepo(repoEntity)

        val repos = dao.getRepositories()
        assertThat(repos).isNotEmpty()
    }


    @After
    fun tearDown() {
        database.close()
    }
}