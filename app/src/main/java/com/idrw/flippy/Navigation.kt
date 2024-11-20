package com.idrw.flippy

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.idrw.flippy.ui.component.LearnStatus
import com.idrw.flippy.ui.view.flashcardPractice.FlashcardPractice
import com.idrw.flippy.ui.view.deck.Deck
import com.idrw.flippy.ui.view.deck.DeckViewModel
import com.idrw.flippy.ui.view.newCard.NewCard
import com.idrw.flippy.ui.view.decks.Decks
import com.idrw.flippy.ui.view.decks.DecksViewModel
import com.idrw.flippy.ui.view.flashcardPractice.FlashcardPracticeViewModel
import com.idrw.flippy.ui.view.newCard.NewCardViewModel
import com.idrw.flippy.ui.view.newDeck.NewDeck
import com.idrw.flippy.ui.view.newDeck.NewDeckViewModel
import kotlinx.serialization.Serializable

@Serializable
data object Routes {
    @Serializable data object Decks
    @Serializable data class Deck(val deckId: Int)
    @Serializable data object NewDeck
    @Serializable data class NewCard(val deckId: Int)
    @Serializable data class Flashcard(val deckId: Int, val cardId: Int, val filterBy: String, val pageIndex: Int)
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
                    val context = LocalContext.current
                    val vm = viewModel {
                        DecksViewModel(context)
                    }
                    Decks(vm)
                }

                composable<Routes.Deck>(
                    enterTransition = { slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left) },
                    popEnterTransition = { slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right) },

                    exitTransition = { slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left) },
                    popExitTransition = { slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right) },
                ) {
                    val args = it.toRoute<Routes.Deck>()
                    val context = LocalContext.current
                    val vm = viewModel {
                        DeckViewModel(context, args.deckId)
                    }
                    Deck(vm, args.deckId)
                }

                composable<Routes.NewCard>(
                    enterTransition = { slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left) },
                    exitTransition = { slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right) }
                ) {
                    val args = it.toRoute<Routes.NewCard>()
                    val context = LocalContext.current
                    val vm = viewModel {
                        NewCardViewModel(context, args.deckId)
                    }
                    NewCard(vm)
                }

                composable<Routes.NewDeck>(
                    enterTransition = { slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left) },
                    popExitTransition = { slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right) }
                ) {
                    val context = LocalContext.current
                    val vm = viewModel {
                        NewDeckViewModel(context)
                    }
                    NewDeck(vm)
                }

                composable<Routes.Flashcard> (
                    enterTransition = { slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left) },
                    popExitTransition = { slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right) }
                ) {
                    val args = it.toRoute<Routes.Flashcard>()
                    val context = LocalContext.current
                    val learnStatus = if (args.filterBy == "null") null else LearnStatus.fromString(args.filterBy)
                    val vm = viewModel {
                        FlashcardPracticeViewModel(context, args.deckId, learnStatus)
                    }
                    FlashcardPractice(vm, args.pageIndex)
                }
            }
        }
    }
}