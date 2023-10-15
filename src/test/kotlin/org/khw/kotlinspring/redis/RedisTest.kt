package org.khw.shoppera.redis

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate

@SpringBootTest
class RedisTest() {

    @Autowired
    lateinit var redisTemplate: RedisTemplate<String, String>


    @Test
    fun redis(){
        val redis = redisTemplate.opsForHash<String, String>()
        redis.put("노래", "10cm", "부동의 첫사랑")
    }
}