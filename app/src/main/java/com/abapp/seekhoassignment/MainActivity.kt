package com.abapp.seekhoassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.abapp.seekhoassignment.navigation.Screen
import com.abapp.seekhoassignment.ui.screen.AnimeDetailScreen
import com.abapp.seekhoassignment.viewmodel.AnimeListViewModel
import com.abapp.seekhoassignment.ui.screen.AnimeListScreen
import com.abapp.seekhoassignment.ui.theme.SeekhoAssignmentTheme
import com.abapp.seekhoassignment.viewmodel.AnimeDetailViewModel
import com.abapp.seekhoassignment.viewmodel.AnimeViewModelFactory
import dagger.hilt.android.AndroidEntryPoint




@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SeekhoAssignmentTheme{
                AnimeApp()
            }
        }

    }
}





@Composable
fun AnimeApp() {
    val navController = rememberNavController()


    //navigation
    NavHost(
        navController = navController,
        startDestination = Screen.AnimeList.route
    ) {



        //route - Anime list
        composable(
            route = Screen.AnimeList.route
        ) {

            //view model
            val listViewModel: AnimeListViewModel = viewModel(
                factory = AnimeViewModelFactory(LocalContext.current )
            )

            //ui
            AnimeListScreen(
                viewModel = listViewModel,
                onAnimeClick = { animeId ->
                    navController.navigate(Screen.AnimeDetail.createRoute(animeId))
                },
            )
        }




        //route - anime details
        composable(
            route = Screen.AnimeDetail.route,
            arguments = listOf(navArgument("animeId") { type = NavType.IntType })
        ) { backStackEntry ->
            //anime id
            val id = backStackEntry.arguments?.getInt("animeId") ?: 0


            //view model
            val detailViewModel: AnimeDetailViewModel = viewModel(
                factory = AnimeViewModelFactory(LocalContext.current )
            )

            //ui
            AnimeDetailScreen(
                animeId = id,
                viewModel = detailViewModel,
                onBackProcessed = {
                    navController.navigateUp()
                },
            )
        }




    }
}