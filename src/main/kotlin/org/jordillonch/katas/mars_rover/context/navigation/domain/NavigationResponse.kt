package org.jordillonch.katas.mars_rover.context.navigation.domain

data class NavigationResponse(val status: Status, val commandsExecuted: List<Commands>)

enum class Status {
    SUCCESS
}
