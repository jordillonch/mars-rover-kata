package org.jordillonch.katas.mars_rover.behaviour.commands_processor

import io.kotlintest.shouldBe
import io.kotlintest.specs.ShouldSpec
import org.jordillonch.katas.mars_rover.context.navigation.domain.Command.FORWARD
import org.jordillonch.katas.mars_rover.context.navigation.domain.Direction.NORTH
import org.jordillonch.katas.mars_rover.context.navigation.domain.Fail
import org.jordillonch.katas.mars_rover.context.navigation.domain.MapSize
import org.jordillonch.katas.mars_rover.context.navigation.domain.Obstacle
import org.jordillonch.katas.mars_rover.context.navigation.domain.OutOfMap
import org.jordillonch.katas.mars_rover.context.navigation.domain.Pose
import org.jordillonch.katas.mars_rover.context.navigation.domain.Position
import org.jordillonch.katas.mars_rover.context.navigation.domain.Success
import org.jordillonch.katas.mars_rover.context.navigation.domain.TerritoryMarksMap
import org.jordillonch.katas.mars_rover.context.navigation.domain.ThereIsAObstacle
import org.jordillonch.katas.mars_rover.context.navigation.domain.commands_processor.CommandsProcessResult
import org.jordillonch.katas.mars_rover.context.navigation.domain.commands_processor.CommandsProcessor
import org.jordillonch.katas.mars_rover.context.navigation.domain.navigator.Navigator

class CommandsProcessorTest : ShouldSpec(
        {
            val marks = mapOf(
                    Position(0, 10) to Obstacle,
                    Position(0, -10) to Obstacle)
            val marsMap = TerritoryMarksMap(MapSize(Position(-100, 100),
                                                    Position(100, -100)),
                                            marks)
            val navigator = Navigator(marsMap)
            val commandsProcessor = CommandsProcessor(navigator)

            should("move forward 3 steps") {
                val initialPose = Pose(Position(0, 0), NORTH)
                val commands = listOf(FORWARD, FORWARD, FORWARD)
                commandsProcessor(initialPose, commands)
                        .shouldBe(CommandsProcessResult(Success,
                                                        Pose(Position(0, 3), NORTH),
                                                        commands))
            }

            should("move forward until out of map") {
                val initialPose = Pose(Position(99, 99), NORTH)
                val commands = listOf(FORWARD, FORWARD, FORWARD)
                commandsProcessor(initialPose, commands)
                        .shouldBe(CommandsProcessResult(Fail(OutOfMap(Position(99, 101))),
                                                        Pose(Position(99, 100), NORTH),
                                                        listOf(FORWARD, FORWARD)))
            }

            should("move forward until obstacle") {
                val initialPose = Pose(Position(0, 8), NORTH)
                val commands = listOf(FORWARD, FORWARD, FORWARD)
                commandsProcessor(initialPose, commands)
                        .shouldBe(CommandsProcessResult(Fail(ThereIsAObstacle(Position(0, 10))),
                                                        Pose(Position(0, 9), NORTH),
                                                        listOf(FORWARD, FORWARD)))
            }
        })
