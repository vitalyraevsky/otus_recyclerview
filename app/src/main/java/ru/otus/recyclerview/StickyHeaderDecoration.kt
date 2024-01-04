package ru.otus.recyclerview

import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class StickyHeaderDecoration(
    private val isHeaderPredicate: ((Items) -> Boolean),
    private val populateAction: (View, Items) -> Unit = { _, _ -> }
) : RecyclerView.ItemDecoration() {

    private var headerHeight = 0

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, parent, state)
        val topChild = parent.getChildAt(0) ?: return
        val topChildPosition = parent.getChildAdapterPosition(topChild)
        if (topChildPosition == RecyclerView.NO_POSITION) return

        val currentHeader = getHeaderViewForItem(topChildPosition, parent) ?: return

        layoutStickyHeader(parent, currentHeader)
        val contactPoint = currentHeader.bottom
        val childInContact = getChildInContact(parent, contactPoint)

        if (childInContact != null) {
            val position = parent.getChildAdapterPosition(childInContact)
            val item = (parent.adapter as ListAdapter<Items, *>).currentList[position]
            if (isHeaderPredicate.invoke(item)) {
                moveHeader(canvas, currentHeader, childInContact)
                return
            }
        }
        drawHeader(canvas, currentHeader)
    }

    private fun getHeaderViewForItem(itemPosition: Int, parent: RecyclerView): View? {
        val headerPosition = getHeaderPositionForItem(parent, itemPosition)
        return if (headerPosition != RecyclerView.NO_POSITION) {
            val layoutResId = (parent.adapter as ListAdapter<Items, *>).currentList[headerPosition].getLayoutResId()
            val headerView = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
            val itemModel = (parent.adapter as ListAdapter<Items, *>).currentList[headerPosition]
            populateAction.invoke(headerView, itemModel)
            headerView
        } else {
            null
        }
    }

    private fun getHeaderPositionForItem(parent: RecyclerView, itemPosition: Int): Int {
        return (itemPosition downTo 0)
            .map {
                val item = (parent.adapter as ListAdapter<Items, *>).currentList[it]
                isHeaderPredicate.invoke(item) to it
            }
            .firstOrNull { it.first }?.second ?: RecyclerView.NO_POSITION
    }

    private fun layoutStickyHeader(parent: RecyclerView, headerView: View) {
        val widthSpec = View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.UNSPECIFIED)

        val childWidthSpec = ViewGroup.getChildMeasureSpec(
            widthSpec, parent.paddingLeft + parent.paddingRight, headerView.layoutParams.width
        )
        val childHeightSpec = ViewGroup.getChildMeasureSpec(
            heightSpec, parent.paddingTop + parent.paddingBottom, headerView.layoutParams.height
        )
        headerView.measure(childWidthSpec, childHeightSpec)
        headerHeight = headerView.measuredHeight
        headerView.layout(0, 0, headerView.measuredWidth, headerView.measuredHeight)
    }

    private fun getChildInContact(parent: RecyclerView, contactPoint: Int): View? {
        var childInContact: View? = null
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            if (child.bottom > contactPoint && child.top <= contactPoint) {
                childInContact = child
                break
            }
        }
        return childInContact
    }

    private fun moveHeader(canvas: Canvas, currentHeader: View, nextHeader: View) {
        with(canvas) {
            save()
            translate(0f, (nextHeader.top - currentHeader.height).toFloat())
            currentHeader.draw(this)
            restore()
        }
    }

    private fun drawHeader(canvas: Canvas, headerView: View) {
        with(canvas) {
            save()
            translate(0f, 0f)
            headerView.draw(this)
            restore()
        }
    }
}