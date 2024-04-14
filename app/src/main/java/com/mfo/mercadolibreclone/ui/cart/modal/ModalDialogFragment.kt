package com.mfo.mercadolibreclone.ui.cart.modal

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mfo.mercadolibreclone.R
import com.mfo.mercadolibreclone.databinding.ModalBinding

class ModalDialogFragment : BottomSheetDialogFragment() {

    private var _binding: ModalBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(quantity: Int, totalPrice: Int): ModalDialogFragment {
            val fragment = ModalDialogFragment()
            val args = Bundle()
            args.putInt("quantity", quantity)
            args.putInt("totalPrice", totalPrice)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val quantity = requireArguments().getInt("quantity")
        val totalPrice = requireArguments().getInt("totalPrice")
        initUI(quantity, totalPrice)
    }

    fun initUI(quantity: Int, totalPrice: Int) {
        initUIState(quantity, totalPrice)
        initListeners()
    }

    fun initUIState(quantity: Int, totalPrice: Int) {
        binding.tvTotalProducts.text = "Products($quantity)"
        binding.tvTotalPrice.text = totalPrice.toString()

    }

    fun initListeners() {
        binding.btnBuy.setOnClickListener {
            println("comprar")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ModalBinding.inflate(inflater, container, false)
        return binding.root

    }

    /*override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }*/
}