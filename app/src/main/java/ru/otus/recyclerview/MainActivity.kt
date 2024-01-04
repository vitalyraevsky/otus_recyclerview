package ru.otus.recyclerview

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    val animals = generateAnimals(50)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val adapter = AnimalAdapter().apply { submitList(animals) }
        val adapter = AnimalAdaptePlay().apply { submitList(animals) }

        findViewById<Button>(R.id.change).setOnClickListener {
            val newAnimals = animals.toMutableList()
            newAnimals.removeAt(2)
            newAnimals.add(5, Animal.Hamster("bob", "Bob", 100))
            newAnimals[0] = Animal.Dog(
                id = newAnimals[0].id,
                name = "Replaced",
                age = 10,
                goodBoy = false
            )
            adapter.submitList(newAnimals)
        }

        val recycler = findViewById<RecyclerView>(R.id.recycler)
        //recycler.layoutManager = LinearLayoutManager(this)
        //recycler.adapter = adapter
        recycler.swapAdapter(adapter, false)
        recycler.setItemViewCacheSize(10)
        recycler.recycledViewPool.setMaxRecycledViews(1, 10)
        recycler.addItemDecoration(StickyHeaderDecoration(
            isHeaderPredicate = { it is HeaderModel },
            populateAction = { view, model -> populateStickyHeader(view, model) }
        ))
        //recycler.addItemDecoration(DividerDecoration())
        //recycler.addItemDecoration(AnimalItemDecoration(this, R.drawable.divider, false))
        //ItemTouchHelper(AnimalTouchCallback()).attachToRecyclerView(recycler)
        //LinearSnapHelper().attachToRecyclerView(recycler)
        //PagerSnapHelper().attachToRecyclerView(recycler)

        //(recycler.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
    }

    private fun populateStickyHeader(view: View, model: Items) {
        if (model !is HeaderModel) return
        val header = view.findViewById<TextView>(R.id.header)
        header.text = model.name
    }

    private fun generateAnimals(count: Int): List<Items> {
        return (0 until count).map { index ->
            val type = Random.nextInt(0, 4)
            when (type) {
                0 -> Animal.Cat(
                    id = "$index",
                    name = "Cat $index",
                    age = Random.nextInt(0, 16),
                    mouseCount = Random.nextInt(0, 10)
                )
                1 -> Animal.Dog(
                    id = "$index",
                    name = "Dog $index",
                    age = Random.nextInt(0, 16),
                    goodBoy = Random.nextBoolean()
                )
                2 -> Animal.Hamster(
                    id = "$index",
                    name = "Hamster $index",
                    age = Random.nextInt(0, 6)
                )
                3 -> HeaderModel(
                    id = "$index",
                    name = "Header $index",
                )
                else -> error("Unsupported type")
            }
        }
    }
}