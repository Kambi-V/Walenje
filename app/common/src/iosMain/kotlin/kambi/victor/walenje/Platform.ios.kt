package kambi.victor.walenje

actual fun getPlatform(): Platform = IOSPlatform()

class IOSPlatform : Platform {
  override val name: String = "IOS"
}
