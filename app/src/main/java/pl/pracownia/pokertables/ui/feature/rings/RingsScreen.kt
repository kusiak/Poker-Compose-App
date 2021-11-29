package pl.pracownia.pokertables.ui.feature.rings

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import pl.pracownia.pokertables.R
import pl.pracownia.pokertables.base.BaseViewModel.Companion.LISTEN_FOR_EFFECTS
import pl.pracownia.pokertables.model.Ring
import pl.pracownia.pokertables.ui.theme.PokerTablesTheme

@Composable
fun RingsScreen(
    state: RingsContract.State,
    effectFlow: Flow<RingsContract.Effect>?,
    onEventSent: (event: RingsContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: RingsContract.Effect.Navigation) -> Unit
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    // Listen for side effects from the VM
    LaunchedEffect(LISTEN_FOR_EFFECTS) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is RingsContract.Effect.DataWasLoaded ->
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Rings are loaded.",
                        duration = SnackbarDuration.Short
                    )
                is RingsContract.Effect.Navigation.ToCategoryDetails -> onNavigationRequested(
                    effect
                )
            }
        }?.collect()
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            RingsAppBar()
        },
    ) {
        Box() {
            RingsList(ringsList = state.rings) { itemId ->
                onEventSent(RingsContract.Event.RingSelection(itemId))
            }
            if (state.isLoading)
                LoadingBar()
        }
    }

}

@Composable
private fun RingsAppBar() {
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Home,
                modifier = Modifier.padding(horizontal = 12.dp),
                contentDescription = "Action icon"
            )
        },
        title = { Text(stringResource(R.string.app_name)) },
        backgroundColor = MaterialTheme.colors.background
    )
}

@Composable
fun RingsList(
    ringsList: List<Ring>,
    onItemClicked: (id: Long) -> Unit = { }
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(items = ringsList) { item ->
            RingRow(item = item, onItemClicked = onItemClicked)
        }
    }
}

@Composable
fun RingRow(
    item: Ring,
    onItemClicked: (id: Long) -> Unit = { }
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .clickable { onItemClicked(item.id) }
    ) {
        Row(modifier = Modifier.animateContentSize()) {
            RingDetails(
                item = item,
                expandedLines = 2,
                modifier = Modifier
                    .padding(
                        start = 8.dp,
                        end = 8.dp,
                        top = 24.dp,
                        bottom = 24.dp
                    )
                    .fillMaxWidth(0.80f)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun RingDetails(
    item: Ring?,
    expandedLines: Int,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = item?.name ?: "",
            style = MaterialTheme.typography.h6
        )
        Text(
            text = item?.game?.name ?: "",
            style = MaterialTheme.typography.subtitle2
        )

    }
}

@Composable
fun LoadingBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokerTablesTheme() {
        RingsScreen(RingsContract.State(), null, { }, { })
    }
}