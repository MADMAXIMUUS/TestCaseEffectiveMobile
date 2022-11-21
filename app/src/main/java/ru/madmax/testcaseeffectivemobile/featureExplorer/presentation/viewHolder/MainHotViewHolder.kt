package ru.madmax.testcaseeffectivemobile.featureExplorer.presentation.viewHolder

import androidx.recyclerview.widget.RecyclerView
import ru.madmax.testcaseeffectivemobile.databinding.ItemMainHotBinding
import ru.madmax.testcaseeffectivemobile.featureExplorer.domain.model.Content
import ru.madmax.testcaseeffectivemobile.featureExplorer.presentation.adapter.BestAdapter
import ru.madmax.testcaseeffectivemobile.featureExplorer.presentation.adapter.HotPagerAdapter
import ru.madmax.testcaseeffectivemobile.util.PagerItemDecoration

class MainHotViewHolder(
    private val binding: ItemMainHotBinding
) : RecyclerView.ViewHolder(binding.root), MainListHolder {

    override fun bind(item: Content, listener: BestAdapter.OnItemClickListener) {
        val hot = item as Content.HotItem
        val adapter = HotPagerAdapter()
        adapter.submitList(hot.items)
        binding.apply {
            itemVp.adapter = adapter
            itemVp.addItemDecoration(PagerItemDecoration(40, 0, 40, 40))
        }
    }

    override fun onAttach() {

    }

    override fun onDetach() {

    }

}