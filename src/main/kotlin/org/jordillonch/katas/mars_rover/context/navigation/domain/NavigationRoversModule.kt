package org.jordillonch.katas.mars_rover.context.navigation.domain

import org.jordillonch.katas.mars_rover.context.navigation.domain.Status.SUCCESS

class NavigationRoversModule(private val marsMap: TerritoryMarksMap,
                             private val initialPose: Pose) {
    fun execute(commands: List<Commands>): NavigationResult {
        val newPose = initialPose
        
        return NavigationResult(SUCCESS, newPose, commands)
    }
}

data class TerritoryMarksMap(val size: MapSize, val marks: Map<Position, Mark>)

data class MapSize(val quadrantPositionLeftUp: Position, val quadrantPositionRightDown: Position) {
    fun minX() = quadrantPositionLeftUp.x
    fun maxX() = quadrantPositionRightDown.x
    fun minY() = quadrantPositionRightDown.y
    fun maxY() = quadrantPositionLeftUp.y
}

sealed class Mark {
    class Unknown
    class Obstacle
}


data class Pose(val position: Position, val direction: Direction)

data class Position(val x: Int, val y: Int)

enum class Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
}