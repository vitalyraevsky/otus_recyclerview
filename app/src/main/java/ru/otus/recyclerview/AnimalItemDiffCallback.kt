package ru.otus.recyclerview

import androidx.recyclerview.widget.DiffUtil

class AnimalItemDiffCallback : DiffUtil.ItemCallback<Items>() {

    override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
        return when {
            oldItem is Animal && newItem is Animal -> oldItem.id == newItem.id
            oldItem is HeaderModel && newItem is HeaderModel -> oldItem.hashCode() == newItem.hashCode()
            else -> false
        }
    }

    override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
        return oldItem == newItem
    }
}