package org.jordillonch.katas.mars_rover.context.navigation.domain.navigation_system

import org.jordillonch.commons.cqrs.command.domain.Command

data class NavigateCommand(val id: String,
                           val commands: List<String>) : Command

fun NavigateCommand.toListOfCommands() = commands.map { org.jordillonch.katas.mars_rover.context.navigation.domain.Command.valueOf(it) }
