package ru.madmax.testcaseeffectivemobile.featureDetails.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import ru.madmax.testcaseeffectivemobile.databinding.ItemDetailsBinding

class DetailsImageAdapter :
    ListAdapter<String, DetailsImageAdapter.DetailsImageViewHolder>(DiffCallback()) {

    inner class DetailsImageViewHolder(val binding: ItemDetailsBinding) : ViewHolder(binding.root) {

        fun onBind(url: String) {
            Glide.with(binding.root).load(url).into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsImageViewHolder {
        val binding = ItemDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailsImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsImageViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }

    class DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem.contentEquals(newItem)

        override fun areContentsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem
    }
}