package org.jordillonch.katas.mars_rover.context.navigation.domain

data class TerritoryMarksMap(val size: MapSize, val marks: MapMarks)

typealias MapMarks = Map<Position, Mark>

sealed class Mark
object Unknown : Mark()
object Obstacle : Mark()

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

fun MapMarks.getMark(position: Position) = getOrDefault(position, Unknown)

fun TerritoryMarksMap.thereIsAObstacle(position: Position) = marks.getMark(position) is Obstacle
