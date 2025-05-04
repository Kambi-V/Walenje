package kambi.victor.walenje.feature.welcome.di

import kambi.victor.walenje.feature.welcome.view_models.SetPinScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val welcomeModules = module { viewModelOf(::SetPinScreenViewModel) }
