package kambi.victor.walenje.di

import kambi.victor.walenje.core.ui.di.coreUiModules
import kambi.victor.walenje.feature.welcome.di.welcomeModules

val commonModule = listOf(coreUiModules, welcomeModules)
