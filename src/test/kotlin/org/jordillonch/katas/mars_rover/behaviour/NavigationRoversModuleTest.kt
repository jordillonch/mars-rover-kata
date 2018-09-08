package org.jordillonch.katas.mars_rover.behaviour

import io.kotlintest.shouldBe
import io.kotlintest.specs.ShouldSpec
import org.jordillonch.katas.mars_rover.context.navigation.domain.Commands.FORWARD
import org.jordillonch.katas.mars_rover.context.navigation.domain.NavigationResponse
import org.jordillonch.katas.mars_rover.context.navigation.domain.NavigationRoversModule
import org.jordillonch.katas.mars_rover.context.navigation.domain.Status

class NavigationRoversModuleTest : ShouldSpec(
        {
            val navigationRoversModule = NavigationRoversModule()

            should("move forward 3 steps") {
                val commands = listOf(FORWARD, FORWARD, FORWARD)
                val response = navigationRoversModule.execute(commands)

                response.shouldBe(NavigationResponse(Status.SUCCESS, commands))
            }
        })
