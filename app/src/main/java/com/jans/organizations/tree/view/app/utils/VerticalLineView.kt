package com.jans.organizations.tree.view.app.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class VerticalLineView : View {
    private val linePaint = Paint()

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        linePaint.color = resources.getColor(android.R.color.black) // Set line color
        linePaint.strokeWidth = resources.displayMetrics.density * 2 // Set line thickness
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val startX = width / 2f // Line starts from the center horizontally
        val startY = 0f // Line starts from the top
        val endY = height.toFloat() // Line ends at the bottom

        canvas.drawLine(startX, startY, startX, endY, linePaint) // Draw the line
    }
}
