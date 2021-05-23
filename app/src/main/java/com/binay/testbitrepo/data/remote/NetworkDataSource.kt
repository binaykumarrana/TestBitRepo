package com.binay.testbitrepo.data.remote

import com.binay.testbitrepo.common.AppConstants
import com.binay.testbitrepo.common.Resource
import com.binay.testbitrepo.data.model.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

/**

 *
 * Created by Binay on 23/5/21.
 */
@ExperimentalCoroutinesApi
class NetworkDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getRepos(arg: String?): Flow<Resource<List<Repository>>> =
        callbackFlow {
            offer(
                Resource.Success(
                    apiService.getRepositories(arg)?.repositoryList ?: listOf()
                )
            )
            AppConstants.NEXT_TEXT = apiService.getRepositories(arg)?.next
            awaitClose { close() }
        }
}