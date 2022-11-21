package ru.madmax.testcaseeffectivemobile.featureExplorer.presentation.viewHolder

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.madmax.testcaseeffectivemobile.databinding.ItemMainBestBinding
import ru.madmax.testcaseeffectivemobile.featureExplorer.domain.model.Content
import ru.madmax.testcaseeffectivemobile.featureExplorer.presentation.adapter.BestAdapter
import ru.madmax.testcaseeffectivemobile.util.GridItemDecoration

class MainBestViewHolder(
    private val binding: ItemMainBestBinding
) : RecyclerView.ViewHolder(binding.root), MainListHolder {

    override fun bind(item: Content, listener: BestAdapter.OnItemClickListener) {
        val best = item as Content.BestItem
        val adapter = BestAdapter(listener)
        adapter.submitList(best.items)
        binding.itemRv.apply {
            this.adapter = adapter
            layoutManager = GridLayoutManager(this.context, 2)
            addItemDecoration(GridItemDecoration(40, 2))
        }
    }

    override fun onAttach() {

    }

    override fun onDetach() {

    }

}