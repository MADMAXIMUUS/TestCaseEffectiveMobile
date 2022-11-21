package ru.madmax.testcaseeffectivemobile.featureDetails.presentation

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import ru.madmax.testcaseeffectivemobile.R
import ru.madmax.testcaseeffectivemobile.databinding.FragmentDetailsBinding
import ru.madmax.testcaseeffectivemobile.util.setNavigationColor
import kotlin.math.abs

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpTransformer()
    }

    private fun setUpTransformer(): CompositePageTransformer {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(100))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = (0.85f + r + 0.15f) / 2
        }
        return transformer
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        requireActivity().setNavigationColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.white
            )
        )
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DetailsImageAdapter()

        binding.productImage.apply {
            this.adapter = adapter
            offscreenPageLimit = 3
            clipChildren = false
            clipToPadding = false
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.uiState.collectLatest { details ->

                adapter.submitList(details.images)

                binding.apply {
                    productImage.setPageTransformer(setUpTransformer())
                    deviceTitle.text = details.title
                    costTitle.text = "$" + details.price.toString()
                    camTitle.text = details.camera
                    procTitle.text = details.CPU
                    flashTitle.text = details.sd
                    ramTitle.text = details.ssd
                    if (details.isFavorites) {
                        favoriteButton.setImageResource(R.drawable.ic_unfavorite_big)
                    } else {
                        favoriteButton.setImageResource(R.drawable.ic_favorite_big)
                    }
                    if (details.capacity.isNotEmpty()) {
                        gb128.text = details.capacity[0] + "GB"
                        gb256.text = details.capacity[1] + "GB"
                    }
                    if (details.color.isNotEmpty()) {
                        color1Back.imageTintList =
                            ColorStateList.valueOf(Color.parseColor(details.color[0]))
                        color2Back.imageTintList =
                            ColorStateList.valueOf(Color.parseColor(details.color[1]))
                    }
                    ratingRoot.rating = details.rating.toFloat()
                    backButton.setOnClickListener {
                        view.findNavController().navigateUp()
                    }
                    buyButton.setOnClickListener {
                        view.findNavController().previousBackStackEntry?.savedStateHandle?.set(
                            "itemId",
                            details.id
                        )
                        view.findNavController().popBackStack()
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