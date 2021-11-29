package pl.pracownia.pokertables.ui.feature.ring_details

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import pl.pracownia.pokertables.model.Ring
import pl.pracownia.pokertables.model.Seat
import pl.pracownia.pokertables.ui.feature.rings.RingDetails
import kotlin.math.min

@Composable
fun RingDetailsScreen(state: RingDetailsContract.State) {
  val scrollState = rememberLazyListState()
  val scrollOffset: Float = min(
    1f,
    1 - (scrollState.firstVisibleItemScrollOffset / 600f + scrollState.firstVisibleItemIndex)
  )
  Surface(color = MaterialTheme.colors.background) {
    Column {
      Surface(elevation = 4.dp) {
        CategoryDetailsCollapsingToolbar(state.ring, scrollOffset)
      }
      Spacer(modifier = Modifier.height(2.dp))
      state.ring?.seats?.let { SeatsList(it) }
    }
  }
}
@Composable
private fun SeatsList(
  seats: List<Seat>
){
  Box(){
    LazyColumn(Modifier.fillMaxWidth()) {
      items(seats) { seat ->
        Card(
          shape = RoundedCornerShape(8.dp),
          backgroundColor = MaterialTheme.colors.surface,
          elevation = 2.dp,
          modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        ){
          Row {
            Card(
              modifier = Modifier.padding(16.dp),
              shape = CircleShape,
              border = BorderStroke(
                width = 1.dp,
                color = Color.Black
              ),
              elevation = 4.dp
            ) {
              Image(
                painter = rememberImagePainter(
                  data = seat.avatar,
                  builder = {
                    transformations(CircleCropTransformation())
                  },
                ),
                modifier = Modifier.size(max(72.dp, 72.dp)),
                contentDescription = "Avatar thumbnail picture",
              )
            }

            Text(
              text = seat.username,
              Modifier.padding(16.dp),
              textAlign = TextAlign.Center,
              style = MaterialTheme.typography.subtitle2
            )

          }
        }
      }
    }
  }
}

@Composable
private fun CategoryDetailsCollapsingToolbar(
  ring: Ring?,
  scrollOffset: Float,
) {
  Row {
    RingDetails(
      item = ring,
      expandedLines = (kotlin.math.max(3f, scrollOffset * 6)).toInt(),
      modifier = Modifier
        .padding(
          end = 16.dp,
          top = 16.dp,
          bottom = 16.dp
        )
        .fillMaxWidth()
    )
  }
}