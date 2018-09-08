package org.jordillonch.katas.mars_rover.context.navigation.domain.navigator

import org.jordillonch.katas.mars_rover.context.navigation.domain.Pose
import org.jordillonch.katas.mars_rover.context.navigation.domain.Status

data class NavigationResult(val status: Status, val pose: Pose)
