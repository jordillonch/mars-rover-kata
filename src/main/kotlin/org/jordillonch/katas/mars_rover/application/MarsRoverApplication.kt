package org.jordillonch.katas.mars_rover.application

import com.google.gson.FieldNamingPolicy
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import navigator
import org.jordillonch.katas.mars_rover.application.infrastructure.initApplicationInfrastructure
import org.jordillonch.katas.mars_rover.application.infrastructure.startEmbeddedServer
import org.jordillonch.katas.mars_rover.context.navigation.config.configureNavigationContext
import org.slf4j.event.Level
import java.text.DateFormat

fun Application.module() {
    val applicationInfrastructure = initApplicationInfrastructure()
    configureNavigationContext(applicationInfrastructure)
    
    install(DefaultHeaders)
    install(CallLogging) {
        level = Level.INFO
    }
    install(ContentNegotiation) {
        gson {
            setDateFormat(DateFormat.LONG)
            setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            setPrettyPrinting()
        }
    }
    install(Routing) {
        get("/health") {
            call.respond(HttpStatusCode.OK)
        }
        navigator(applicationInfrastructure.commandBus)
    }
}

fun main(args: Array<String>) {
    startEmbeddedServer(args)
}
