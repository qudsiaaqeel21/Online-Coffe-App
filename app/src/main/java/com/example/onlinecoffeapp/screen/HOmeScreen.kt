package com.example.onlinecoffeapp.screen



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.onlinecoffeapp.Coffee
import com.example.onlinecoffeapp.CoffeeViewModel

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