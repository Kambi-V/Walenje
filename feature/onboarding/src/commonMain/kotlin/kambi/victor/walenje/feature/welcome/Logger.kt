package kambi.victor.walenje.feature.welcome

import co.touchlab.kermit.Logger
import co.touchlab.kermit.NoTagFormatter
import co.touchlab.kermit.loggerConfigInit
import co.touchlab.kermit.platformLogWriter

internal val logger by lazy {
  Logger(loggerConfigInit(platformLogWriter(NoTagFormatter)), "LOGGER")
}
