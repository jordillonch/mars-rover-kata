package org.jordillonch.katas.mars_rover.context.navigation.adapter

import org.jordillonch.katas.mars_rover.context.navigation.domain.navigation_system.NavigateCommand

internal data class NavigationRouteRequest(val routeId: String, val commands: List<String>)

internal fun NavigationRouteRequest.toCommand() =
        NavigateCommand(routeId, commands)