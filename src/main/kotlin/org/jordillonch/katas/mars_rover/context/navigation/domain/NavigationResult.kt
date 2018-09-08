package org.jordillonch.katas.mars_rover.context.navigation.domain

data class NavigationResult(val status: Status, val pose: Pose, val commandsExecuted: List<Commands>)

enum class Status {
    SUCCESS
}
