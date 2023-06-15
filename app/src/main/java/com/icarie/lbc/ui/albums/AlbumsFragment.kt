package com.icarie.lbc.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.icarie.lbc.databinding.FragmentAlbumsBinding
import com.icarie.lbc.ui.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AlbumsFragment : Fragment() {

    private val albumsViewModel: AlbumsViewModel by viewModels()
    private lateinit var binding: FragmentAlbumsBinding

    private val albumAdapter = AlbumAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentAlbumsBinding.inflate(inflater)
            .apply {
                binding = this
                lifecycleOwner = viewLifecycleOwner
                viewModel = albumsViewModel
                albums.adapter = albumAdapter
            }.root
    }

    override fun onStart() {
        super.onStart()

        lifecycleScope.launch {
            albumsViewModel.uiState.collectLatest {
                when (it) {
                    is UIState.Failure -> {
                        binding.progress.visibility = View.GONE
                        binding.error.visibility = View.VISIBLE
                    }

                    is UIState.Loading -> {
                        binding.progress.visibility = View.VISIBLE
                        binding.error.visibility = View.GONE
                    }

                    is UIState.Success -> {
                        binding.progress.visibility = View.GONE
                        binding.error.visibility = View.GONE
                        it.data?.collectLatest { pagingData ->
                            albumAdapter.updateData(pagingData)
                        }
                    }

                    else -> Unit
                }
            }
        }
    }
}