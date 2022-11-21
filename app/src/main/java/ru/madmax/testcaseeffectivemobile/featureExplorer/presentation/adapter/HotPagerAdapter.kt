package ru.madmax.testcaseeffectivemobile.featureExplorer.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.madmax.testcaseeffectivemobile.databinding.ItemHotBinding
import ru.madmax.testcaseeffectivemobile.featureExplorer.domain.model.HomeStore

class HotPagerAdapter : ListAdapter<HomeStore, HotPagerAdapter.HotViewHolder>(DiffCallback()) {

    inner class HotViewHolder(private val binding: ItemHotBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(homeStore: HomeStore) {
            binding.apply {
                if (homeStore.is_new) {
                    hotNew.visibility = View.VISIBLE
                } else {
                    hotNew.visibility = View.INVISIBLE
                }
                hotTitle.text = homeStore.title
                hotDescription.text = homeStore.subtitle
                Glide.with(binding.root).load(homeStore.picture).into(image)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotViewHolder {
        val binding = ItemHotBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HotViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HotViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }

    class DiffCallback : DiffUtil.ItemCallback<HomeStore>() {
        override fun areItemsTheSame(oldItem: HomeStore, newItem: HomeStore): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: HomeStore, newItem: HomeStore) =
            oldItem == newItem
    }

}