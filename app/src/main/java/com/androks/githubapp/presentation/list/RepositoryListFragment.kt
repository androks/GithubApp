package com.androks.githubapp.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.androks.githubapp.R
import com.androks.githubapp.ext.observe
import com.androks.githubapp.presentation.list.recyclerView.RepositoriesRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_repositories.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoryListFragment : Fragment() {

    private val repositoryViewModel: RepositoryListViewModel by viewModel()
    private lateinit var repositoriesAdapter: RepositoriesRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repositories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        repositoryViewModel.loadList()
        observe(repositoryViewModel.state, ::showState)
    }

    private fun initView() {
        repositoriesAdapter = RepositoriesRecyclerViewAdapter()
        repositoriesRv.adapter = repositoriesAdapter
        repositoriesRv.setHasFixedSize(true)
    }

    private fun showState(state: RepositoryListViewModel.ViewState) {
        progressBar.isVisible = state.isLoading
        repositoriesRv.isVisible = state.repositories.isNotEmpty()
        repositoriesAdapter.setItems(state.repositories)
        errorTv.isVisible = state.isError
    }
}
