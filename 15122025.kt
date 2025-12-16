package com.example.a15122025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.painterResource
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
        composable("detail/{name}/{imageId}", arguments = listOf(
            navArgument("name") {
                type = NavType.StringType },
            navArgument("imageId") {
                type = NavType.IntType}
        )
        ) { backStackEntry ->
            val itemName = backStackEntry.arguments?.getString("name") ?: "Bilinmeyen Öge"
            val imageId = backStackEntry.arguments?.getInt("imageId") ?: 0
            DetailScreen(itemName, imageId)
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

    val itemImageResources = mapOf(
        "Yoongi" to R.drawable.yoongi,
        "Connor" to R.drawable.connor,
        "Remus" to R.drawable.remus,
        "Loki" to R.drawable.loki,
        "Yekta" to R.drawable.yekta,
        "HIM" to R.drawable.him
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
                    val imageId = itemImageResources[item] ?: 0
                    navController.navigate("detail/$item/$imageId")
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
fun DetailScreen(name: String, imageId: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (imageId != 0) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = name
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "$name Detay Sayfası",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
