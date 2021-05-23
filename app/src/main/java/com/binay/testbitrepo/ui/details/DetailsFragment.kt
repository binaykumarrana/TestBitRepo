package com.binay.testbitrepo.ui.details

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.Fragment
import com.binay.testbitrepo.R
import com.binay.testbitrepo.data.model.Repository
import com.binay.testbitrepo.databinding.DetailsFragmentBinding
import com.binay.testbitrepo.extention.hide
import com.binay.testbitrepo.extention.show

/**

 *
 * Created by Binay on 23/5/21.
 */
class DetailsFragment : Fragment(R.layout.details_fragment) {
    private lateinit var repository: Repository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            DetailsFragmentArgs.fromBundle(it).also { args ->
                repository = args.repo
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = DetailsFragmentBinding.bind(view)
        binding.tvName.text = "Name: ${repository.full_name}"
        binding.tvForkpolicy.text = "Fork Policy: ${repository.fork_policy}"
        binding.tvLang.text = "Repo Lang: ${repository.language}"
        binding.tvUpdate.text =
            "Last Updated: ${repository.updated_on.substring(0, 10)}"
        if (TextUtils.isEmpty(repository.description)) {
            binding.tvDesc.hide()
        } else {
            binding.tvDesc.show()
            binding.tvDesc.text = repository.description
        }
        if (repository.is_private) {
            binding.tvAccess.text = "Private Repository"
        } else {
            binding.tvAccess.text = "Public Repository"
        }
    }
}