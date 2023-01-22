package be.civadis.atelier.redis.rest

import be.civadis.atelier.redis.model.User
import be.civadis.atelier.redis.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserResource (val repository: UserRepository){

    companion object {
        val logger = LoggerFactory.getLogger(UserResource::class.java)
    }

    @PostMapping()
    fun addPerson(@RequestBody user: User): ResponseEntity<String> {
        logger.info("Add NEW user")
        repository.save(user)
        return ResponseEntity("Saved user - ${user.username}", HttpStatus.CREATED)
    }

    @GetMapping()
    fun allPeople(): ResponseEntity<MutableIterable<User>> {
        logger.info("List ALL users")
        val people = repository.findAll()
        return ResponseEntity(people, HttpStatus.OK)
    }

}