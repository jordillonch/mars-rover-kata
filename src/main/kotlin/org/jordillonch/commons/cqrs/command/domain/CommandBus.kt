package org.jordillonch.commons.cqrs.command.domain

interface Command

interface CommandHandler<in Q : Command, R> {
    suspend fun on(command: Q): R
}

interface CommandBus {
    fun <Q : Command> registerHandler(handler: CommandHandler<Q, *>)
    suspend fun <R> handle(command: Command): R
}

class NoCommandHandlerFound : RuntimeException()
