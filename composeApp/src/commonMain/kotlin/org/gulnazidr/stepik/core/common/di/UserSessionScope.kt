package org.gulnazidr.stepik.core.common.di

import org.koin.core.qualifier.named

object UserSessionScope {
    val USER_SESSION_SCOPE = named("userSessionScope")
}