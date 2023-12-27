package ru.otus.recyclerview

sealed class Animal {

    abstract val id: String
    abstract val name: String
    abstract val age: Int

    data class Cat(
        override val id: String,
        override val name: String,
        override val age: Int,
        val mouseCount: Int,
    ) : Animal()

    data class Dog(
        override val id: String,
        override val name: String,
        override val age: Int,
        val goodBoy: Boolean
    ) : Animal()

    data class Hamster(
        override val id: String,
        override val name: String,
        override val age: Int,
    ) : Animal()
}
