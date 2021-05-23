package com.binay.testbitrepo.di

import com.binay.testbitrepo.domain.BitbucketRepository
import com.binay.testbitrepo.domain.BitbucketRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

/**

 * Created by Binay on 23/5/21.
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {
    @ExperimentalCoroutinesApi
    @InternalCoroutinesApi
    @Binds
    abstract fun dataSource(impl: BitbucketRepositoryImpl): BitbucketRepository
}