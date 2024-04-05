
data class Point2D(val x: Int, val y: Int) {
    fun neighbors(): Set<Point2D> =
        setOf(
            Point2D(x - 1, y - 1),
            Point2D(x, y - 1),
            Point2D(x + 1, y - 1),
            Point2D(x - 1, y),
            Point2D(x + 1, y),
            Point2D(x - 1, y + 1),
            Point2D(x, y + 1),
            Point2D(x + 1, y + 1)
        )
}

fun main() {
    val point2D = Point2D(2, 3)

    println(point2D.neighbors())

}

