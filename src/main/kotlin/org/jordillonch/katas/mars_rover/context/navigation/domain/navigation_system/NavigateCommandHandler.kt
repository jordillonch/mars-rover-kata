package org.jordillonch.katas.mars_rover.context.navigation.domain.navigation_system

import org.jordillonch.commons.cqrs.command.domain.CommandHandler
import org.jordillonch.katas.mars_rover.context.navigation.domain.commands_processor.toNavigateResponse

class NavigateCommandHandler(private val navigationSystem: NavigationSystem) : CommandHandler<NavigateCommand, NavigateResponse> {
    override suspend fun on(command: NavigateCommand): NavigateResponse =
            navigationSystem.navigate(command.toListOfCommands())
                    .toNavigateResponse(command.id)
}

data class NavigateResponse(val id: String, val status: String, val failReason: NavigateFailResponse?)
interface NavigateFailResponse
data class NavigateFailOutOfMapResponse(val outPosition: NavigatePositionResponse) : NavigateFailResponse
data class NavigateFailThereIsAObstacleResponse(val obstaclePosition: NavigatePositionResponse) : NavigateFailResponse
data class NavigatePositionResponse(val x: Int, val y: Int)