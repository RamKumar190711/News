package com.toqsoft.news.presentationLayer.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import java.net.URLEncoder
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun NewsNavGraph() {
    val navController = rememberNavController()
    val apiKey = "22272ea5595f4521900f9dd40863ed17" // TODO: inject securely

    NavHost(navController = navController, startDestination = "news") {
        composable("news") {
            NewsScreen(
                apiKey = apiKey,
                onArticleClick = { url ->
                    // ✅ Encode before navigating
                    val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                    navController.navigate("webview/$encodedUrl")
                }
            )
        }
        composable(
            route = "webview/{url}",
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) { backStackEntry ->
            // ✅ Decode before loading
            val encodedUrl = backStackEntry.arguments?.getString("url") ?: ""
            val url = URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8.toString())
            ArticleWebViewScreen(url = url)
        }
    }
}
