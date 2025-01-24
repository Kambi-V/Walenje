package kambi.victor.walenje.core.ui.di

import kambi.victor.walenje.core.ui.view_models.SetPinViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val coreUiModules = module { viewModelOf(::SetPinViewModel) }
