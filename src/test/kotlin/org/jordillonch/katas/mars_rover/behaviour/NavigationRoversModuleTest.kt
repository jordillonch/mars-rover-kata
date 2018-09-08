package org.jordillonch.katas.mars_rover.behaviour

import io.kotlintest.shouldBe
import io.kotlintest.specs.ShouldSpec
import org.jordillonch.katas.mars_rover.context.navigation.domain.Commands.FORWARD
import org.jordillonch.katas.mars_rover.context.navigation.domain.Direction.NORTH
import org.jordillonch.katas.mars_rover.context.navigation.domain.MapSize
import org.jordillonch.katas.mars_rover.context.navigation.domain.Mark
import org.jordillonch.katas.mars_rover.context.navigation.domain.NavigationResult
import org.jordillonch.katas.mars_rover.context.navigation.domain.NavigationRoversModule
import org.jordillonch.katas.mars_rover.context.navigation.domain.Position
import org.jordillonch.katas.mars_rover.context.navigation.domain.Pose
import org.jordillonch.katas.mars_rover.context.navigation.domain.Status.SUCCESS
import org.jordillonch.katas.mars_rover.context.navigation.domain.TerritoryMarksMap

class NavigationRoversModuleTest : ShouldSpec(
        {
            val marks = emptyMap<Position, Mark>()
            val marsMap = TerritoryMarksMap(MapSize(Position(-100, 100),
                                                    Position(100, -100)),
                                            marks)
            val initialPose = Pose(Position(0, 0), NORTH)
            val navigationRoversModule = NavigationRoversModule(marsMap, initialPose)

            should("move forward 3 steps") {
                val commands = listOf(FORWARD, FORWARD, FORWARD)
                val result = navigationRoversModule.execute(commands)

                result.shouldBe(NavigationResult(SUCCESS, Pose(Position(0, 3), NORTH), commands))
            }
        })
