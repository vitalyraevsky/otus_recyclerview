package ru.otus.recyclerview

import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback

class AnimalDiffCallback(
    private val oldAnimals: List<Animal>,
    private val newAnimals: List<Animal>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldAnimals.size

    override fun getNewListSize(): Int = newAnimals.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldAnimals[oldItemPosition]
        val new = newAnimals[newItemPosition]
        return old.id == new.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldAnimals[oldItemPosition]
        val new = newAnimals[newItemPosition]
        return old.equals(new)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Int? {
        val old = oldAnimals[oldItemPosition]
        val new = newAnimals[newItemPosition]
        return when {
            old.name != new.name -> { AnimalAdaptePlay.UPDATE_NAME }
            old.age != new.age -> { AnimalAdaptePlay.UPDATE_AGE }
            else -> { AnimalAdaptePlay.UPDATE_ALL}
        }
    }
}