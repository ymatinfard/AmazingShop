package com.matin.amazingshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.matin.amazingshop.core.designsystem.theme.AmazingShopTheme
import com.matin.amazingshop.navigation.AmazingShopNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmazingShopTheme {
                Scaffold { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        AmazingShopNavHost()
                    }
                }
            }
        }
    }
}