package org.jordillonch.katas.mars_rover.context.navigation.domain.commands_processor

import org.jordillonch.katas.mars_rover.context.navigation.domain.Command
import org.jordillonch.katas.mars_rover.context.navigation.domain.Pose
import org.jordillonch.katas.mars_rover.context.navigation.domain.Status

data class CommandsProcessResult(val status: Status, val pose: Pose, val commandsExecuted: List<Command>)
