package com.binay.testbitrepo.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.binay.testbitrepo.R
import com.binay.testbitrepo.common.AppConstants
import com.binay.testbitrepo.common.Resource
import com.binay.testbitrepo.data.model.Repository
import com.binay.testbitrepo.databinding.HomeFragmentBinding
import com.binay.testbitrepo.extention.hide
import com.binay.testbitrepo.extention.show
import com.binay.testbitrepo.presentation.OnRepoItemClick
import com.binay.testbitrepo.presentation.RepoViewModel
import com.binay.testbitrepo.ui.adapter.RepoAdapter
import dagger.hilt.android.AndroidEntryPoint

/**

 *
 * Created by Binay on 23/5/21.
 */
@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment), OnRepoItemClick {
    private lateinit var binding: HomeFragmentBinding
    private val repositoryList = arrayListOf<Repository>()
    private lateinit var repoAdapter: RepoAdapter
    private var pageNum: Int = 1
    private val viewModel by activityViewModels<RepoViewModel>()
    private var isDetailClicked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repoAdapter = RepoAdapter(repositoryList, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HomeFragmentBinding.bind(view)
        binding.rvRepository.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRepository.adapter = repoAdapter
        if (!isDetailClicked)
            fetchRepo(AppConstants.NEXT_TEXT)
        //Next click, only enabled when next it available
        binding.btnNext.setOnClickListener {
            pageNum += 1
            viewModel.setPageNum(pageNum)
            fetchRepo(AppConstants.NEXT_TEXT)
        }
    }

    private fun fetchRepo(apiArguments: String?) {
        viewModel.fetchRepositoryList(apiArguments).observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.show()
                }
                is Resource.Success -> {
                    if (result.data.isEmpty()) {
                        binding.progressBar.hide()
                        binding.tvNoResult.show()
                        binding.btnNext.hide()
                        return@Observer
                    }
                    repositoryList.addAll(result.data)
                    repoAdapter.notifyDataSetChanged()
                    binding.tvNoResult.hide()
                    binding.progressBar.hide()
                    binding.btnNext.show()
                }
                is Resource.Failure -> {
                    if (repositoryList.isEmpty())
                        binding.tvNoResult.show()
                    binding.progressBar.hide()
                }
            }
        })
    }

    override fun onItemClick(repo: Repository) {
        isDetailClicked = true
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                repo
            )
        )
    }
}