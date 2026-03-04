package com.example.denislab1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "ЛР №1 – Ткаченко Денис"

        val container = findViewById<FrameLayout>(R.id.drawing_container)
        container.addView(DrawingView(this))
    }
}

class DrawingView(context: Context) : View(context) {

    private val paint = Paint().apply { isAntiAlias = true }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val w = width.toFloat()
        val h = height.toFloat()
        val cx = w / 2f

        canvas.drawColor(Color.parseColor("#0D1117"))

        // ═══ ЗАДАНИЕ 4 — вариант 6: НОВОГОДНЯЯ ЁЛКА ═══

        paint.apply {
            style = Paint.Style.FILL
            color = Color.parseColor("#94A3B8")
            textSize = 32f
            textAlign = Paint.Align.CENTER
        }
        canvas.drawText("Задание 4 — вариант 6: Ёлка", cx, h * 0.04f, paint)

        // Ствол
        val trunkTop    = h * 0.40f
        val trunkBottom = h * 0.48f
        val trunkHalf   = 22f
        paint.apply { style = Paint.Style.FILL; color = Color.parseColor("#8B4513") }
        canvas.drawRect(cx - trunkHalf, trunkTop, cx + trunkHalf, trunkBottom, paint)

        // Нижний ярус
        paint.color = Color.parseColor("#14532D")
        val p1 = Path().apply {
            moveTo(cx, h * 0.20f)
            lineTo(cx - 150f, h * 0.42f)
            lineTo(cx + 150f, h * 0.42f)
            close()
        }
        canvas.drawPath(p1, paint)

        // Средний ярус
        paint.color = Color.parseColor("#16A34A")
        val p2 = Path().apply {
            moveTo(cx, h * 0.14f)
            lineTo(cx - 110f, h * 0.32f)
            lineTo(cx + 110f, h * 0.32f)
            close()
        }
        canvas.drawPath(p2, paint)

        // Верхний ярус
        paint.color = Color.parseColor("#4ADE80")
        val p3 = Path().apply {
            moveTo(cx, h * 0.08f)
            lineTo(cx - 70f, h * 0.22f)
            lineTo(cx + 70f, h * 0.22f)
            close()
        }
        canvas.drawPath(p3, paint)

        // Звезда
        val starCy  = h * 0.065f
        val rOuter  = 28f
        val rInner  = 13f
        val starPath = Path()
        for (i in 0..9) {
            val r     = if (i % 2 == 0) rOuter else rInner
            val angle = (Math.PI / 5.0 * i - Math.PI / 2.0).toFloat()
            val x     = cx + r * cos(angle)
            val y     = starCy + r * sin(angle)
            if (i == 0) starPath.moveTo(x, y) else starPath.lineTo(x, y)
        }
        starPath.close()
        paint.color = Color.parseColor("#FBBF24")
        canvas.drawPath(starPath, paint)

        // ═══ ЗАДАНИЕ 3 — вариант 8: ЗЕЛЁНЫЙ КВАДРАТ (S = P) ═══

        paint.apply {
            style = Paint.Style.FILL
            color = Color.parseColor("#94A3B8")
            textSize = 32f
            textAlign = Paint.Align.CENTER
        }
        canvas.drawText("Задание 3 — вариант 8: Квадрат (S = P)", cx, h * 0.53f, paint)

        // --- Математическая сторона: a = 4 (из a² = 4a) ---
        val a     = 4
        val area  = a * a   // S = 16
        val perim = 4 * a   // P = 16

        // --- Визуальная сторона: 200 пикселей (только для отрисовки) ---
        val side    = 200f
        val squareCy = h * 0.755f

        // Заливка
        paint.apply { style = Paint.Style.FILL; color = Color.parseColor("#22C55E") }
        canvas.drawRect(cx - side / 2f, squareCy - side / 2f,
            cx + side / 2f, squareCy + side / 2f, paint)

        // Обводка
        paint.apply {
            style       = Paint.Style.STROKE
            color       = Color.parseColor("#86EFAC")
            strokeWidth = 4f
        }
        canvas.drawRect(cx - side / 2f, squareCy - side / 2f,
            cx + side / 2f, squareCy + side / 2f, paint)

        // Текст S и P внутри квадрата
        paint.apply {
            style    = Paint.Style.FILL
            color    = Color.BLACK
            textSize = 36f
            textAlign = Paint.Align.CENTER
        }
        canvas.drawText("S = $area", cx, squareCy - 16f, paint)
        canvas.drawText("P = $perim", cx, squareCy + 30f, paint)

        // Пояснение под квадратом
        paint.apply {
            color    = Color.parseColor("#94A3B8")
            textSize = 28f
        }
        canvas.drawText("a² = 4a  →  a = 4  →  S = P = 16",
            cx, squareCy + side / 2f + 44f, paint)
    }
}
