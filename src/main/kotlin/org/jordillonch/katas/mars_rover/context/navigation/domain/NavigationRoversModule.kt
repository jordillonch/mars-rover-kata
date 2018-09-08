package org.jordillonch.katas.mars_rover.context.navigation.domain

class NavigationRoversModule {
    fun execute(commands: List<Commands>): NavigationResponse {
        return NavigationResponse(Status.SUCCESS, commands)
    }
}
