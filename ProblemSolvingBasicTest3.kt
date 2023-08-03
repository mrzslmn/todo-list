fun convertToMilitaryTime(time12Hour: String): String {
    val timeFormat = time12Hour.takeLast(2).toUpperCase()
    val (hour, minute, second) = time12Hour.dropLast(4).split(":").map { it.toInt() }

    val militaryHour = when {
        timeFormat == "PM" && hour != 12 -> hour + 12
        timeFormat == "AM" && hour == 12 -> 0
        else -> hour
    }

    return String.format("%02d:%02d:%02d", militaryHour, minute, second)
}

fun main() {
    if (args.size != 1) {
        println("Usage: KotlinApp <time12Hour>")
        return
    }

    val time12Hour = args[0]
    println("Input Time: $time12Hour")
    println("Converted Time: ${convertToMilitaryTime(time12Hour)}")
}
