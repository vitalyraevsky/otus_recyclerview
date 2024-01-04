package ru.otus.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

sealed class AnimalViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    abstract fun bind(model: Items)

    class Cat(view: View) : AnimalViewHolder(view) {

        private val name = view.findViewById<TextView>(R.id.name)
        private val age = view.findViewById<TextView>(R.id.age)

        override fun bind(model: Items) {
            model as Animal.Cat
            name.text = model.name
            age.text = model.age.toString()
        }
    }

    class Dog(view: View) : AnimalViewHolder(view) {

        private val name = view.findViewById<TextView>(R.id.name)
        private val age = view.findViewById<TextView>(R.id.age)

        override fun bind(model: Items) {
            model as Animal.Dog
            name.text = model.name
            age.text = model.age.toString()
        }
    }

    class Hamster(view: View) : AnimalViewHolder(view) {

        private val name = view.findViewById<TextView>(R.id.name)
        private val age = view.findViewById<TextView>(R.id.age)

        override fun bind(model: Items) {
            model as Animal.Hamster
            name.text = model.name
            age.text = model.age.toString()
        }
    }

    class Header(view: View) : AnimalViewHolder(view), StickyHolder {

        private val header = view.findViewById<TextView>(R.id.header)

        override fun bind(model: Items) {
            model as HeaderModel
            header.text = model.name
        }
    }
}
