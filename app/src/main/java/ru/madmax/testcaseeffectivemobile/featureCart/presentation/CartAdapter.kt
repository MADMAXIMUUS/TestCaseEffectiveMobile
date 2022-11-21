package ru.madmax.testcaseeffectivemobile.featureCart.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.madmax.testcaseeffectivemobile.databinding.ItemCartBinding
import ru.madmax.testcaseeffectivemobile.featureCart.domain.model.Basket

class CartAdapter(val listener: OnItemClickedListener) : ListAdapter<Basket, CartAdapter.CartViewHolder>(DiffCallback()) {

    inner class CartViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(basket: Basket) {
            binding.apply {
                title.text = basket.title
                cost.text = "$${basket.price}"
                amount.text = basket.amount.toString()
                Glide.with(binding.root).load(basket.images).into(image)
                intButton.setOnClickListener {
                    listener.onPlusClick(absoluteAdapterPosition)
                }
                decButton.setOnClickListener {
                    listener.onMinusClick(absoluteAdapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }

    class DiffCallback : DiffUtil.ItemCallback<Basket>() {
        override fun areItemsTheSame(oldItem: Basket, newItem: Basket): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Basket, newItem: Basket) =
            oldItem == newItem
    }

    interface OnItemClickedListener{
        fun onPlusClick(position: Int)
        fun onMinusClick(position: Int)
    }
}