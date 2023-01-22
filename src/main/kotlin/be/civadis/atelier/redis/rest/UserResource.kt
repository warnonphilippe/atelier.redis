package be.civadis.atelier.redis.rest

import be.civadis.atelier.redis.model.User
import be.civadis.atelier.redis.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserResource (val repository: UserRepository){

    companion object {
        val logger = LoggerFactory.getLogger(UserResource::class.java)
    }

    @PostMapping()
    fun addPerson(@RequestBody user: User): ResponseEntity<User> {
        logger.info("Add NEW user")
        repository.save(user)
        return ResponseEntity(user, HttpStatus.CREATED)
    }

    @GetMapping()
    fun allPeople(): ResponseEntity<List<User>> {
        logger.info("List ALL users")
        val users = repository.findAll().toList();
        return ResponseEntity(users, HttpStatus.OK)
    }

}