package com.binay.testbitrepo.data.local

import com.binay.testbitrepo.common.Resource
import com.binay.testbitrepo.data.model.RepoEntity
import com.binay.testbitrepo.data.model.Repository
import com.binay.testbitrepo.data.model.asRepoList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/**

 * Created by Binay on 23/5/21.
 */
@ExperimentalCoroutinesApi
class LocalDataSource @Inject constructor(private val repoDao: RepoDao) {
    suspend fun getCachedRepos(): Resource<List<Repository>> {
        return Resource.Success(repoDao.getRepositories().asRepoList())
    }

    suspend fun saveRepo(repoEntity: RepoEntity) {
        repoDao.saveRepo(repoEntity)
    }
}