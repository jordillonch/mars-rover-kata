package org.jordillonch.test.mars_rover.context.navigator.behaviour

import io.kotlintest.shouldBe
import io.kotlintest.specs.ShouldSpec
import org.jordillonch.katas.mars_rover.context.navigation.domain.Command.FORWARD
import org.jordillonch.katas.mars_rover.context.navigation.domain.Direction.EAST
import org.jordillonch.katas.mars_rover.context.navigation.domain.Direction.NORTH
import org.jordillonch.katas.mars_rover.context.navigation.domain.Direction.SOUTH
import org.jordillonch.katas.mars_rover.context.navigation.domain.Direction.WEST
import org.jordillonch.katas.mars_rover.context.navigation.domain.Fail
import org.jordillonch.katas.mars_rover.context.navigation.domain.MapSize
import org.jordillonch.katas.mars_rover.context.navigation.domain.Mark
import org.jordillonch.katas.mars_rover.context.navigation.domain.OutOfMap
import org.jordillonch.katas.mars_rover.context.navigation.domain.Pose
import org.jordillonch.katas.mars_rover.context.navigation.domain.Position
import org.jordillonch.katas.mars_rover.context.navigation.domain.Success
import org.jordillonch.katas.mars_rover.context.navigation.domain.TerritoryMarksMap
import org.jordillonch.katas.mars_rover.context.navigation.domain.navigator.NavigationResult
import org.jordillonch.katas.mars_rover.context.navigation.domain.navigator.Navigator

class OutOfMapDetectionNavigatorTest : ShouldSpec(
        {
            val marks = emptyMap<Position, Mark>()
            val marsMap = TerritoryMarksMap(MapSize(Position(-1, 1),
                                                    Position(1, -1)),
                                            marks)
            val navigator = Navigator(marsMap)

            should("move forward to north direction until out of bounds") {
                Pose(Position.origin(), NORTH)
                        .run { navigator(this, FORWARD) }
                        .also { it.shouldBe(NavigationResult(Success, Pose(Position(0, 1), NORTH))) }
                        .run { navigator(this.pose, FORWARD) }
                        .also {
                            it.shouldBe(NavigationResult(Fail(OutOfMap(Position(0, 2))),
                                                         Pose(Position(0, 1), NORTH)))
                        }
            }

            should("move forward to east direction until out of bounds") {
                Pose(Position.origin(), EAST)
                        .run { navigator(this, FORWARD) }
                        .also { it.shouldBe(NavigationResult(Success, Pose(Position(1, 0), EAST))) }
                        .run { navigator(this.pose, FORWARD) }
                        .also {
                            it.shouldBe(NavigationResult(Fail(OutOfMap(Position(2, 0))),
                                                         Pose(Position(1, 0), EAST)))
                        }
            }

            should("move forward to south direction until out of bounds") {
                Pose(Position.origin(), SOUTH)
                        .run { navigator(this, FORWARD) }
                        .also { it.shouldBe(NavigationResult(Success, Pose(Position(0, -1), SOUTH))) }
                        .run { navigator(this.pose, FORWARD) }
                        .also {
                            it.shouldBe(NavigationResult(Fail(OutOfMap(Position(0, -2))),
                                                         Pose(Position(0, -1), SOUTH)))
                        }
            }

            should("move forward to west direction until out of bounds") {
                Pose(Position.origin(), WEST)
                        .run { navigator(this, FORWARD) }
                        .also { it.shouldBe(NavigationResult(Success, Pose(Position(-1, 0), WEST))) }
                        .run { navigator(this.pose, FORWARD) }
                        .also {
                            it.shouldBe(NavigationResult(Fail(OutOfMap(Position(-2, 0))),
                                                         Pose(Position(-1, 0), WEST)))
                        }
            }
        })
