package ru.otus.recyclerview

import androidx.recyclerview.widget.DiffUtil

class AnimalItemDiffCallback : DiffUtil.ItemCallback<Animal>() {

    override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean =
        oldItem == newItem
}