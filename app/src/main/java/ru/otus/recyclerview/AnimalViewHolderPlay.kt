package ru.otus.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

sealed class AnimalViewHolderPlay(
    view: View
) : RecyclerView.ViewHolder(view) {

    abstract fun bind(model: Items)

    abstract fun bind(model: Items, playload: List<Any>)

    class Cat(view: View): AnimalViewHolderPlay(view) {

        private val name = view.findViewById<TextView>(R.id.name)
        private val age = view.findViewById<TextView>(R.id.age)

        override fun bind(model: Items) {
            model as Animal.Cat
            name.text = model.name
            age.text = model.age.toString()
        }

        override fun bind(model: Items, playloads: List<Any>) {
            model as Animal.Cat
            if(playloads.isEmpty()) {
                bind(model)
            } else {
                for (playload in playloads) {
                    when (playload as Int) {
                        AnimalAdaptePlay.UPDATE_NAME -> {
                            name.text = model.name
                        }
                        AnimalAdaptePlay.UPDATE_AGE -> {
                            age.text = model.age.toString()
                        }
                        else -> { bind(model) }
                    }
                }
            }
        }
    }

    class Dog(view: View): AnimalViewHolderPlay(view) {

        private val name = view.findViewById<TextView>(R.id.name)
        private val age = view.findViewById<TextView>(R.id.age)

        override fun bind(model: Items) {
            model as Animal.Dog
            name.text = model.name
            age.text = model.age.toString()
        }

        override fun bind(model: Items, playloads: List<Any>) {
            model as Animal.Dog
            if(playloads.isEmpty()) {
                bind(model)
            } else {
                for (playload in playloads) {
                    when (playload as Int) {
                        AnimalAdaptePlay.UPDATE_NAME -> {
                            name.text = model.name
                        }
                        AnimalAdaptePlay.UPDATE_AGE -> {
                            age.text = model.age.toString()
                        }
                        else -> { bind(model) }
                    }
                }
            }
        }
    }

    class Hamster(view: View): AnimalViewHolderPlay(view) {

        private val name = view.findViewById<TextView>(R.id.name)
        private val age = view.findViewById<TextView>(R.id.age)

        override fun bind(model: Items) {
            model as Animal.Hamster
            name.text = model.name
            age.text = model.age.toString()
        }

        override fun bind(model: Items, playloads: List<Any>) {
            model as Animal.Hamster
            if(playloads.isEmpty()) {
                bind(model)
            } else {
                for (playload in playloads) {
                    when (playload as Int) {
                        AnimalAdaptePlay.UPDATE_NAME -> {
                            name.text = model.name
                        }
                        AnimalAdaptePlay.UPDATE_AGE -> {
                            age.text = model.age.toString()
                        }
                        else -> { bind(model) }
                    }
                }
            }
        }
    }

    class Header(view: View): AnimalViewHolderPlay(view), StickyHolder {

        private val header = view.findViewById<TextView>(R.id.header)

        override fun bind(model: Items) {
            model as HeaderModel
            header.text = model.name
        }

        override fun bind(model: Items, playload: List<Any>) {
            bind(model)
        }
    }
}