fun main() {
    val numbers = readLine()!!.split(" ").map { it.toLong() }.sorted()

    val minSum = numbers.take(4).sum()
    val maxSum = numbers.takeLast(4).sum()

    println("$minSum $maxSum")
}
