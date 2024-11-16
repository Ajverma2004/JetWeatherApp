package com.ajverma.jetweatherapp

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ajverma.jetweatherapp.domain.util.CustomTextField
import com.ajverma.jetweatherapp.presentation.ui.screens.home.HomeScreen
import com.ajverma.jetweatherapp.presentation.ui.screens.home.HomeScreenViewModel
import com.ajverma.jetweatherapp.ui.theme.JetWeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HomeScreenViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ){
            viewModel.loadWeatherInfo()
        }

        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
        
        setContent {
            JetWeatherAppTheme {
                var city by remember {
                    mutableStateOf("")
                }

                Scaffold(
                    topBar = {
                        CustomTextField(
                            icon = Icons.Default.Search,
                            text = city,
                            onTextChange = { city = it },
                            search = {
                                viewModel.loadWeatherInfoByCity(city)
                            }
                        )
                    },
                    modifier = Modifier.padding(top = 20.dp)
                ) { paddingValues->
                    HomeScreen(
                        data = viewModel.state,
                        modifier = Modifier.padding(paddingValues)
                    )
                }

//                    HomeScreen(
//                        data = viewModel.state,
////                        modifier = Modifier.padding(paddingValues)
//                    )
            }
        }
    }
}
