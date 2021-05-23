package com.binay.testbitrepo.domain

import com.binay.testbitrepo.common.Resource
import com.binay.testbitrepo.data.model.RepoEntity
import com.binay.testbitrepo.data.model.Repository
import kotlinx.coroutines.flow.Flow

/**

 *
 * Created by Binay on 23/5/21.
 */
interface BitbucketRepository {
    suspend fun getRepos(arg: String?): Flow<Resource<List<Repository>>>
    suspend fun getCachedRepos(): Resource<List<Repository>>
    suspend fun saveRepo(repoEntity: RepoEntity)
}