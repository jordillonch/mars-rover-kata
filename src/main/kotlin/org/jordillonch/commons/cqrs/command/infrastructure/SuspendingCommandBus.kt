package org.jordillonch.commons.cqrs.command.infrastructure

import org.jordillonch.commons.cqrs.command.domain.NoCommandHandlerFound
import org.jordillonch.commons.cqrs.command.domain.Command
import org.jordillonch.commons.cqrs.command.domain.CommandBus
import org.jordillonch.commons.cqrs.command.domain.CommandHandler
import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.jvm.jvmErasure

class SuspendingCommandBus : CommandBus {
    private val handlers: MutableMap<String, CommandHandler<Command, Any>> = mutableMapOf()

    override fun <Q : Command> registerHandler(handler: CommandHandler<Q, *>) {
        @Suppress("UNCHECKED_CAST")
        handlers[classFrom(handler)] = handler as CommandHandler<Command, Any>
    }

    override suspend fun <R> handle(command: Command): R {
        @Suppress("UNCHECKED_CAST")
        return handlers[command::class.qualifiedName]
                       ?.on(command) as R
               ?: throw NoCommandHandlerFound()
    }

    private fun <Q : Command> classFrom(handler: CommandHandler<Q, *>) =
            handler.javaClass.kotlin
                    .declaredFunctions
                    .firstFunctionNamedOn()
                    .mapParameterTypes()
                    .first { it.isSubclassOf(Command::class) }
                    .qualifiedName!!

    private fun Collection<KFunction<*>>.firstFunctionNamedOn() = first { it.name == "on" }

    private fun KFunction<*>.mapParameterTypes() = parameters.map { it.type.jvmErasure }
}
