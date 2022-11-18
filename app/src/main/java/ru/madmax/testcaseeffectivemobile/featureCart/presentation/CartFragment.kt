package ru.madmax.testcaseeffectivemobile.featureCart.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import ru.madmax.testcaseeffectivemobile.databinding.FragmentCartBinding
import ru.madmax.testcaseeffectivemobile.util.ListsItemDecoration

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val cartViewModel: CartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CartAdapter()

        binding.cartRv.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(ListsItemDecoration(20, 20))
        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            cartViewModel.uiState.collectLatest { event ->

                adapter.submitList(event.basket)

                binding.apply {
                    totalValue.text = "$" + event.total.toString() + " us"
                    deliveryValue.text = event.delivery
                    backButton.setOnClickListener {
                        view.findNavController().navigateUp()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}