package ru.otus.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import java.util.*

private const val TYPE_CAT = 1
private const val TYPE_DOG = 2
private const val TYPE_HAMSTER = 3
private const val TYPE_HEADER = 4

class AnimalAdaptePlay() : ListAdapter<Items, AnimalViewHolderPlay>(AnimalItemDiffCallback()) {

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is Animal.Cat -> TYPE_CAT
        is Animal.Dog -> TYPE_DOG
        is Animal.Hamster -> TYPE_HAMSTER
        is HeaderModel -> TYPE_HEADER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolderPlay {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_CAT -> AnimalViewHolderPlay.Cat(
                inflater.inflate(R.layout.item_cat, parent, false)
            )

            TYPE_DOG -> AnimalViewHolderPlay.Dog(
                inflater.inflate(R.layout.item_dog, parent, false)
            )

            TYPE_HAMSTER -> AnimalViewHolderPlay.Hamster(
                inflater.inflate(R.layout.item_hamster, parent, false)
            )

            TYPE_HEADER -> AnimalViewHolderPlay.Header(
                inflater.inflate(R.layout.item_header, parent, false)
            )

            else -> error("Improssibru")
        }
    }

    override fun onBindViewHolder(holder: AnimalViewHolderPlay, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: AnimalViewHolderPlay,
        position: Int,
        payloads: MutableList<Any>
    ) {
        holder.bind(getItem(position), payloads)
    }

    fun onItemRemoved(position: Int) {
        val newList = currentList.toMutableList().apply {
            removeAt(position)
        }
        submitList(newList)
    }

    fun getAnimal(position: Int): Items = getItem(position)

    fun onExchanged(fromPosition: Int, toPosition: Int) {
        val list = currentList.toMutableList()
        Collections.swap(list, fromPosition, toPosition)
        submitList(list)
    }

    companion object {
        const val UPDATE_NAME = 1
        const val UPDATE_AGE = 2
        const val UPDATE_ALL = 3
    }
}