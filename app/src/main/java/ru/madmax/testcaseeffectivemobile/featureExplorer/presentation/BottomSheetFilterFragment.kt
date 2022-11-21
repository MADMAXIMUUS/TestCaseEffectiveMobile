package ru.madmax.testcaseeffectivemobile.featureExplorer.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.setFragmentResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.madmax.testcaseeffectivemobile.R
import ru.madmax.testcaseeffectivemobile.databinding.FragmentBottomSheetFilterBinding


class BottomSheetFilterFragment(
    private var brand: String,
    private var priceMin: Int,
    private var priceMax: Int
) : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetFilterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrayBrands = resources.getStringArray(R.array.brands)
        val arrayPrices = resources.getStringArray(R.array.prices)

        binding.apply {
            brandSelectorTitle.setText(brand, false)
            priceSelectorTitle.setText("$$priceMin - $$priceMax", false)
            brandSelectorTitle.setAdapter(
                ArrayAdapter(
                    requireContext(),
                    R.layout.item_dropdown,
                    arrayBrands
                )
            )
            brandSelectorTitle.setOnItemClickListener { _, _, position, _ ->
                brand = arrayBrands[position]
            }
            priceSelectorTitle.setOnItemClickListener { _, _, position, _ ->
                priceMin = position * 1000
                priceMax = position * 1000 + 999
                if (position == 9) {
                    priceMax = 10000
                }
            }
            priceSelectorTitle.setAdapter(
                ArrayAdapter(
                    requireContext(),
                    R.layout.item_dropdown,
                    arrayPrices
                )
            )
            closeButton.setOnClickListener {
                dismiss()
            }
            doneButton.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("filterBrand", brand)
                bundle.putInt("filterPriceMin", priceMin)
                bundle.putInt("filterPriceMax", priceMax)
                setFragmentResult(
                    "filter",
                    bundle
                )
                dismiss()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}