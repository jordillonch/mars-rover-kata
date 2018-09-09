package org.jordillonch.katas.mars_rover.context.navigation.domain.navigator

import org.jordillonch.katas.mars_rover.context.navigation.domain.Command
import org.jordillonch.katas.mars_rover.context.navigation.domain.Pose
import org.jordillonch.katas.mars_rover.context.navigation.domain.Status.SUCCESS
import org.jordillonch.katas.mars_rover.context.navigation.domain.TerritoryMarksMap
import org.jordillonch.katas.mars_rover.context.navigation.domain.calculateNewPose

class Navigator(private val marsMap: TerritoryMarksMap) {

    operator fun invoke(pose: Pose, command: Command): NavigationResult {
        val newPose = pose.calculateNewPose(command)

        return NavigationResult(SUCCESS, newPose)
    }
}
