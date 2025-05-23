package kambi.victor.walenje.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kambi.victor.walenje.core.designsystem.icons.WalenjeIcons
import kambi.victor.walenje.core.designsystem.virtualKey
import kambi.victor.walenje.core.ui.view_models.SetPinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun SetPin(
  title: String? = null,
  onSuccess: (String) -> Unit,
  viewModel: SetPinViewModel = koinViewModel(),
) {
  val input = viewModel.pin.collectAsState().value

  LazyColumn {
    /* Pin display */
    item {
      val MAX_PIN_LENGTH = 6
      Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Text(
          text = title ?: "Enter your new 6-digit PIN",
          style =
            MaterialTheme.typography.titleLarge.copy(
              fontWeight = FontWeight.SemiBold,
              textAlign = TextAlign.Center,
            ),
        )
        Text(input, style = MaterialTheme.typography.bodyLarge)
        Row(
          horizontalArrangement =
            Arrangement.spacedBy(12.dp, alignment = Alignment.CenterHorizontally)
        ) {
          for (i in 1..MAX_PIN_LENGTH) {
            if (i <= input.length) {
              Icon(imageVector = WalenjeIcons.Circle, contentDescription = null)
            } else {
              Icon(imageVector = WalenjeIcons.CircleOutline, contentDescription = null)
            }
          }
        }
      }
    }

    item { Spacer(modifier = Modifier.padding(vertical = 64.dp)) }

    /* Key pad */
    item {
      NumberPad(
        onValueChange = { pin ->
          viewModel.onPinChange(pin)

          if (pin.length == 6) {
            onSuccess(pin)
            viewModel.onPinChange("")
          }
        }
      )
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NumberPad(onValueChange: (String) -> Unit) {
  val haptic = LocalHapticFeedback.current
  var input by remember { mutableStateOf("") }
  ProvideTextStyle(MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Medium)) {
    FlowRow(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement =
        Arrangement.spacedBy(space = 16.dp, alignment = Alignment.CenterHorizontally),
      verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
      maxItemsInEachRow = 3,
    ) {
      for (i in 1..12) {
        if (i == 10) {
          Spacer(modifier = Modifier.width(60.dp))
        } else if (i == 11) {
          NumberPadItem(
            value = "0",
            onClick = {
              input = "0"
              haptic.virtualKey()
              onValueChange("0")
            },
          )
        } else if (i == 12) {
          NumberPadItem(
            onClick = {
              input = "del"
              haptic.virtualKey()
              onValueChange("del")
            },
            backgroundVisible = false,
          ) {
            Column(
              //              verticalArrangement = Arrangement.spacedBy(space = 2.dp),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Icon(
                imageVector = WalenjeIcons.ArrowLeftLong,
                contentDescription = null,
                modifier = Modifier.size(32.dp),
              )
              Icon(imageVector = WalenjeIcons.Del, contentDescription = null)
            }
          }
        } else {
          NumberPadItem(
            value = i.toString(),
            onClick = {
              input = i.toString()
              haptic.virtualKey()
              onValueChange(input)
            },
          )
        }
      }
    }
  }
}

@Composable
fun NumberPadItem(value: String, onClick: (() -> Unit)? = null, modifier: Modifier = Modifier) {
  NumberPadItem(onClick = onClick, modifier = modifier) {
    Text(value, textAlign = TextAlign.Center)
  }
}

@Composable
fun NumberPadItem(
  onClick: (() -> Unit)? = null,
  modifier: Modifier = Modifier,
  backgroundVisible: Boolean = true,
  content: @Composable () -> Unit,
) {
  Box(
    modifier =
      modifier
        .size(60.dp)
        .clip(CircleShape)
        .background(
          if (backgroundVisible) {
            MaterialTheme.colorScheme.onSurface.copy(alpha = .2f)
          } else Color.Unspecified
        )
        .then(
          if (onClick != null) Modifier.clickable { onClick() }
          else Modifier // Apply clickable only if onClick is provided
        ),
    contentAlignment = Alignment.Center,
  ) {
    content()
  }
}
