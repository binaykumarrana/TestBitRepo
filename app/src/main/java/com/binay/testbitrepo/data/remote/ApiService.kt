package com.binay.testbitrepo.data.remote

import com.binay.testbitrepo.data.model.RepositoryList
import retrofit2.http.GET
import retrofit2.http.Query

/**

 * Created by Binay on 23/5/21.
 */
interface ApiService {
    @GET("repositories?")
    suspend fun getRepositories(@Query("after") arg: String?): RepositoryList?
}