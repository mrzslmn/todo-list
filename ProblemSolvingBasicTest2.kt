import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val array = IntArray(n)

    for (i in 0 until n) {
        array[i] = scanner.nextInt()
    }

    scanner.close()

    val positiveCount = array.count { it > 0 }
    val negativeCount = array.count { it < 0 }
    val zeroCount = array.count { it == 0 }

    val positiveRatio = positiveCount.toDouble() / n
    val negativeRatio = negativeCount.toDouble() / n
    val zeroRatio = zeroCount.toDouble() / n

    println("%.6f".format(positiveRatio))
    println("%.6f".format(negativeRatio))
    println("%.6f".format(zeroRatio))
}
