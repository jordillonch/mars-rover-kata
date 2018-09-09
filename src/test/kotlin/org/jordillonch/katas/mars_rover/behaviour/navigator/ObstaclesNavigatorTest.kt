package org.jordillonch.katas.mars_rover.behaviour.navigator

import io.kotlintest.shouldBe
import io.kotlintest.specs.ShouldSpec
import org.jordillonch.katas.mars_rover.context.navigation.domain.Command.FORWARD
import org.jordillonch.katas.mars_rover.context.navigation.domain.Direction.NORTH
import org.jordillonch.katas.mars_rover.context.navigation.domain.Fail
import org.jordillonch.katas.mars_rover.context.navigation.domain.MapSize
import org.jordillonch.katas.mars_rover.context.navigation.domain.Obstacle
import org.jordillonch.katas.mars_rover.context.navigation.domain.Pose
import org.jordillonch.katas.mars_rover.context.navigation.domain.Position
import org.jordillonch.katas.mars_rover.context.navigation.domain.TerritoryMarksMap
import org.jordillonch.katas.mars_rover.context.navigation.domain.ThereIsAObstacle
import org.jordillonch.katas.mars_rover.context.navigation.domain.navigator.NavigationResult
import org.jordillonch.katas.mars_rover.context.navigation.domain.navigator.Navigator

class ObstaclesNavigatorTest : ShouldSpec(
        {
            val marks = mapOf(Position(0, 1) to Obstacle)
            val marsMap = TerritoryMarksMap(MapSize(Position(-100, 100),
                                                    Position(100, -100)),
                                            marks)
            val navigator = Navigator(marsMap)

            should("try to move forward but it can't because an obstacle") {
                navigator(Pose(Position.origin(), NORTH), FORWARD)
                        .shouldBe(NavigationResult(Fail(reason = ThereIsAObstacle(Position(0, 1))),
                                                   Pose(Position(0, 0), NORTH)))
            }
        })
