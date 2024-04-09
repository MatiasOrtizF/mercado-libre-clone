package com.mfo.mercadolibreclone.ui.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfo.mercadolibreclone.databinding.FragmentSearchBinding
import com.mfo.mercadolibreclone.ui.favorites.FavoritesFragmentDirections
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
        //val preferences = PreferenceHelper.defaultPrefs(requireContext())
        //val historyArray = preferences["my_array_key", emptyList<String>()]
        //searchViewModel.getHistorySearch(historyArray)
        initUI()
    }

    private fun initUI() {
        //initList()
        initUIState()
        initListeners()
    }

    /*private fun initList() {
        searchAdapter = SearchAdapter(onItemSelected = {
            findNavController().navigate(SearchFragmentDirections.actionIdSearchFragmentToIdProductFragment(it))
        })

        binding.rvHistorySearch.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
        }
    }*/

    private fun initUIState() {
        /*lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.history.collect {
                    searchAdapter.updateList(it)
                }
            }
        }*/
        binding.etSearchProduct.requestFocus()

        val inm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inm.showSoftInput(binding.etSearchProduct, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(SearchFragmentDirections.actionIdSearchFragmentToIdHomeFragment())
        }
        binding.etSearchProduct.addTextChangedListener {
            binding.btnDeleteSearch.isVisible = it.toString().isNotEmpty()
        }
        binding.btnDeleteSearch.setOnClickListener {
            binding.etSearchProduct.text = null
        }
        binding.etSearchProduct.setOnEditorActionListener { _, _, _ ->
            if(binding.etSearchProduct.text.toString().isNotEmpty()) {
                val productName: String = binding.etSearchProduct.text.toString()
                findNavController().navigate(SearchFragmentDirections.actionIdSearchFragmentToSearchDetailFragment(productName))
                binding.etSearchProduct.setText("")
            }
            true
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