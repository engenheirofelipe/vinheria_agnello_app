package br.com.fiap.room_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.fiap.room_android.screens.AtualizacaoScreen
import br.com.fiap.room_android.screens.ListaScreen
import br.com.fiap.room_android.screens.MenuScreen
import br.com.fiap.room_android.screens.VinhoList
import br.com.fiap.room_android.screens.VinhoScreen
import br.com.fiap.room_android.ui.theme.Room_androidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Room_androidTheme {
                    // VinhoScreen()
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "menu"
                ){
                    composable("menu"){
                        MenuScreen(navController)
                    }
                    composable("vinhos"){
                        VinhoScreen(navController)
                    }
                    composable("lista"){
                        ListaScreen(navController)
                    }
                    composable(
                        "atualizacao_vinho/{vinhoId}",
                        arguments = listOf(navArgument("vinhoId"){type = NavType.LongType})
                    ){
                        val vinhoId = it.arguments?.getLong("vinhoId") ?: 0L
                        AtualizacaoScreen(navController, vinhoId = vinhoId)
                    }
                }

            }
        }
    }
}




