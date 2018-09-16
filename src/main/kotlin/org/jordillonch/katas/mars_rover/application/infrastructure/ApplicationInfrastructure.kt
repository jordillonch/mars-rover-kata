package org.jordillonch.katas.mars_rover.application.infrastructure

import org.jordillonch.commons.cqrs.command.domain.CommandBus
import org.jordillonch.commons.cqrs.command.infrastructure.SuspendingCommandBus

data class ApplicationInfrastructure(val commandBus: CommandBus)

fun initApplicationInfrastructure() = ApplicationInfrastructure(commandBus())

private fun commandBus(): CommandBus = SuspendingCommandBus()
