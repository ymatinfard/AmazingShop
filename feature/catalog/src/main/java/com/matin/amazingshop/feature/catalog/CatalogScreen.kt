package com.matin.amazingshop.feature.catalog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CatalogScreen(viewModel: CatalogScreenViewModel) {
    val catalogState = viewModel.catalogUiState.collectAsStateWithLifecycle()
    Box(modifier = Modifier.fillMaxSize()) {
        Text("Catalog Screen" + catalogState.value)
    }
}
