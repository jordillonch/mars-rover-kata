package org.jordillonch.katas.mars_rover.context.navigation.domain

data class Position(val x: Int, val y: Int) {
    companion object {
        fun origin() = Position(0, 0)
    }
}
