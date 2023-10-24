package org.khw.shoppera

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class ShopperaApplication

fun main(args: Array<String>) {
    runApplication<ShopperaApplication>(*args)
}
