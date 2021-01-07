package com.example.notes.component

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.LoadingListBinding
import timber.log.Timber

/**
 * Created by M.Reza Sulaiman on 06/01/21
 * Jepara, Indonesia.
 */
class LoadingFooterViewHolder(
    private val binding: LoadingListBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState) {
        binding.loadingItemProgressBar.isVisible = loadState is LoadState.Loading
        Timber.d("LoadingFooterViewHolder() footer")
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): LoadingFooterViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_notes, parent, false)
            val binding = LoadingListBinding.bind(view)
            Timber.d("LoadingFooterViewHolder() create() footer")
            return LoadingFooterViewHolder(binding, retry)
        }
    }
}