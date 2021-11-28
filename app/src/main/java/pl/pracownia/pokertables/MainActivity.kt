package pl.pracownia.pokertables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pl.pracownia.pokertables.ui.theme.PokerTablesTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      PokerTablesTheme {
        MyApp()
      }
    }
  }
}

@Composable
fun MyApp() {
  Scaffold(
    content = {
      Greeting(name = "Test")
    }
  )
}

@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}
