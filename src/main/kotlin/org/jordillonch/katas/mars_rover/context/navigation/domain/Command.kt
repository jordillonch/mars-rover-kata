package org.jordillonch.katas.mars_rover.context.navigation.domain

enum class Command {
    FORWARD,
    BACKWARD,
    LEFT,
    RIGHT
}

fun Pose.calculateNewPose(command: Command): Pose {
    return when (command) {
        Command.FORWARD  -> when (direction) {
            Direction.NORTH -> copy(position = position.copy(y = position.y + 1))
            Direction.EAST  -> copy(position = position.copy(x = position.x + 1))
            Direction.SOUTH -> copy(position = position.copy(y = position.y - 1))
            Direction.WEST  -> copy(position = position.copy(x = position.x - 1))
        }
        Command.BACKWARD -> when (direction) {
            Direction.NORTH -> copy(position = position.copy(y = position.y - 1))
            Direction.EAST  -> copy(position = position.copy(x = position.x - 1))
            Direction.SOUTH -> copy(position = position.copy(y = position.y + 1))
            Direction.WEST  -> copy(position = position.copy(x = position.x + 1))
        }
        Command.LEFT     -> when (direction) {
            Direction.NORTH -> copy(direction = Direction.WEST)
            Direction.EAST  -> copy(direction = Direction.NORTH)
            Direction.SOUTH -> copy(direction = Direction.EAST)
            Direction.WEST  -> copy(direction = Direction.SOUTH)
        }
        Command.RIGHT    -> when (direction) {
            Direction.NORTH -> copy(direction = Direction.EAST)
            Direction.EAST  -> copy(direction = Direction.SOUTH)
            Direction.SOUTH -> copy(direction = Direction.WEST)
            Direction.WEST  -> copy(direction = Direction.NORTH)
        }
    }
}
