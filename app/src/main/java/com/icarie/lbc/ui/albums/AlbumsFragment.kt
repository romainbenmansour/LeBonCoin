package com.icarie.lbc.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.icarie.lbc.databinding.FragmentAlbumsBinding
import com.icarie.lbc.ui.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlbumsFragment : Fragment() {

    private lateinit var binding: FragmentAlbumsBinding

    private val albumsViewModel: AlbumsViewModel by viewModels()
    private val albumAdapter = AlbumAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentAlbumsBinding.inflate(inflater)
        .apply {
            binding = this
            lifecycleOwner = viewLifecycleOwner
            viewModel = albumsViewModel
            albums.adapter = albumAdapter
        }.also {
            prepareForLifecycle()
        }.root

    private fun prepareForLifecycle() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                collectUIState()
            }
        }
    }

    private suspend fun collectUIState() {
        albumsViewModel.uiState.collect {
            when (it) {
                is UIState.Failure -> {
                    binding.progress.visibility = View.GONE
                    binding.error.visibility = View.VISIBLE
                    binding.albums.visibility = View.VISIBLE
                }

                is UIState.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.error.visibility = View.GONE
                    binding.albums.visibility = View.GONE
                }

                is UIState.Success -> {
                    binding.progress.visibility = View.GONE
                    binding.error.visibility = View.GONE
                    binding.albums.visibility = View.VISIBLE
                    collectAlbums()
                }

                else -> Unit
            }
        }
    }

    private suspend fun collectAlbums() {
        albumsViewModel.albumsFlow.collectLatest {
            albumAdapter.updateData(it)
        }
    }
}