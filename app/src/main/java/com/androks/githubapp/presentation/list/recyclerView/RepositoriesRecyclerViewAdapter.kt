package com.androks.githubapp.presentation.list.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.androks.githubapp.R
import com.androks.githubapp.domain.model.RepositoryModel
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoriesRecyclerViewAdapter :
    RecyclerView.Adapter<RepositoriesRecyclerViewAdapter.ViewHolder>() {

    private var repositories = mutableListOf<RepositoryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_repository,
                parent,
                false
            )
        )

    override fun getItemCount() = repositories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repositories[position])
    }

    fun setItems(data: List<RepositoryModel>) {
        repositories = data.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(model: RepositoryModel) {
            with(itemView) {
                nameTextView.text = model.name
                descTextView.text = model.description
                startsTextView.text = model.stars.toString()
                forksTextView.text = model.forks.toString()
                langTextView.isVisible = !model.language.isNullOrEmpty()
                if (langTextView.isVisible) langTextView.text = model.language
            }
        }
    }
}