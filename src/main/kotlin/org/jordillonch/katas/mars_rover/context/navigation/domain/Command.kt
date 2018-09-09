package org.jordillonch.katas.mars_rover.context.navigation.domain

import org.jordillonch.katas.mars_rover.context.navigation.domain.Command.*
import org.jordillonch.katas.mars_rover.context.navigation.domain.Direction.*

enum class Command {
    FORWARD,
    BACKWARD,
    LEFT,
    RIGHT
}

fun Pose.calculateNewPose(command: Command): Pose {
    return when (command) {
        FORWARD  -> when (direction) {
            NORTH -> copy(position = position.copy(y = position.y + 1))
            EAST  -> copy(position = position.copy(x = position.x + 1))
            SOUTH -> copy(position = position.copy(y = position.y - 1))
            WEST  -> copy(position = position.copy(x = position.x - 1))
        }
        BACKWARD -> when (direction) {
            NORTH -> copy(position = position.copy(y = position.y - 1))
            EAST  -> copy(position = position.copy(x = position.x - 1))
            SOUTH -> copy(position = position.copy(y = position.y + 1))
            WEST  -> copy(position = position.copy(x = position.x + 1))
        }
        LEFT     -> when (direction) {
            NORTH -> copy(direction = WEST)
            EAST  -> copy(direction = NORTH)
            SOUTH -> copy(direction = EAST)
            WEST  -> copy(direction = SOUTH)
        }
        RIGHT    -> when (direction) {
            NORTH -> copy(direction = EAST)
            EAST  -> copy(direction = SOUTH)
            SOUTH -> copy(direction = WEST)
            WEST  -> copy(direction = NORTH)
        }
    }
}
