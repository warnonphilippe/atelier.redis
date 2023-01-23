package be.civadis.atelier.redis.service

import be.civadis.atelier.redis.model.User
import be.civadis.atelier.redis.repository.UserRepository
import org.jetbrains.annotations.NotNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestBody
import java.util.*

@Service
@Transactional
@Validated
class UserService(val repository: UserRepository) {

    fun saveUser(@NotNull user: User): User {
        var userFound = repository.findById(user.username)
        if (userFound.isEmpty){
            return repository.save(user)
        } else {
            userFound.get().email = user.email;
            return userFound.get()
        }
    }

    fun deleteUser(@NotNull username : String) : Optional<String> {
        if (repository.existsById(username)) {
            repository.deleteById(username)
            return Optional.of(username)
        }
        return Optional.empty()
    }
}


