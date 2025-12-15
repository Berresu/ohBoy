package com.example.a15122025

import android.R.attr.type
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.a15122025.ui.theme._15122025Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _15122025Theme {
                Scaffold{innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)){
                        AppNav()
                    }
                }

            }
        }
    }
}

@Composable
fun AppNav() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController)
        }
        composable("detail/{name}", arguments = listOf(navArgument("name") {
            type = NavType.StringType
        })) { backStackEntry ->
            val itemName = backStackEntry.arguments?.getString("name") ?: "Bilinmeyen Öge"
            DetailScreen(itemName)
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    val carouselItems = listOf(
        "Yoongi",
        "Connor",
        "Remus",
        "Loki",
        "Yekta",
        "HIM"
    )
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(carouselItems) { item ->
            CarouselCard(
                title = item,
                onClick = {
                    if (item == "Yoongi") {
                        navController.navigate("detail/$item")
                    }
                    else if (item == "Connor") {
                        navController.navigate("detail/$item")
                    }
                    else if (item == "Remus") {
                        navController.navigate("detail/$item")
                    }
                    else if (item == "Loki") {
                        navController.navigate("detail/$item")
                    }
                    else if (item == "Yekta") {
                        navController.navigate("detail/$item")
                    }
                    else if (item == "HIM") {
                        navController.navigate("detail/$item")
                    }
                }
            )
        }
    }
}

@Composable
fun CarouselCard(
    title: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(120.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun DetailScreen(name: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$name Detay Sayfası",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
