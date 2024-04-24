package com.mfo.mercadolibreclone.ui.searchDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfo.mercadolibreclone.databinding.FragmentSearchDetailBinding
import com.mfo.mercadolibreclone.ui.searchDetail.adapter.SearchDetailAdapter
import com.mfo.mercadolibreclone.utils.PreferenceHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchDetailFragment : Fragment() {

    val args: SearchDetailFragmentArgs by navArgs()
    private lateinit var searchDetailAdapter: SearchDetailAdapter
    private val searchDetailViewModel: SearchDetailViewModel by viewModels()

    private var _binding: FragmentSearchDetailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val word: String = args.word
        searchDetailViewModel.searchProductByName(word.lowercase())
        initUI()
    }

    private fun initUI() {
        initList()
        initUIState()
        initListeners()
    }

    private fun initListeners() {

    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchDetailViewModel.state.collect {
                    when(it) {
                        SearchDetailState.Loading -> loadingState()
                        is SearchDetailState.Error -> errorState(it.error)
                        is SearchDetailState.Success -> successSate(it)
                    }
                }
            }
        }
    }

    private fun initList() {
        searchDetailAdapter = SearchDetailAdapter(
            onItemSelected = {
                findNavController().navigate(SearchDetailFragmentDirections.actionSearchDetailFragmentToIdProductDetailFragment(it.id, it.subCategory))
            },
        )
        binding.rvSearchDetail.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchDetailAdapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun loadingState() {
        binding.pb.isVisible = true
    }

    private fun errorState(error: String) {
        binding.pb.isVisible = false
        val context = binding.root.context
        Toast.makeText(context, "Error: $error", Toast.LENGTH_SHORT).show()
    }

    private fun successSate(state: SearchDetailState.Success) {
        binding.pb.isVisible = false
        searchDetailAdapter.updateList(state.products)
    }
}