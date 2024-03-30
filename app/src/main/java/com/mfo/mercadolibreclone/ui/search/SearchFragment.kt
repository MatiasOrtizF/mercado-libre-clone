package com.mfo.mercadolibreclone.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfo.mercadolibreclone.R
import com.mfo.mercadolibreclone.databinding.FragmentHomeBinding
import com.mfo.mercadolibreclone.databinding.FragmentSearchBinding
import com.mfo.mercadolibreclone.ui.globals.ProductFragment
import com.mfo.mercadolibreclone.ui.search.adapter.SearchAdapter
import com.mfo.mercadolibreclone.utils.PreferenceHelper
import com.mfo.mercadolibreclone.utils.PreferenceHelper.get
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var searchAdapter: SearchAdapter

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val preferences = PreferenceHelper.defaultPrefs(requireContext())
        val historyArray = preferences["my_array_key", emptyList<String>()]
        searchViewModel.getHistorySearch(historyArray)
        initUI()
    }

    private fun initUI() {
        initList()
        initUIState()
        //initListeners()
    }

    private fun initList() {
        searchAdapter = SearchAdapter(onItemSelected = {
            findNavController().navigate(SearchFragmentDirections.actionIdSearchFragmentToIdProductFragment(it))
        })

        binding.rvHistorySearch.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.history.collect {
                    searchAdapter.updateList(it)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }
}