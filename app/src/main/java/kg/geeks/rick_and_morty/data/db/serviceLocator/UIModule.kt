package kg.geeks.rick_and_morty.data.db.serviceLocator

import kg.geeks.rick_and_morty.ui.screen.characters.CharactersViewModel
import kg.geeks.rick_and_morty.ui.screen.episodes.EpisodesViewModel
import kg.geeks.rick_and_morty.ui.screen.fav.FavoriteCharactersViewModel
import kg.geeks.rick_and_morty.ui.screen.locations.LocationsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { CharactersViewModel(get()) }
    viewModel { LocationsViewModel(get()) }
    viewModel { EpisodesViewModel(get()) }
    viewModel { FavoriteCharactersViewModel(get()) }
}