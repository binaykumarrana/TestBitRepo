package com.binay.testbitrepo.domain

import com.binay.testbitrepo.common.Resource
import com.binay.testbitrepo.data.local.LocalDataSource
import com.binay.testbitrepo.data.model.RepoEntity
import com.binay.testbitrepo.data.model.Repository
import com.binay.testbitrepo.data.model.asRepoEntity
import com.binay.testbitrepo.data.remote.NetworkDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

/**

 *
 * Created by Binay on 23/5/21.
 */
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@ActivityRetainedScoped
class BitbucketRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource
) : BitbucketRepository {
    override suspend fun getRepos(arg: String?): Flow<Resource<List<Repository>>> = callbackFlow {
        networkDataSource.getRepos(arg).collectLatest {
            when (it) {
                is Resource.Success -> {
                    for (repo in it.data) {
                        saveRepo(repo.asRepoEntity())
                    }
                    offer(getCachedRepos())
                }
                is Resource.Failure -> {
                    offer(getCachedRepos())
                }
            }
        }

        awaitClose { cancel() }
    }

    override suspend fun getCachedRepos(): Resource<List<Repository>> {
        return localDataSource.getCachedRepos()
    }

    override suspend fun saveRepo(repoEntity: RepoEntity) {
        localDataSource.saveRepo(repoEntity)
    }
}