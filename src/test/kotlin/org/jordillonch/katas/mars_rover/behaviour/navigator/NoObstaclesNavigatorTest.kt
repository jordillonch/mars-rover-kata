package org.jordillonch.katas.mars_rover.behaviour.navigator

import io.kotlintest.shouldBe
import io.kotlintest.specs.ShouldSpec
import org.jordillonch.katas.mars_rover.context.navigation.domain.Command.BACKWARD
import org.jordillonch.katas.mars_rover.context.navigation.domain.Command.FORWARD
import org.jordillonch.katas.mars_rover.context.navigation.domain.Command.LEFT
import org.jordillonch.katas.mars_rover.context.navigation.domain.Command.RIGHT
import org.jordillonch.katas.mars_rover.context.navigation.domain.Direction.EAST
import org.jordillonch.katas.mars_rover.context.navigation.domain.Direction.NORTH
import org.jordillonch.katas.mars_rover.context.navigation.domain.Direction.WEST
import org.jordillonch.katas.mars_rover.context.navigation.domain.MapSize
import org.jordillonch.katas.mars_rover.context.navigation.domain.Mark
import org.jordillonch.katas.mars_rover.context.navigation.domain.Pose
import org.jordillonch.katas.mars_rover.context.navigation.domain.Position
import org.jordillonch.katas.mars_rover.context.navigation.domain.Status.SUCCESS
import org.jordillonch.katas.mars_rover.context.navigation.domain.TerritoryMarksMap
import org.jordillonch.katas.mars_rover.context.navigation.domain.navigator.NavigationResult
import org.jordillonch.katas.mars_rover.context.navigation.domain.navigator.Navigator

class NoObstaclesNavigatorTest : ShouldSpec(
        {
            val marks = emptyMap<Position, Mark>()
            val marsMap = TerritoryMarksMap(MapSize(Position(-100, 100),
                                                    Position(100, -100)),
                                            marks)
            val navigator = Navigator(marsMap)

            should("move forward from origin") {
                navigator(Pose(Position.origin(), NORTH), FORWARD)
                        .shouldBe(NavigationResult(SUCCESS, Pose(Position(0, 1), NORTH)))
            }

            should("move backward from origin") {
                navigator(Pose(Position.origin(), NORTH), BACKWARD)
                        .shouldBe(NavigationResult(SUCCESS, Pose(Position(0, -1), NORTH)))
            }

            should("move left from origin") {
                navigator(Pose(Position.origin(), NORTH), LEFT)
                        .shouldBe(NavigationResult(SUCCESS, Pose(Position(0, 0), WEST)))
            }

            should("move right from origin") {
                navigator(Pose(Position.origin(), NORTH), RIGHT)
                        .shouldBe(NavigationResult(SUCCESS, Pose(Position(0, 0), EAST)))
            }
        })