@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.pow

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
    sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая (2 балла)
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean {
    val num1 = number / 1000
    val num2 = (number / 100) % 10
    val num3 = (number / 10) % 10
    val num4 = number % 10
    return num1 + num2 == num3 + num4
}

/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean {
    val queen = abs(x1 - x2) == abs(y1 - y2)
    return x1 == x2 || y1 == y2 || queen
}


/**
 * Простая (2 балла)
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int {
    val m = month
    val y = year
    if (y % 4 != 0 || y % 100 == 0 && y % 400 != 0) {
        return when {
            m == 2 -> 28
            m >= 7 && m % 2 == 0 -> 31
            m <= 7 && m % 2 > 0 -> 31
            else -> 30
        }
    } else {
        return when {
            m == 2 -> 29
            m >= 7 && m % 2 == 0 -> 31
            m <= 7 && m % 2 > 0 -> 31
            else -> 30
        }
    }
}

/**
 * Простая (2 балла)
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(
    x1: Double, y1: Double, r1: Double,
    x2: Double, y2: Double, r2: Double
): Boolean {
        return r1 <= r2 && (x1 - x2).pow(2) + (y1 - y2).pow(2) <= (r2 - r1).pow(2)
}

/**
 * Средняя (3 балла)
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean {
    val max = maxOf(a, b, c)
    val min = minOf(a, b, c)
    val midl = (a + b + c) - max - min
    return min <= minOf(r, s) && midl <= maxOf(r, s)
}
