package com.binay.testbitrepo.ui.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.binay.testbitrepo.common.BaseViewHolder
import com.binay.testbitrepo.data.model.Repository
import com.binay.testbitrepo.databinding.RepoItemBinding
import com.binay.testbitrepo.extention.hide
import com.binay.testbitrepo.extention.show
import com.binay.testbitrepo.presentation.OnRepoItemClick

/**
 * Copyright 2021 Payed Pvt. Ltd.
 *
 * Created by Binay on 23/5/21.
 */
class RepoAdapter(
    private val repoList: List<Repository>,
    private val itemClickListener: OnRepoItemClick
) : RecyclerView.Adapter<BaseViewHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            RepoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val holder = RepoViewHolder(itemBinding)

        itemBinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.onItemClick(repoList[position])
        }

        return holder
    }

    override fun getItemCount(): Int = repoList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is RepoViewHolder -> holder.bind(repoList[position])
        }
    }

    private inner class RepoViewHolder(
        val binding: RepoItemBinding
    ) : BaseViewHolder<Repository>(binding.root) {
        override fun bind(item: Repository) {
            binding.tvName.text = "Name: ${item.full_name}"
            binding.tvForkpolicy.text = "Fork Policy: ${item.fork_policy}"
            binding.tvLang.text = "Repo Lang: ${item.language}"
            binding.tvUpdate.text =
                "Last Updated: ${item.updated_on.substring(0, 10)}"
            if (TextUtils.isEmpty(item.description)) {
                binding.tvDesc.hide()
            } else {
                binding.tvDesc.show()
                binding.tvDesc.text = item.description
            }
            if (item.is_private) {
                binding.tvAccess.text = "Private Repository"
            } else {
                binding.tvAccess.text = "Public Repository"
            }
        }

    }
}