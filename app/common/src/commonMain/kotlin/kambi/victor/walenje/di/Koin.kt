package kambi.victor.walenje.di

import org.koin.dsl.KoinConfiguration

fun createKoinConfiguration() = KoinConfiguration { modules(commonModule) }
