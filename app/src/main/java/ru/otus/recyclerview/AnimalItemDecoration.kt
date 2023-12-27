package ru.otus.recyclerview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class AnimalItemDecoration(
    private val context: Context,
    @DrawableRes private val divider: Int,
    private val shouldShowLastDivider: Boolean = false
) : RecyclerView.ItemDecoration() {

    private val rect = Rect()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        strokeWidth = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            2f,
            context.resources.displayMetrics
        )
    }

    private val dividerDrawable = ContextCompat.getDrawable(context, divider)!!

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        var childCount = parent.childCount

        if (!shouldShowLastDivider) {
            childCount--
        }
        val adapter = parent.adapter as AnimalAdapter

        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + dividerDrawable.intrinsicHeight
            dividerDrawable.setBounds(left, top, right, bottom)
            dividerDrawable.draw(canvas)
            val position = parent.getChildAdapterPosition(child)
            if (position != RecyclerView.NO_POSITION) {
                if (adapter.getAnimal(position) is Animal.Hamster) {
                    parent.getDecoratedBoundsWithMargins(child, rect)
                    canvas.drawLine(
                        rect.left.toFloat(), rect.top.toFloat(),
                        rect.right.toFloat(), rect.bottom.toFloat(),
                        paint
                    )
                    canvas.drawLine(
                        rect.right.toFloat(), rect.top.toFloat(),
                        rect.left.toFloat(), rect.bottom.toFloat(),
                        paint
                    )
                }
            }
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val adapter = parent.adapter as AnimalAdapter
        val position = parent.getChildAdapterPosition(view)
        val paddingAll = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            16f,
            context.resources.displayMetrics
        )
        if (position != RecyclerView.NO_POSITION) {
            outRect.top += paddingAll.toInt()
            outRect.bottom += paddingAll.toInt()
            if (adapter.getAnimal(position) is Animal.Hamster) {
                val padding = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    32f,
                    context.resources.displayMetrics
                )
                outRect.left += padding.toInt()
            }
        }
    }
}