package org.jordillonch.katas.mars_rover.application

import com.google.gson.FieldNamingPolicy
import com.typesafe.config.ConfigFactory
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.config.HoconApplicationConfig
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.server.engine.ApplicationEngineEnvironmentBuilder
import io.ktor.server.engine.applicationEngineEnvironment
import io.ktor.server.engine.connector
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.netty.NettyApplicationEngine
import org.slf4j.LoggerFactory
import org.slf4j.event.Level
import java.text.DateFormat

fun Application.module() {
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
    }
}

fun main(args: Array<String>) {
    val env = environment(args)

    val nettyEngineEnvironment = applicationEngineEnvironment {
        loadLogger()
        log.info("Environment: $env")
        loadConfiguration(env)
    }

    val nettyEngineConfiguration = {
        NettyApplicationEngine.Configuration()
    }
    embeddedServer(factory = Netty,
                   environment = nettyEngineEnvironment,
                   configure = {
                       @Suppress("UNUSED_EXPRESSION")
                       nettyEngineConfiguration
                   })
            .start(wait = true)
}

private fun environment(args: Array<String>) =
        args.find { it.contains("-env=") }?.replace("-env=", "")?.toLowerCase() ?: "dev"

private fun ApplicationEngineEnvironmentBuilder.loadConfiguration(env: String) {
    val configFile = "application-$env.conf"
    log.info("Loading production configuration <$configFile>")
    config = HoconApplicationConfig(ConfigFactory.load(configFile))
    connector {
        port = config.config("ktor.deployment").property("port").getString().toInt()
    }
}

private fun ApplicationEngineEnvironmentBuilder.loadLogger() {
    log = LoggerFactory.getLogger("ktor.application")
}
