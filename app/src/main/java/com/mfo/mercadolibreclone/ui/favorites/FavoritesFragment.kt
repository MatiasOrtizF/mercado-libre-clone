package com.mfo.mercadolibreclone.ui.favorites

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.mfo.mercadolibreclone.R
import com.mfo.mercadolibreclone.databinding.FragmentFavoritesBinding
import com.mfo.mercadolibreclone.ui.favorites.adapter.FavoriteAdapter
import com.mfo.mercadolibreclone.utils.PreferenceHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private lateinit var favoriteAdapter: FavoriteAdapter
    private val favoritesViewModel: FavoritesViewModel by viewModels()

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val preferences = PreferenceHelper.defaultPrefs(requireContext())
        val token: String = preferences.getString("jwt", "").toString()
        favoritesViewModel.getAllProductsInFavorites(token)
        initUI()
    }

    private fun initUI() {
        initList()
        initUIState()
        initListeners()
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(FavoritesFragmentDirections.actionIdFavoritesFragmentToIdHomeFragment())
        }
        binding.btnSearch.setOnClickListener {
            findNavController().navigate(FavoritesFragmentDirections.actionIdFavoritesFragmentToIdSearchFragment())
        }
        binding.btnCart.setOnClickListener {
            findNavController().navigate(FavoritesFragmentDirections.actionIdFavoritesFragmentToIdCartFragment())
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                favoritesViewModel.state.collect {
                    when(it) {
                        FavoriteState.Loading -> loadingState()
                        is FavoriteState.Error -> errorState(it.error)
                        is FavoriteState.Success -> successSate(it)
                    }
                }
            }
        }
    }

    private fun initList() {
        favoriteAdapter = FavoriteAdapter(
            onItemSelected = {
                findNavController().navigate(FavoritesFragmentDirections.actionIdFavoritesFragmentToIdProductDetailFragment(it.product.id, it.product.subCategory))
            },
            onFavoriteDeleteButtonClicked = { id, position ->
                deleteToFavorites(id, position)
            }
        )
        binding.rvFavorite.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = favoriteAdapter
        }
    }

    private fun deleteToFavorites(id: Long, position: Int) {
        val preferences = PreferenceHelper.defaultPrefs(requireContext())
        val token: String = preferences.getString("jwt", "").toString()
        lifecycleScope.launch {
            val isDelete = favoritesViewModel.deleteProductInFavorite(token, id)
            if (isDelete) {
              favoriteAdapter.onDeleteItem(position)
          }
        }
    }

    private fun loadingState() {
        //binding.pb.isVisible = true
    }

    private fun errorState(error: String) {
        //binding.pb.isVisible = false
        val context = binding.root.context
        Toast.makeText(context, "Error: $error", Toast.LENGTH_SHORT).show()
    }

    private fun successSate(state: FavoriteState.Success) {
        //binding.pb.isVisible = false
        if(state.products.isEmpty()) {
            binding.layoutFavEmpty.isVisible = true
        } else {
            binding.layoutFavEmpty.isVisible = false
            favoriteAdapter.updateList(state.products)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }
}