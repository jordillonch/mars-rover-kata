package org.jordillonch.katas.mars_rover.context.navigation.domain.commands_processor

import org.jordillonch.katas.mars_rover.context.navigation.domain.Command
import org.jordillonch.katas.mars_rover.context.navigation.domain.Fail
import org.jordillonch.katas.mars_rover.context.navigation.domain.Pose
import org.jordillonch.katas.mars_rover.context.navigation.domain.Success
import org.jordillonch.katas.mars_rover.context.navigation.domain.navigator.Navigator

class CommandsProcessor(private val navigator: Navigator) {

    operator fun invoke(initialPose: Pose, commands: List<Command>) =
            executeCommands(initialPose, commands, emptyList())

    private tailrec fun executeCommands(currentPose: Pose,
                                        commands: List<Command>,
                                        executedCommands: List<Command>): CommandsProcessResult =
            if (commands.isEmpty()) {
                CommandsProcessResult(Success, currentPose, executedCommands)
            } else {
                val commandToExecute = commands.first()
                val restCommandsToExecute = commands.drop(1)
                val result = navigator(currentPose, commandToExecute)
                val newExecutedCommands = executedCommands + commandToExecute
                when (result.status) {
                    is Fail    -> CommandsProcessResult(result.status, result.pose, newExecutedCommands)
                    is Success -> executeCommands(result.pose, restCommandsToExecute, newExecutedCommands)
                }
            }
}
