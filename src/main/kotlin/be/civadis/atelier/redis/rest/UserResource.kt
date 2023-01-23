package be.civadis.atelier.redis.rest

import be.civadis.atelier.redis.model.User
import be.civadis.atelier.redis.repository.UserRepository
import be.civadis.atelier.redis.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/users")
class UserResource (val userService: UserService, val repository: UserRepository){

    companion object {
        val logger = LoggerFactory.getLogger(UserResource::class.java)
    }

    @PostMapping()
    @PutMapping
    fun saveUser(@RequestBody user: User): ResponseEntity<User> {
        var savedUser = userService.saveUser(user)
        return ResponseEntity(savedUser, HttpStatus.OK)
    }

    @GetMapping()
    fun allUser(): ResponseEntity<List<User>> {
        logger.info("List ALL users")
        val users = repository.findAll().toList()
        return ResponseEntity(users, HttpStatus.OK)
    }

    @GetMapping("/{username}")
    fun findUserByUsername(@PathVariable username: String): ResponseEntity<Optional<User>>{
        val user = repository.findById(username)
        if (user.isPresent){
            return ResponseEntity(user, HttpStatus.OK)
        } else {
            return ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/email/{email}")
    fun findUserByEmail(@PathVariable email: String): ResponseEntity<Optional<User>>{
        val user = repository.findByEmail(email)
        if (user.isPresent){
            return ResponseEntity(user, HttpStatus.OK)
        } else {
            return ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{username}")
    fun deleteUserByUsername(@PathVariable username: String): ResponseEntity<String>{
        var deletedUsername = userService.deleteUser(username)
        if (deletedUsername.isPresent){
            repository.deleteById(username)
            return ResponseEntity(username + " deleted", HttpStatus.OK)
        } else {
            return ResponseEntity.notFound().build()
        }
    }

}