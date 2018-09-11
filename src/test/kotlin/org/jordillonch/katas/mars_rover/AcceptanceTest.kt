package org.jordillonch.katas.mars_rover

import com.typesafe.config.ConfigFactory
import io.kotlintest.specs.AbstractShouldSpec
import io.kotlintest.specs.ShouldSpec
import io.ktor.config.HoconApplicationConfig
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.createTestEnvironment
import org.jordillonch.katas.mars_rover.application.module

abstract class AcceptanceTest(body: AbstractShouldSpec.() -> Unit = {}) : ShouldSpec(body) {
    companion object {
        protected val configuration = HoconApplicationConfig(ConfigFactory.load("application-test.conf"))
        val testApplicationEngine = TestApplicationEngine(
                createTestEnvironment {
                    config = configuration
                }).also {
            it.start(wait = true)
            it.application.module()
        }
    }
}
