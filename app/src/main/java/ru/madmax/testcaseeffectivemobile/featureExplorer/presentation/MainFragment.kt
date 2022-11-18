package ru.madmax.testcaseeffectivemobile.featureExplorer.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.madmax.testcaseeffectivemobile.R
import ru.madmax.testcaseeffectivemobile.databinding.FragmentMainBinding

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.filterButton.setOnClickListener {
            BottomSheetFilterFragment().show(
                requireActivity().supportFragmentManager,
                "Filters"
            )
        }
        binding.cartButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_cartFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}