package be.civadis.atelier.redis.repository

import be.civadis.atelier.redis.model.User
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository: CrudRepository<User, String> {

    fun findByEmail(email: String): Optional<User>

}