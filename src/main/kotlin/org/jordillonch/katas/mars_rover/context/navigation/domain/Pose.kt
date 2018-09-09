package org.jordillonch.katas.mars_rover.context.navigation.domain

data class Pose(val position: Position, val direction: Direction)

data class Position(val x: Int, val y: Int) {
    companion object {
        fun origin() = Position(0, 0)
    }
}

enum class Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
}
