package com.binay.testbitrepo.presentation

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.binay.testbitrepo.common.AppConstants.PAGE_NUM
import com.binay.testbitrepo.common.Resource
import com.binay.testbitrepo.domain.BitbucketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

/**

 *
 * Created by Binay on 23/5/21.
 */
class RepoViewModel @ViewModelInject constructor(
    private val repository: BitbucketRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val pageNumber = savedStateHandle.getLiveData<String>(PAGE_NUM, "1")

    fun fetchRepositoryList(arg: String?) = pageNumber.distinctUntilChanged().switchMap {
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading)
            try {
                repository.getRepos(arg).collectLatest {
                    emit(it)
                }
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }

    fun setPageNum(pageNum: Int) {
        pageNumber.value = pageNum.toString()
    }
}