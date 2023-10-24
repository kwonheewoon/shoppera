package org.khw.shoppera.common.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.ThreadLocalRandom

class RandomUtils {

    companion object{
        private val CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"

        private val DATE_TIME_WITH_MILLIS_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")

        fun generateOrderNumber(): String {
            val current = LocalDateTime.now()
            val basePart = current.format(DATE_TIME_WITH_MILLIS_FORMATTER)
            val randomPart = generateRandomString(4)

            return "$basePart$randomPart"
        }

        fun generateRandomString(length: Int): String {
            return (1..length)
                .map { ThreadLocalRandom.current().nextInt(0, CHARS.length) }
                .map(CHARS::get)
                .joinToString("")
        }
    }
}