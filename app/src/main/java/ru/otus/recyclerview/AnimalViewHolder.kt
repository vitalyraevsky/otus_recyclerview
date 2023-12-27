package ru.otus.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

sealed class AnimalViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    abstract fun bind(animal: Animal)

    class Cat(view: View): AnimalViewHolder(view) {

        private val name = view.findViewById<TextView>(R.id.name)
        private val age = view.findViewById<TextView>(R.id.age)

        override fun bind(animal: Animal) {
            animal as Animal.Cat
            name.text = animal.name
            age.text = animal.age.toString()
        }
    }

    class Dog(view: View): AnimalViewHolder(view) {

        private val name = view.findViewById<TextView>(R.id.name)
        private val age = view.findViewById<TextView>(R.id.age)

        override fun bind(animal: Animal) {
            animal as Animal.Dog
            name.text = animal.name
            age.text = animal.age.toString()
        }
    }

    class Hamster(view: View): AnimalViewHolder(view) {

        private val name = view.findViewById<TextView>(R.id.name)
        private val age = view.findViewById<TextView>(R.id.age)

        override fun bind(animal: Animal) {
            animal as Animal.Hamster
            name.text = animal.name
            age.text = animal.age.toString()
        }
    }

}
