package kambi.victor.walenje.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kambi.victor.walenje.core.designsystem.icons.WalenjeIcons
import kambi.victor.walenje.core.designsystem.medium
import kambi.victor.walenje.core.designsystem.noIndicationClickable
import kambi.victor.walenje.core.ui.view_models.SetPinViewModel
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun SetPin(
  title: String? = null,
  onSuccess: (String) -> Unit,
  viewModel: SetPinViewModel = koinViewModel(),
) {
  val input = viewModel.pin.collectAsState()

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
        Text(input.value, style = MaterialTheme.typography.bodyLarge)
        Row(
          horizontalArrangement =
            Arrangement.spacedBy(12.dp, alignment = Alignment.CenterHorizontally)
        ) {
          for (i in 1..MAX_PIN_LENGTH) {
            if (i <= input.value.length) {
              Icon(imageVector = vectorResource(WalenjeIcons.Circle), contentDescription = null)
            } else {
              Icon(
                imageVector = vectorResource(WalenjeIcons.CircleOutline),
                contentDescription = null,
              )
            }
          }
        }
      }
    }

    item { Spacer(modifier = Modifier.padding(vertical = 64.dp)) }

    /* Key pad */
    item {
      WalenjeNumPad(
        onValueChange = { pin ->
          viewModel.onPinChange(pin)
          if (pin.length == 6) {
            onSuccess(pin)
          }
          //          }
        }
      )
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WalenjeNumPad(onValueChange: (String) -> Unit) {
  val haptic = LocalHapticFeedback.current
  var input by remember { mutableStateOf("") }
  ProvideTextStyle(MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold)) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
      Text(input)
      FlowRow(maxItemsInEachRow = 3, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        for (i in 1..3) {
          WalenjeNumPadEntry(
            value = "$i",
            onClick = {
              if (input.length < 6) {
                haptic.medium()
                input += i
                onValueChange(input)
              }
            },
            modifier = Modifier.weight(1f),
          )
        }
      }
      FlowRow(maxItemsInEachRow = 3, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        for (i in 4..6) {
          WalenjeNumPadEntry(
            value = "$i",
            onClick = {
              if (input.length < 6) {
                haptic.medium()
                input += i
                onValueChange(input)
              }
            },
            modifier = Modifier.weight(1f),
          )
        }
      }
      FlowRow(maxItemsInEachRow = 3, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        for (i in 7..9) {
          WalenjeNumPadEntry(
            value = "$i",
            onClick = {
              if (input.length < 6) {
                haptic.medium()
                input += i
                onValueChange(input)
              }
            },
            modifier = Modifier.weight(1f),
          )
        }
      }
      FlowRow(maxItemsInEachRow = 3, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        WalenjeNumPadEntry(
          onClick = null,
          modifier = Modifier.weight(1f).noIndicationClickable {},
        ) {}
        WalenjeNumPadEntry(
          "0",
          onClick = {
            if (input.length < 6) {
              haptic.medium()
              input += "0"
              onValueChange(input)
            }
          },
          modifier = Modifier.weight(1f),
        )
        WalenjeNumPadEntry(
          onClick = {
            if (input.isNotEmpty()) {
              haptic.medium()
              input = input.dropLast(1)
              onValueChange(input)
            }
          },
          modifier = Modifier.weight(1f),
        ) {
          Icon(imageVector = vectorResource(WalenjeIcons.ArrowLeftLong), contentDescription = null)
        }
      }
    }
  }
}

@Composable
fun WalenjeNumPadEntry(
  value: String,
  onClick: (() -> Unit)? = null,
  modifier: Modifier = Modifier,
) {
  WalenjeNumPadEntry(onClick = onClick, modifier = modifier) {
    Text(value, textAlign = TextAlign.Center)
  }
}

@Composable
fun WalenjeNumPadEntry(
  onClick: (() -> Unit)? = null,
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit,
) {
  Box(
    modifier =
      modifier
        .height(50.dp)
        .clip(RoundedCornerShape(20.dp))
        .then(
          if (onClick != null) Modifier.clickable { onClick() }
          else Modifier // Apply clickable only if onClick is provided
        ),
    contentAlignment = Alignment.Center,
  ) {
    content()
  }
}
