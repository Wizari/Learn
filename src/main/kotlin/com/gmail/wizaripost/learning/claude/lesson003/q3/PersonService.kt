package com.gmail.wizaripost.learning.claude.lesson003.q3

import org.springframework.stereotype.Service

@Service
class PersonService {
    fun getCityOrDefault(person: Person): String {
            return person.address?.city ?: "Неизвестно"
    }
}
