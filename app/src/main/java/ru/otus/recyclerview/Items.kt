package ru.otus.recyclerview

sealed class Items {
    abstract val id: String
    abstract val name: String

    abstract fun getLayoutResId(): Int
}

sealed class Animal : Items() {

    abstract val age: Int

    data class Cat(
        override val id: String,
        override val name: String,
        override val age: Int,
        val mouseCount: Int,
    ) : Animal() {

        override fun getLayoutResId() = R.layout.item_cat
    }

    data class Dog(
        override val id: String,
        override val name: String,
        override val age: Int,
        val goodBoy: Boolean
    ) : Animal() {

        override fun getLayoutResId() = R.layout.item_dog
    }

    data class Hamster(
        override val id: String,
        override val name: String,
        override val age: Int,
    ) : Animal() {

        override fun getLayoutResId() = R.layout.item_hamster

    }
}

data class HeaderModel(
    override val id: String,
    override val name: String,
) : Items() {

    override fun getLayoutResId() = R.layout.item_header
}
