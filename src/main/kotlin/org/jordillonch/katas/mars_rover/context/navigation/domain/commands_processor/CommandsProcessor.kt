package org.jordillonch.katas.mars_rover.context.navigation.domain.commands_processor

import org.jordillonch.katas.mars_rover.context.navigation.domain.Command
import org.jordillonch.katas.mars_rover.context.navigation.domain.Pose
import org.jordillonch.katas.mars_rover.context.navigation.domain.navigator.Navigator
import org.jordillonch.katas.mars_rover.context.navigation.domain.Status.SUCCESS

class CommandsProcessor(private val navigator: Navigator) {

    operator fun invoke(initialPose: Pose, commands: List<Command>): CommandsProcessResult {
        return executeCommands(initialPose, commands, emptyList())
    }

    private tailrec fun executeCommands(currentPose: Pose,
                                        commands: List<Command>,
                                        executedCommands: List<Command>): CommandsProcessResult {
        return when (commands) {
            emptyList<Command>() -> CommandsProcessResult(
                    SUCCESS,
                    currentPose,
                    executedCommands)
            else                 -> {
                val commandToExecute = commands.first()
                val result = navigator(currentPose, commandToExecute)
                when (result.status) {
                    SUCCESS -> executeCommands(result.pose,
                                               commands.drop(1),
                                               executedCommands + commandToExecute)
                }
            }
        }
    }
}


