package ru.madmax.testcaseeffectivemobile.featureExplorer.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.madmax.testcaseeffectivemobile.featureExplorer.domain.model.Content
import ru.madmax.testcaseeffectivemobile.featureExplorer.presentation.viewHolder.HolderFactory
import ru.madmax.testcaseeffectivemobile.featureExplorer.presentation.viewHolder.MainListHolder

class ExplorerAdapter(private val listener: BestAdapter.OnItemClickListener) : ListAdapter<Content, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Content.BestItem -> Content.BEST_TYPE
            is Content.HotItem -> Content.HOT_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HolderFactory.getHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MainListHolder).bind(getItem(position), listener)
    }

    class DiffCallback : DiffUtil.ItemCallback<Content>() {
        override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
            return if (oldItem is Content.BestItem && newItem is Content.BestItem) {
                (oldItem.items == newItem.items)
            } else if (oldItem is Content.HotItem && newItem is Content.HotItem) {
                (oldItem.items == newItem.items)
            } else {
                oldItem.hashCode() == oldItem.hashCode()
            }
        }

        override fun areContentsTheSame(oldItem: Content, newItem: Content) =
            oldItem == newItem
    }
}