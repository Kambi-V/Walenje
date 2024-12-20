package kambi.victor.walenje

interface Platform {
  val name: String
}

expect fun getPlatform(): Platform
