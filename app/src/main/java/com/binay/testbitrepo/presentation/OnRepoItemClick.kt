package com.binay.testbitrepo.presentation

import com.binay.testbitrepo.data.model.Repository

/**

 * Created by Binay on 23/5/21.
 */
interface OnRepoItemClick {
    fun onItemClick(repo: Repository)
}