package ru.otus.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import java.util.*

private const val TYPE_CAT = 1
private const val TYPE_DOG = 2
private const val TYPE_HAMSTER = 3

class AnimalAdapter() : ListAdapter<Animal, AnimalViewHolder>(AnimalItemDiffCallback()) {

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is Animal.Cat -> TYPE_CAT
        is Animal.Dog -> TYPE_DOG
        is Animal.Hamster -> TYPE_HAMSTER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_CAT -> AnimalViewHolder.Cat(
                inflater.inflate(R.layout.item_cat, parent, false)
            )

            TYPE_DOG -> AnimalViewHolder.Dog(
                inflater.inflate(R.layout.item_dog, parent, false)
            )

            TYPE_HAMSTER -> AnimalViewHolder.Hamster(
                inflater.inflate(R.layout.item_hamster, parent, false)
            )

            else -> error("none")
        }
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    fun onItemRemoved(position: Int) {
        val newList = currentList.toMutableList().apply {
            removeAt(position)
        }
        submitList(newList)
    }

    fun getAnimal(position: Int): Animal = getItem(position)

    fun onExchanged(fromPosition: Int, toPosition: Int) {
        val list = currentList.toMutableList()
        Collections.swap(list, fromPosition, toPosition)
        submitList(list)
    }
}