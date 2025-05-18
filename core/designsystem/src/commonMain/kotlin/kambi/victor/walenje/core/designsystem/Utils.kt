package kambi.victor.walenje.core.designsystem

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource

@Composable fun DrawableResource.vector() = vectorResource(this)

@Composable fun DrawableResource.painter() = painterResource(this)

@Composable fun DrawableResource.image() = imageResource(this)
