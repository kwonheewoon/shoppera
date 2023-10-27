package org.khw.shoppera.common

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.ThreadLocalRandom

class RandomUtilsTest {

    @Test
    fun `주문번호 생성`(){
        val orderNumber = generateOrderNumber()

        Assertions.assertEquals(orderNumber.length, 21)
    }

    fun generateOrderNumber(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")
        val basePart = current.format(formatter)

        val randomPart = generateRandomString(4)

        return "$basePart$randomPart"
    }

    fun generateRandomString(length: Int): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        return (1..length)
            .map { ThreadLocalRandom.current().nextInt(0, chars.length) }
            .map(chars::get)
            .joinToString("")
    }

}