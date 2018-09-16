package org.jordillonch.katas.mars_rover.application.infrastructure

import com.typesafe.config.ConfigFactory
import io.ktor.config.HoconApplicationConfig
import io.ktor.server.engine.ApplicationEngineEnvironmentBuilder
import io.ktor.server.engine.applicationEngineEnvironment
import io.ktor.server.engine.connector
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.netty.NettyApplicationEngine
import org.slf4j.LoggerFactory

fun startEmbeddedServer(args: Array<String>) {
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
