package kambi.victor.walenje.core.designsystem.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import kambi.victor.walenje.core.designsystem.vector
import walenje.core.designsystem.generated.resources.Res
import walenje.core.designsystem.generated.resources.del
import walenje.core.designsystem.generated.resources.fluent_lock_closed_key_16_filled
import walenje.core.designsystem.generated.resources.gravity_ui_arrow_chevron_right
import walenje.core.designsystem.generated.resources.icon_park_outline_arrow_left
import walenje.core.designsystem.generated.resources.line_md_chevron_up
import walenje.core.designsystem.generated.resources.material_symbols_keyboard_backspace_rounded
import walenje.core.designsystem.generated.resources.material_symbols_lock
import walenje.core.designsystem.generated.resources.mdi_circle
import walenje.core.designsystem.generated.resources.mdi_circle_outline
import walenje.core.designsystem.generated.resources.ri_arrow_left_long_line
import walenje.core.designsystem.generated.resources.walenje_passcode

object WalenjeIcons {
  val Analytics = Icons.Default.Analytics

  val ArrowChevronRight
    @Composable get() = Res.drawable.gravity_ui_arrow_chevron_right.vector()

  val ArrowLeft
    @Composable get() = Res.drawable.icon_park_outline_arrow_left.vector()

  val ArrowLeftLong
    @Composable get() = Res.drawable.ri_arrow_left_long_line.vector()

  val BackspaceArrow
    @Composable get() = Res.drawable.material_symbols_keyboard_backspace_rounded.vector()

  val ChevronLeft = Icons.Default.ChevronLeft
  val ChevronUp
    @Composable get() = Res.drawable.line_md_chevron_up.vector()

  val Circle
    @Composable get() = Res.drawable.mdi_circle.vector()

  val CircleOutline
    @Composable get() = Res.drawable.mdi_circle_outline.vector()

  val Del
    @Composable get() = Res.drawable.del.vector()

  val Home = Icons.Default.Home
  val Lock
    @Composable get() = Res.drawable.material_symbols_lock.vector()

  val LockKey
    @Composable get() = Res.drawable.fluent_lock_closed_key_16_filled.vector()

  val Profile = Icons.Default.Person
  val Star
    @Composable get() = Res.drawable.walenje_passcode.vector()
}
