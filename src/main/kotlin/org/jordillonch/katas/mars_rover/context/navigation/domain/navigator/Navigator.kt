package org.jordillonch.katas.mars_rover.context.navigation.domain.navigator

import org.jordillonch.katas.mars_rover.context.navigation.domain.Command
import org.jordillonch.katas.mars_rover.context.navigation.domain.Command.BACKWARD
import org.jordillonch.katas.mars_rover.context.navigation.domain.Command.FORWARD
import org.jordillonch.katas.mars_rover.context.navigation.domain.Command.LEFT
import org.jordillonch.katas.mars_rover.context.navigation.domain.Command.RIGHT
import org.jordillonch.katas.mars_rover.context.navigation.domain.Direction.EAST
import org.jordillonch.katas.mars_rover.context.navigation.domain.Direction.NORTH
import org.jordillonch.katas.mars_rover.context.navigation.domain.Direction.SOUTH
import org.jordillonch.katas.mars_rover.context.navigation.domain.Direction.WEST
import org.jordillonch.katas.mars_rover.context.navigation.domain.Pose
import org.jordillonch.katas.mars_rover.context.navigation.domain.Status.SUCCESS
import org.jordillonch.katas.mars_rover.context.navigation.domain.TerritoryMarksMap

class Navigator(private val marsMap: TerritoryMarksMap) {

    operator fun invoke(pose: Pose, command: Command): NavigationResult {
        val newPose = when (command) {
            FORWARD  -> when (pose.direction) {
                NORTH -> pose.copy(position = pose.position.copy(y = pose.position.y + 1))
                EAST  -> pose.copy(position = pose.position.copy(x = pose.position.x + 1))
                SOUTH -> pose.copy(position = pose.position.copy(y = pose.position.y - 1))
                WEST  -> pose.copy(position = pose.position.copy(x = pose.position.x - 1))
            }
            BACKWARD -> when (pose.direction) {
                NORTH -> pose.copy(position = pose.position.copy(y = pose.position.y - 1))
                EAST  -> pose.copy(position = pose.position.copy(x = pose.position.x - 1))
                SOUTH -> pose.copy(position = pose.position.copy(y = pose.position.y + 1))
                WEST  -> pose.copy(position = pose.position.copy(x = pose.position.x + 1))
            }
            LEFT     -> when (pose.direction) {
                NORTH -> pose.copy(direction = WEST)
                EAST  -> pose.copy(direction = NORTH)
                SOUTH -> pose.copy(direction = EAST)
                WEST  -> pose.copy(direction = SOUTH)
            }
            RIGHT    -> when (pose.direction) {
                NORTH -> pose.copy(direction = EAST)
                EAST  -> pose.copy(direction = SOUTH)
                SOUTH -> pose.copy(direction = WEST)
                WEST  -> pose.copy(direction = NORTH)
            }
        }

        return NavigationResult(SUCCESS, newPose)
    }
}
