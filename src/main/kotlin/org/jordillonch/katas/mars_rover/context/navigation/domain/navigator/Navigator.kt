package org.jordillonch.katas.mars_rover.context.navigation.domain.navigator

import org.jordillonch.katas.mars_rover.context.navigation.domain.Command
import org.jordillonch.katas.mars_rover.context.navigation.domain.Pose
import org.jordillonch.katas.mars_rover.context.navigation.domain.Status
import org.jordillonch.katas.mars_rover.context.navigation.domain.TerritoryMarksMap

class Navigator(private val marsMap: TerritoryMarksMap) {

    operator fun invoke(pose: Pose, command: Command): NavigationResult {
        return NavigationResult(Status.SUCCESS, pose)
    }
}
