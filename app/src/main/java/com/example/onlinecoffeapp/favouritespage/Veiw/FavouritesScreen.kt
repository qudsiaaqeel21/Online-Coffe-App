package com.example.onlinecoffeapp.favouritespage.Veiw


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.onlinecoffeapp.Coffee
import com.example.onlinecoffeapp.CoffeeViewModel

val it: Coffee = TODO()

@Composable
fun FavouritesScreen(viewModel: CoffeeViewModel) {
    val favourites by viewModel.favouriteCoffees.collectAsState()

    Column(Modifier.fillMaxSize()) {
        Text(
            "My Favourites",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        if (favourites.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = "Empty",
                        modifier = Modifier.size(80.dp),
                        tint = Color.Gray
                    )
                    Spacer(Modifier.height(16.dp))
                    Text("No Favourites Yet", color = Color.Gray)
                    Text("Tap heart icon to add coffee", color = Color.Gray)
                }
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(favourites, key = { it.id }) { coffee ->
                    CoffeeItemCard(
                        coffee = coffee,
                        onHeartClick = { viewModel.toggleFavourite(it) }
                    )
                }
            }
        }
    }
}

@Composable
fun CoffeeItemCard(coffee: Coffee, onHeartClick: () -> Unit) {
    TODO("Not yet implemented")
}