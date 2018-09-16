package org.jordillonch.katas.mars_rover.context.navigation.domain.navigation_system

import org.jordillonch.katas.mars_rover.context.navigation.domain.Command
import org.jordillonch.katas.mars_rover.context.navigation.domain.Pose
import org.jordillonch.katas.mars_rover.context.navigation.domain.commands_processor.CommandsProcessor

class NavigationSystem(initialPose: Pose,
                       private val commandsProcessor: CommandsProcessor) {
    var currentPose = initialPose

    fun navigate(commands: List<Command>) =
            commandsProcessor(currentPose, commands)
                    .also { currentPose = it.pose }
}