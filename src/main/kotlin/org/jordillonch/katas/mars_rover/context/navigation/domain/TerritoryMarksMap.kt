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

fun TerritoryMarksMap.isOutOfBounds(position: Position) =
        (position.x > size.maxX()
         || position.x < size.minX()
         || position.y > size.maxY()
         || position.y < size.minY())
