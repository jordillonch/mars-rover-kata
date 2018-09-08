package org.jordillonch.katas.mars_rover.context.navigation.domain

data class TerritoryMarksMap(val size: MapSize, val marks: Map<Position, Mark>)

enum class Mark {
    UNKNOWN,
    OBSTACLE
}

data class MapSize(val quadrantPositionLeftUp: Position, val quadrantPositionRightDown: Position) {
    fun minX() = quadrantPositionLeftUp.x
    fun maxX() = quadrantPositionRightDown.x
    fun minY() = quadrantPositionRightDown.y
    fun maxY() = quadrantPositionLeftUp.y
}
