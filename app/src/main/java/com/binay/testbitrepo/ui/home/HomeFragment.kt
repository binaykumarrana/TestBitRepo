package com.binay.testbitrepo.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.binay.testbitrepo.R
import com.binay.testbitrepo.data.model.Repository
import com.binay.testbitrepo.databinding.HomeFragmentBinding
import com.binay.testbitrepo.presentation.OnRepoItemClick
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repoAdapter = RepoAdapter(repositoryList, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HomeFragmentBinding.bind(view)
        binding.rvRepository.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRepository.adapter = repoAdapter

        //Next click, only enabled when next it available
        binding.btnNext.setOnClickListener {

        }
    }

    override fun onItemClick(repo: Repository) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                repo
            )
        )
    }
}