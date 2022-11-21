package ru.madmax.testcaseeffectivemobile.featureExplorer.presentation.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.madmax.testcaseeffectivemobile.databinding.ItemMainBestBinding
import ru.madmax.testcaseeffectivemobile.databinding.ItemMainHotBinding
import ru.madmax.testcaseeffectivemobile.featureExplorer.domain.model.Content

class HolderFactory {
    companion object {
        fun getHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return when (viewType) {
                Content.HOT_TYPE -> {
                    val binding = ItemMainHotBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                    MainHotViewHolder(binding)
                }
                Content.BEST_TYPE -> {
                    val binding = ItemMainBestBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                    MainBestViewHolder(binding)
                }
                else -> {
                    val binding = ItemMainHotBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                    MainHotViewHolder(binding)
                }
            }
        }
    }
}