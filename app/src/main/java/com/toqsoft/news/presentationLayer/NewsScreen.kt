package com.toqsoft.news.presentationLayer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import coil.compose.rememberAsyncImagePainter
import com.toqsoft.news.dataLayer.models.NewsArticle
import com.toqsoft.news.domainLayer.viewModel.NewsViewModel

@Composable
fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel(),
    apiKey: String,
    onArticleClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(uiState.isLoading)

    LaunchedEffect(Unit) {
        viewModel.loadNews(apiKey = apiKey)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
//            SmallTopAppBar(title = { Text("Top Headlines") })
        }
    ) { innerPadding ->
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { viewModel.loadNews(apiKey = apiKey) },
            modifier = Modifier
                .padding(innerPadding) // ✅ respect status bar & top bar
                .fillMaxSize()
        ) {
            if (uiState.news.isEmpty() && uiState.isLoading) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(10) { SkeletonNewsItem() }
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(uiState.news) { article ->
                        NewsItem(article, onClick = { onArticleClick(article.url) })
                    }
                }
            }
        }
    }
}

@Composable
fun NewsItem(article: NewsArticle, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            article.imageUrl?.let { url ->
                Image(
                    painter = rememberAsyncImagePainter(url),
                    contentDescription = article.title,
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(article.title, style = MaterialTheme.typography.titleMedium)
                article.description?.let {
                    Text(it, style = MaterialTheme.typography.bodyMedium, maxLines = 3)
                }
            }
        }
    }
}

@Composable
fun SkeletonNewsItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Box(
                    modifier = Modifier
                        .height(20.dp)
                        .fillMaxWidth(0.7f)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .height(14.dp)
                        .fillMaxWidth(0.9f)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                )
            }
        }
    }
}
