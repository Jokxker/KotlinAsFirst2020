@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sqrt

// Урок 2: ветвления (здесь), логический тип (см. 2.2).
// Максимальное количество баллов = 6
// Рекомендуемое количество баллов = 5
// Вместе с предыдущими уроками = 9/12

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая (2 балла)
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    return when {
        age % 10 == 0 || age > 4 && age < 20 || age > 104 && age < 120 -> "$age лет"
        age % 10 == 1 -> "$age год"
        age % 10 > 4 -> "$age лет"
        age % 10 > 1 -> "$age года"
        else -> "$age"
    }
}
/**
 * Простая (2 балла)
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val dis = ((t1 * v1) + (t2 * v2) + (t3 * v3)) / 2
    val dis1 = t1 * v1
    val dis2 = t2 * v2
    val dis3 = t3 * v3
    return when {
        dis1 > dis -> dis / v1
        (dis1 + dis2) > dis -> t1 + ((dis - dis1) / v2)
        else -> (dis - dis1 - dis2) / v3 + t1 + t2
    }
}
/**
 * Простая (2 балла)
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    val kX = kingX
    val kY = kingY
    val rX1 = rookX1
    val rY1 = rookY1
    val rX2 = rookX2
    val rY2 = rookY2
    return when {
        (rX1 == kX || rY1 == kY) && (rX2 == kX || rY2 == kY) -> 3
        rX2 == kX || rY2 == kY -> 2
        rX1 == kX || rY1 == kY -> 1
        else -> 0
    }
}

/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    val kX = kingX
    val kY = kingY
    val rX = rookX
    val rY = rookY
    val bX = bishopX
    val bY = bishopY
    return when {
        (rX == kX || rY == kY) && (bX - bY == kX - kY || bX + bY == kX + kY || bX - bY < (kX - kY) - 8) -> 3
        bX - bY == kX - kY || bX + bY == kX + kY || bX - bY < (kX - kY) - 8 -> 2
        rX == kX || rY == kY -> 1
        else -> 0
    }
}

/**
 * Простая (2 балла)
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    if ((c < (a + b)) && (a < (b + c)) && (b < (a + c))) {
        val max = maxOf(a, b, c)
        val min = minOf(a, b, c)
        val middle = (a + b + c) - max - min
        val sign = min.pow(2) + middle.pow(2) - max.pow(2)
        return when {
            sign > 0 -> 0
            sign == 0.0 -> 1
            else -> 2
        }
    } else return -1
}

/**
 * Средняя (3 балла)
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    if (a <= d) {
        return when {
            b == c || a == d -> 0
            a < c && b >= d -> d - c
            a > c && b <= d -> b - a
            a > c && b >= d -> d - a
            c < b && d >= b -> b - c
            a == c && b == d -> b - a
            a == c && b < d -> b - a
            a == c && b > d -> d - c
            else -> -1
        }
    } else return -1
}
