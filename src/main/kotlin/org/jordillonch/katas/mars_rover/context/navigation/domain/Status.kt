package org.jordillonch.katas.mars_rover.context.navigation.domain

sealed class Status
object Success : Status()
data class Fail(val reason: Reason) : Status()

sealed class Reason
data class OutOfMap(val outPosition: Position) : Reason()
data class ThereIsAObstacle(val outPosition: Position) : Reason()
