package ru.madmax.testcaseeffectivemobile.featureExplorer.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import ru.madmax.testcaseeffectivemobile.R
import ru.madmax.testcaseeffectivemobile.databinding.FragmentMainBinding
import ru.madmax.testcaseeffectivemobile.featureExplorer.presentation.adapter.BestAdapter
import ru.madmax.testcaseeffectivemobile.featureExplorer.presentation.adapter.ExplorerAdapter
import ru.madmax.testcaseeffectivemobile.util.ListsMainItemDecoration
import ru.madmax.testcaseeffectivemobile.util.setNavigationColor

@AndroidEntryPoint
class MainFragment : Fragment(), BestAdapter.OnItemClickListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ExplorerViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity()
            .supportFragmentManager
            .setFragmentResultListener("filter", this) { _, bundle ->
                if (bundle.getInt("filterPriceMax") != 0) {
                    val brand = bundle.getString("filterBrand") ?: "Samsung"
                    val priceMin = bundle.getInt("filterPriceMin")
                    val priceMax = bundle.getInt("filterPriceMax")
                    viewModel.updateFilter(brand, priceMin, priceMax)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        requireActivity().setNavigationColor(ContextCompat.getColor(requireContext(), R.color.blue))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        navController
            .currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<String>("itemId")
            ?.observe(
                viewLifecycleOwner
            ) { _ ->
                viewModel.updateCartAmount()
            }

        binding.cartButton.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_cartFragment)
        }

        val adapter = ExplorerAdapter(this)

        binding.content.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(ListsMainItemDecoration(0, 0))
        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.uiState.collectLatest { explorer ->

                adapter.submitList(explorer.content)

                binding.apply {
                    if (explorer.cartAmount > 0) {
                        badge.visibility = View.VISIBLE
                        badge.text = explorer.cartAmount.toString()
                    }

                    filterButton.setOnClickListener {
                        BottomSheetFilterFragment(
                            explorer.filterBrand,
                            explorer.filterMinPrice,
                            explorer.filterMaxPrice
                        ).show(
                            requireActivity().supportFragmentManager,
                            "Filters"
                        )
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick() {
        navController.navigate(R.id.action_mainFragment_to_detailsFragment)
    }

}