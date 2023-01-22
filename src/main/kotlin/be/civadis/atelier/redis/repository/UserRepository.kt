package be.civadis.atelier.redis.repository

import be.civadis.atelier.redis.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, String> {}