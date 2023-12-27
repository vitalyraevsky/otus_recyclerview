package ru.otus.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

sealed class AnimalViewHolderPlay(
    view: View
) : RecyclerView.ViewHolder(view) {

    abstract fun bind(animal: Animal)

    abstract fun bind(animal: Animal, playload: List<Any>)

    class Cat(view: View): AnimalViewHolderPlay(view) {

        private val name = view.findViewById<TextView>(R.id.name)
        private val age = view.findViewById<TextView>(R.id.age)

        override fun bind(animal: Animal) {
            animal as Animal.Cat
            name.text = animal.name
            age.text = animal.age.toString()
        }

        override fun bind(animal: Animal, playloads: List<Any>) {
            if(playloads.isEmpty()) {
                bind(animal)
            } else {
                for (playload in playloads) {
                    when (playload as Int) {
                        AnimalAdaptePlay.UPDATE_NAME -> {
                            name.text = animal.name
                        }
                        AnimalAdaptePlay.UPDATE_AGE -> {
                            age.text = animal.age.toString()
                        }
                        else -> { bind(animal) }
                    }
                }
            }
        }
    }

    class Dog(view: View): AnimalViewHolderPlay(view) {

        private val name = view.findViewById<TextView>(R.id.name)
        private val age = view.findViewById<TextView>(R.id.age)

        override fun bind(animal: Animal) {
            animal as Animal.Dog
            name.text = animal.name
            age.text = animal.age.toString()
        }

        override fun bind(animal: Animal, playloads: List<Any>) {
            if(playloads.isEmpty()) {
                bind(animal)
            } else {
                for (playload in playloads) {
                    when (playload as Int) {
                        AnimalAdaptePlay.UPDATE_NAME -> {
                            name.text = animal.name
                        }
                        AnimalAdaptePlay.UPDATE_AGE -> {
                            age.text = animal.age.toString()
                        }
                        else -> { bind(animal) }
                    }
                }
            }
        }
    }

    class Hamster(view: View): AnimalViewHolderPlay(view) {

        private val name = view.findViewById<TextView>(R.id.name)
        private val age = view.findViewById<TextView>(R.id.age)

        override fun bind(animal: Animal) {
            animal as Animal.Hamster
            name.text = animal.name
            age.text = animal.age.toString()
        }

        override fun bind(animal: Animal, playloads: List<Any>) {
            if(playloads.isEmpty()) {
                bind(animal)
            } else {
                for (playload in playloads) {
                    when (playload as Int) {
                        AnimalAdaptePlay.UPDATE_NAME -> {
                            name.text = animal.name
                        }
                        AnimalAdaptePlay.UPDATE_AGE -> {
                            age.text = animal.age.toString()
                        }
                        else -> { bind(animal) }
                    }
                }
            }
        }
    }
}
