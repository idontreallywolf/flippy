package com.idrw.flippy

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.idrw.flippy.ui.view.card.Card
import com.idrw.flippy.ui.view.deck.Deck
import com.idrw.flippy.ui.view.deck.DeckViewModel
import com.idrw.flippy.ui.view.decks.Decks
import kotlinx.serialization.Serializable

@Serializable
data object Routes {
    @Serializable data object Decks
    @Serializable data class Deck(val deckId: String)
    @Serializable data class Card(val cardId: String)
}

val LocalNavController = compositionLocalOf<NavHostController> {
    error("NavController not provided")
}

@Composable
fun Navigation(content: @Composable (page: @Composable () -> Unit) -> Unit) {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavController provides navController) {
        content {
            NavHost(navController = navController, startDestination = Routes.Decks) {
                composable<Routes.Decks>(
                    enterTransition = { slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right) },
                    exitTransition = { slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left) }
                ) {
                    Decks()
                }

                composable<Routes.Deck>(
                    enterTransition = { slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left) },
                    exitTransition = { slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right) }
                ) {
                    val args = it.toRoute<Routes.Deck>()
                    val vm = viewModel<DeckViewModel>()
                    Deck(vm, args.deckId)
                }

                composable<Routes.Card> {
                    val args = it.toRoute<Routes.Card>()
                    Card(args.cardId)
                }
            }
        }
    }
}