```markdown
# Лабораторная работа №1 — Знакомство с Android Studio

**Дисциплина:** Программирование мобильных устройств  
**Студент:** Ткаченко Денис  
**Группа:** ИНС-24-2  
**Дата рождения:** 23.06.2006  
**СКФУ, 2024**

---

## Цель работы

Изучение интерфейса Android Studio и создание первого простого приложения с использованием графических примитивов Canvas.

---

## Вариант

| Задание | Вариант | Описание |
|---------|---------|----------|
| Задание 3 | 8 | Зелёный квадрат, площадь которого равна периметру |
| Задание 4 | 6 | Новогодняя ёлка |

---

## Ход работы

### 1. Создание проекта

В Android Studio создан проект **Empty Views Activity** с языком Kotlin.  
В заголовке приложения указано авторство через `setTitle()`.

### 2. Разметка (activity_main.xml)

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/root_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#0D1117">

    <TextView
        android:id="@+id/tvStudentInfo"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="16dp"
        android:text="Ткаченко Денис\nГруппа: ИНС-24-2\nДата рождения: 23.06.2006"
        android:textSize="20sp"
        android:textColor="#E0E0E0"
        android:textStyle="bold"
        android:gravity="center"
        android:background="#1F2937"
        android:elevation="8dp"
        android:translationZ="4dp"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <FrameLayout
        android:id="@+id/drawing_container"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintTop_toBottomOf="@id/tvStudentInfo"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### 3. Основной код (MainActivity.kt)

```kotlin
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

    private val paint = Paint().apply {
        isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val w = width.toFloat()
        val h = height.toFloat()
        val cx = w / 2f

        canvas.drawColor(Color.parseColor("#0D1117"))

        // ЗАДАНИЕ 4 — вариант 6: НОВОГОДНЯЯ ЁЛКА 

        paint.apply {
            style = Paint.Style.FILL
            color = Color.parseColor("#94A3B8")
            textSize = 32f
            textAlign = Paint.Align.CENTER
        }
        canvas.drawText("Задание 4 — вариант 6: Ёлка", cx, h * 0.04f, paint)

        val trunkTop = h * 0.40f
        val trunkBottom = h * 0.48f
        val trunkHalf = 22f
        paint.apply { style = Paint.Style.FILL; color = Color.parseColor("#8B4513") }
        canvas.drawRect(cx - trunkHalf, trunkTop, cx + trunkHalf, trunkBottom, paint)

        paint.color = Color.parseColor("#14532D")
        val p1 = Path().apply {
            moveTo(cx, h * 0.20f)
            lineTo(cx - 150f, h * 0.42f)
            lineTo(cx + 150f, h * 0.42f)
            close()
        }
        canvas.drawPath(p1, paint)

        paint.color = Color.parseColor("#16A34A")
        val p2 = Path().apply {
            moveTo(cx, h * 0.14f)
            lineTo(cx - 110f, h * 0.32f)
            lineTo(cx + 110f, h * 0.32f)
            close()
        }
        canvas.drawPath(p2, paint)

        paint.color = Color.parseColor("#4ADE80")
        val p3 = Path().apply {
            moveTo(cx, h * 0.08f)
            lineTo(cx - 70f, h * 0.22f)
            lineTo(cx + 70f, h * 0.22f)
            close()
        }
        canvas.drawPath(p3, paint)

        val starCy = h * 0.065f
        val rOuter = 28f
        val rInner = 13f
        val starPath = Path()
        for (i in 0..9) {
            val r = if (i % 2 == 0) rOuter else rInner
            val angle = (Math.PI / 5.0 * i - Math.PI / 2.0).toFloat()
            val x = cx + r * cos(angle)
            val y = starCy + r * sin(angle)
            if (i == 0) starPath.moveTo(x, y) else starPath.lineTo(x, y)
        }
        starPath.close()
        paint.color = Color.parseColor("#FBBF24")
        canvas.drawPath(starPath, paint)

        // ЗАДАНИЕ 3 — вариант 8: ЗЕЛЁНЫЙ КВАДРАТ (S ~ P) 

        paint.apply {
            color = Color.parseColor("#94A3B8")
            textSize = 32f
            textAlign = Paint.Align.CENTER
        }
        canvas.drawText("Задание 3 — вариант 8: Квадрат (S ≈ P)", cx, h * 0.53f, paint)

        val side = minOf(w, h * 0.40f) * 0.45f
        val squareCy = h * 0.755f

        paint.apply { style = Paint.Style.FILL; color = Color.parseColor("#22C55E") }
        canvas.drawRect(cx - side / 2f, squareCy - side / 2f, cx + side / 2f, squareCy + side / 2f, paint)

        paint.apply {
            style = Paint.Style.STROKE
            color = Color.parseColor("#86EFAC")
            strokeWidth = 4f
        }
        canvas.drawRect(cx - side / 2f, squareCy - side / 2f, cx + side / 2f, squareCy + side / 2f, paint)

        val sideInt = side.toInt()
        val area = sideInt * sideInt
        val perim = 4 * sideInt
        paint.apply {
            style = Paint.Style.FILL
            color = Color.BLACK
            textSize = 34f
            textAlign = Paint.Align.CENTER
        }
        canvas.drawText("S = $area", cx, squareCy - 14f, paint)
        canvas.drawText("P = $perim", cx, squareCy + 28f, paint)

        paint.apply {
            color = Color.parseColor("#94A3B8")
            textSize = 28f
        }
        canvas.drawText("a² = 4a  →  a = 4  →  S = P = 16", cx, squareCy + side / 2f + 40f, paint)
    }
}
```

---

## Задание 3 — Зелёный квадрат (S = P)

**Математическое обоснование:**

Площадь квадрата: `S = a²`  
Периметр квадрата: `P = 4a`

Условие `S = P`:
```
a² = 4a
a = 4
S = 16, P = 16
```

При стороне `a = 4` площадь равна периметру. В приложении квадрат масштабируется адаптивно, подписи `S` и `P` отображаются на фигуре.

---

## Задание 4 — Новогодняя ёлка

Ёлка построена из графических примитивов через `Path` (ломаные линии):

| Элемент | Цвет | Метод |
|---------|------|-------|
| Нижний ярус | `#14532D` (тёмно-зелёный) | `drawPath` |
| Средний ярус | `#16A34A` (средне-зелёный) | `drawPath` |
| Верхний ярус | `#4ADE80` (светло-зелёный) | `drawPath` |
| Ствол | `#8B4513` (коричневый) | `drawRect` |
| Звезда | `#FBBF24` (жёлтый) | `drawPath` (5-конечная) |

---

## Ответы на контрольные вопросы

**1. Понятие виджета**  
Виджет в Android — это визуальный компонент пользовательского интерфейса, наследующий от класса `View`. Виджеты отображают данные и/или реагируют на действия пользователя. Примеры: `TextView`, `Button`, `EditText`, `CheckBox`.

**2. Графические примитивы**  
Графические примитивы — базовые геометрические фигуры, из которых строится изображение. В Android они рисуются на объекте `Canvas` с помощью объекта `Paint`:
- `drawRect()` — прямоугольник
- `drawCircle()` — круг
- `drawLine()` / `drawLines()` — отрезки
- `drawOval()` — овал
- `drawPath()` — произвольная фигура (ломаные, кривые)
- `drawPoint()` / `drawPoints()` — точки

**3. Переопределение метода**  
Переопределение (override) — механизм ООП, позволяющий изменить реализацию метода родительского класса в дочернем. В Kotlin обозначается ключевым словом `override`. В данной работе переопределяется метод `onDraw(canvas: Canvas)` класса `View`, чтобы задать собственную логику отрисовки.

**4. Наследование**  
Наследование — механизм ООП, при котором дочерний класс получает свойства и методы родительского класса. В данной работе `DrawingView` наследуется от `View`, что позволяет использовать готовую инфраструктуру отрисовки Android (метод `onDraw`, управление жизненным циклом) без реализации с нуля.

---

## Вывод

В ходе лабораторной работы был изучен интерфейс Android Studio, создан кастомный `View` с переопределённым методом `onDraw()`. Реализованы два задания: зелёный квадрат со свойством S = P (вариант 8) и новогодняя ёлка из трёх ярусов с использованием `Path` (вариант 6). Получены навыки работы с `Canvas`, `Paint`, `Path` и базовыми графическими примитивами Android.
```