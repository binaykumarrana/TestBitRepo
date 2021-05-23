package com.binay.testbitrepo.common

/**

 *
 * Created by Binay on 23/5/21.
 */
sealed class Resource<out T> {
    object Loading: Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure(val exception: Exception) : Resource<Nothing>()
}