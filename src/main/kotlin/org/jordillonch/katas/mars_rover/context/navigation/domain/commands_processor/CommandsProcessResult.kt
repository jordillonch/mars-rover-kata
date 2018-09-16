package org.jordillonch.katas.mars_rover.context.navigation.domain.commands_processor

import org.jordillonch.katas.mars_rover.context.navigation.domain.Command
import org.jordillonch.katas.mars_rover.context.navigation.domain.Fail
import org.jordillonch.katas.mars_rover.context.navigation.domain.OutOfMap
import org.jordillonch.katas.mars_rover.context.navigation.domain.Pose
import org.jordillonch.katas.mars_rover.context.navigation.domain.Status
import org.jordillonch.katas.mars_rover.context.navigation.domain.Success
import org.jordillonch.katas.mars_rover.context.navigation.domain.ThereIsAObstacle
import org.jordillonch.katas.mars_rover.context.navigation.domain.navigation_system.NavigateFailOutOfMapResponse
import org.jordillonch.katas.mars_rover.context.navigation.domain.navigation_system.NavigateFailThereIsAObstacleResponse
import org.jordillonch.katas.mars_rover.context.navigation.domain.navigation_system.NavigatePositionResponse
import org.jordillonch.katas.mars_rover.context.navigation.domain.navigation_system.NavigateResponse

data class CommandsProcessResult(val status: Status, val pose: Pose, val commandsExecuted: List<Command>)

fun CommandsProcessResult.toNavigateResponse(id: String): NavigateResponse {
    return NavigateResponse(id,
                                                                                                         when (status) {
                                                                                                             is Success -> "success"
                                                                                                             is Fail    -> "fail"
                                                                                                         },
                                                                                                         when (status) {
                                                                                                             is Fail -> when (status.reason) {
                                                                                                                 is OutOfMap         ->
                                                                                                                     NavigateFailOutOfMapResponse(
                                                                                                                             NavigatePositionResponse(
                                                                                                                                     status.reason.outPosition.x,
                                                                                                                                     status.reason.outPosition.y))
                                                                                                                 is ThereIsAObstacle ->
                                                                                                                     NavigateFailThereIsAObstacleResponse(
                                                                                                                             NavigatePositionResponse(
                                                                                                                                     status.reason.obstaclePosition.x,
                                                                                                                                     status.reason.obstaclePosition.y))

                                                                                                             }
                                                                                                             else    -> null
                                                                                                         })
}
