package org.jordillonch.katas.mars_rover.context.navigation.config

import org.jordillonch.katas.mars_rover.application.infrastructure.ApplicationInfrastructure
import org.jordillonch.katas.mars_rover.context.navigation.domain.Direction
import org.jordillonch.katas.mars_rover.context.navigation.domain.MapSize
import org.jordillonch.katas.mars_rover.context.navigation.domain.Obstacle
import org.jordillonch.katas.mars_rover.context.navigation.domain.Pose
import org.jordillonch.katas.mars_rover.context.navigation.domain.Position
import org.jordillonch.katas.mars_rover.context.navigation.domain.TerritoryMarksMap
import org.jordillonch.katas.mars_rover.context.navigation.domain.navigation_system.NavigationSystem
import org.jordillonch.katas.mars_rover.context.navigation.domain.navigation_system.NavigateCommandHandler
import org.jordillonch.katas.mars_rover.context.navigation.domain.commands_processor.CommandsProcessor
import org.jordillonch.katas.mars_rover.context.navigation.domain.navigator.Navigator

fun configureNavigationContext(applicationInfrastructure: ApplicationInfrastructure) {
    val marks = mapOf(
            Position(0, 10) to Obstacle,
            Position(0, -10) to Obstacle)
    val marsMap = TerritoryMarksMap(MapSize(Position(-100, 100),
                                            Position(100, -100)),
                                    marks)
    val navigator = Navigator(marsMap)
    val commandsProcessor = CommandsProcessor(navigator)
    val initialPose = Pose(Position(0, 0), Direction.NORTH)
    val navigationSystem = NavigationSystem(initialPose, commandsProcessor)
    val handler = NavigateCommandHandler(navigationSystem)

    applicationInfrastructure.commandBus.registerHandler(handler)
}