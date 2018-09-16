package org.jordillonch.test.commons.faker

import com.github.javafaker.Faker as JavaFaker

class Faker {
    companion object {
        private val faker = JavaFaker()

        fun instance(): JavaFaker = faker
    }
}
