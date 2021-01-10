package com.example.android.codelabs.paging.ui.retry

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.repos_load_state_footer_view_item.view.*
import no3ratii.mohammad.dev.app.pagingtestnew.R

class ReposLoadStateViewHolder(
    private val view: View,
    retry: () -> Unit
) : RecyclerView.ViewHolder(view) {

    init {
        view.retry_button.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            view.error_msg.text = loadState.error.localizedMessage
        }

        /** exaple from is

          when (x) {
        is Int -> print(x + 1)
        is String -> print(x.length + 1)
        is IntArray -> print(x.sum())
        }
        is مقایسه میکند
        ایا عنصر اول از جنس عنصر دوم است
         */
        view.progress_bar.isVisible = loadState is LoadState.Loading
//        binding.retryButton.isVisible = loadState !is LoadState.Loading
//        binding.errorMsg.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): ReposLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.repos_load_state_footer_view_item, parent, false)
            return ReposLoadStateViewHolder(view, retry)
        }
    }
}
