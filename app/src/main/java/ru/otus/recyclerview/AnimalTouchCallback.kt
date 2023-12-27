package ru.otus.recyclerview

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class AnimalTouchCallback : ItemTouchHelper.Callback() {


    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val canRemove =
            viewHolder.animalAdapter.getAnimal(viewHolder.bindingAdapterPosition) !is Animal.Hamster
        return if (canRemove) {
            makeMovementFlags(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT
            )
        } else {
            makeMovementFlags(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                0
            )
        }
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        viewHolder.animalAdapter.onExchanged(
            viewHolder.bindingAdapterPosition,
            target.bindingAdapterPosition
        )
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        viewHolder.animalAdapter.onItemRemoved(viewHolder.bindingAdapterPosition)
    }

    private val RecyclerView.ViewHolder.animalAdapter: AnimalAdapter
        get() = bindingAdapter as? AnimalAdapter ?: error("Not AnimalAdapter")

}