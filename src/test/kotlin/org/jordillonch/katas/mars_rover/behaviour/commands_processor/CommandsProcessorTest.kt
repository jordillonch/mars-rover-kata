package org.jordillonch.katas.mars_rover.behaviour.commands_processor

import io.kotlintest.shouldBe
import io.kotlintest.specs.ShouldSpec
import org.jordillonch.katas.mars_rover.context.navigation.domain.Command.FORWARD
import org.jordillonch.katas.mars_rover.context.navigation.domain.Direction.NORTH
import org.jordillonch.katas.mars_rover.context.navigation.domain.MapSize
import org.jordillonch.katas.mars_rover.context.navigation.domain.Mark
import org.jordillonch.katas.mars_rover.context.navigation.domain.commands_processor.CommandsProcessResult
import org.jordillonch.katas.mars_rover.context.navigation.domain.commands_processor.CommandsProcessor
import org.jordillonch.katas.mars_rover.context.navigation.domain.Position
import org.jordillonch.katas.mars_rover.context.navigation.domain.Pose
import org.jordillonch.katas.mars_rover.context.navigation.domain.Status.SUCCESS
import org.jordillonch.katas.mars_rover.context.navigation.domain.TerritoryMarksMap
import org.jordillonch.katas.mars_rover.context.navigation.domain.navigator.Navigator

class CommandsProcessorTest : ShouldSpec(
        {
            val marks = emptyMap<Position, Mark>()
            val marsMap = TerritoryMarksMap(MapSize(Position(-100, 100),
                                                    Position(100, -100)),
                                            marks)
            val navigator = Navigator(marsMap)
            val commandsProcessor = CommandsProcessor(navigator)

            should("move forward 3 steps") {
                val initialPose = Pose(Position(0, 0), NORTH)
                val commands = listOf(FORWARD, FORWARD, FORWARD)
                val result = commandsProcessor(initialPose, commands)

                result.shouldBe(CommandsProcessResult(
                        SUCCESS,
                        Pose(Position(0, 0), NORTH),
                        commands))
            }
        })
