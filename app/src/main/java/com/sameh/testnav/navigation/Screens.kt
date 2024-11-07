package com.sameh.testnav.navigation

import kotlinx.serialization.Serializable

@Serializable
object Welcome

@Serializable
object Login

@Serializable
object AppNav

@Serializable
object HomeNav

@Serializable
object Home

@Serializable
data class FromHome(
    val id: Int
)

@Serializable
object ProfileNav

@Serializable
object Profile

@Serializable
data class EditProfile(
    val image: String
)

@Serializable
object SettingsNav

@Serializable
object Settings

@Serializable
data class SettingsItem(
    val itemId: Int
)