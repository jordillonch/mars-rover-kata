package org.jordillonch.katas.mars_rover.context.navigation.domain.navigator

import org.jordillonch.katas.mars_rover.context.navigation.domain.Command
import org.jordillonch.katas.mars_rover.context.navigation.domain.Fail
import org.jordillonch.katas.mars_rover.context.navigation.domain.OutOfMap
import org.jordillonch.katas.mars_rover.context.navigation.domain.Pose
import org.jordillonch.katas.mars_rover.context.navigation.domain.Success
import org.jordillonch.katas.mars_rover.context.navigation.domain.TerritoryMarksMap
import org.jordillonch.katas.mars_rover.context.navigation.domain.ThereIsAObstacle
import org.jordillonch.katas.mars_rover.context.navigation.domain.calculateNewPose
import org.jordillonch.katas.mars_rover.context.navigation.domain.isOutOfBounds
import org.jordillonch.katas.mars_rover.context.navigation.domain.thereIsAObstacle

class Navigator(private val marsMap: TerritoryMarksMap) {

    operator fun invoke(pose: Pose, command: Command): NavigationResult {
        val newPose = pose.calculateNewPose(command)

        return when {
            marsMap.isOutOfBounds(newPose.position)    -> NavigationResult(Fail(reason = OutOfMap(newPose.position)), pose)
            marsMap.thereIsAObstacle(newPose.position) -> NavigationResult(Fail(reason = ThereIsAObstacle(newPose.position)), pose)
            else                                       -> NavigationResult(Success, newPose)
        }
    }
}
