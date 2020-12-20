@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

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
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    val shortAge = age % 100
    if (shortAge in 10..20) return "$age лет"
    val shortSAge = shortAge % 10
    return when {
        shortSAge == 0 || shortSAge in 5..9 -> "$age лет"
        shortSAge == 1 -> "$age год"
        shortSAge in 2..4 -> "$age года"
        else -> "Нет ответа"
    }
}

/**
 * Простая
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
    val path1 = t1 * v1
    val path2 = t2 * v2
    val halfPath = (t1 * v1 + t2 * v2 + t3 * v3) / 2.0
    if (halfPath <= path1) {
        return halfPath / v1
    } else if (halfPath <= (path1 + path2)) {
        return t1 + (halfPath - path1) / v2
    } else {
        return t1 + t2 + (halfPath - path1 - path2) / v3
    }
}

/**
 * Простая
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
    var rook1 = 0
    var rook2 = 0
    if (kingX == rookX1 || kingY == rookY1) {
        rook1 = 1
    }
    if (kingX == rookX2 || kingY == rookY2) {
        rook2 = 2
    }
    return rook1 + rook2
}

/**
 * Простая
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
    var fig1 = 0
    var fig2 = 0
    if (kingX == rookX || kingY == rookY) {
        fig1 = 1
    }
    if (abs(kingX - bishopX) == abs(kingY - bishopY)) {
        fig2 = 2
    }
    return fig1 + fig2
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    val gip = maxOf(a, b, c)
    val kat1: Double
    val kat2: Double
    when (gip) {
        a -> {
            kat1 = b
            kat2 = c
        }
        b -> {
            kat1 = a
            kat2 = c
        }
        else -> {
            kat1 = a
            kat2 = b
        }
    }
    return if (kat1 <= 0 || kat2 <= 0 || kat1 + kat2 <= gip) {
        -1
    } else if (kat1 * kat1 + kat2 * kat2 < gip * gip) {
        2
    } else if (kat1 * kat1 + kat2 * kat2 == gip * gip) {
        1
    } else {
        0
    }
}


/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    var result = 0
    if ((c > b && d > b) || (c < a && d < a)) {
        result = -1
    } else if (b in c..d) {
        result = b - max(a, c)
    } else if (a in c..d) {
        result = min(b, d) - a
    } else if (c in a..b) {
        result = min(d, b) - c
    } else if (d in a..b) {
        result = d - max(a, c)
    }
    return result
}
