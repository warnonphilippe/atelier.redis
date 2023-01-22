package be.civadis.atelier.redis.model

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("User")
class User(@Id var username: String, var email: String)