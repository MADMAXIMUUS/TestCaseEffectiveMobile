package ru.madmax.testcaseeffectivemobile.featureExplorer.presentation.adapter

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.madmax.testcaseeffectivemobile.R
import ru.madmax.testcaseeffectivemobile.databinding.ItemBestBinding
import ru.madmax.testcaseeffectivemobile.featureExplorer.domain.model.BestSeller


class BestAdapter(private val listener: OnItemClickListener) : ListAdapter<BestSeller, BestAdapter.BestViewHolder>(DiffCallback()) {

    inner class BestViewHolder(private val binding: ItemBestBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(bestSeller: BestSeller) {

            binding.apply {
                if (bestSeller.is_favorites) {
                    favoriteButton.setImageResource(R.drawable.ic_unfavorite)
                } else {
                    favoriteButton.setImageResource(R.drawable.ic_favorite)
                }
                bestTitle.text = bestSeller.title
                bestCost.text = "$" + bestSeller.discount_price.toString()
                bestCost.paintFlags = bestCostSale.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                bestCostSale.text = "$" + bestSeller.price_without_discount.toString()
                Glide.with(binding.root).load(bestSeller.picture).into(bestImage)
                binding.root.setOnClickListener{
                    listener.onItemClick()
                }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<BestSeller>() {
        override fun areItemsTheSame(oldItem: BestSeller, newItem: BestSeller): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: BestSeller, newItem: BestSeller) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestViewHolder {
        val binding = ItemBestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BestViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }

    interface OnItemClickListener{
        fun onItemClick()
    }
}