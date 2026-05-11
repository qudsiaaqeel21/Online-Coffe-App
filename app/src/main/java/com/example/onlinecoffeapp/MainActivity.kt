package com.example.onlinecoffeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.onlinecoffeapp.favouritespage.Veiw.FavouritesScreen
import com.example.onlinecoffeapp.ui.theme.OnlinecoffeappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnlinecoffeappTheme {
                val viewModel: CoffeeViewModel by viewModels()
                MainScreen(viewModel)
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: CoffeeViewModel) {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Default.Home, "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Icon(Icons.Default.Favorite, "Favourites") },
                    label = { Text("Favourites") }
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedTab) {
                0 -> HomeScreen(viewModel)
                1 -> FavouritesScreen(viewModel)
            }
        }
    }
}

// <-- YAHAN MASLA THA, YE THEEK KIYA HAI
@Composable
fun HomeScreen(viewModel: CoffeeViewModel) {
    val coffees by viewModel.allCoffees.collectAsState()

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(coffees, key = { it.id }) { coffee ->
            CoffeeItemCard(
                coffee = coffee,
                onHeartClick = { viewModel.toggleFavourite(it) }
            )
        }
    }
}

// Ye bhi add karna parega warna error aayega
@Composable
fun CoffeeItemCard(coffee: Coffee, onHeartClick: (Coffee) -> Unit) {
    Card(Modifier.fillMaxWidth()) {
        Row(
            Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = coffee.image),
                contentDescription = coffee.name,
                modifier = Modifier.size(80.dp)
            )

            Spacer(Modifier.width(12.dp))

            Column(Modifier.weight(1f)) {
                Text(coffee.name, style = MaterialTheme.typography.titleMedium)
                Text(coffee.desc, style = MaterialTheme.typography.bodySmall)
                Text("Rs. ${coffee.price}", style = MaterialTheme.typography.titleSmall)
            }

            IconButton(onClick = { onHeartClick(coffee) }) {
                Icon(
                    imageVector = if (coffee.isFavourite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favourite",
                    tint = if (coffee.isFavourite) Color.Red else Color.Gray
                )
            }
        }
    }
}