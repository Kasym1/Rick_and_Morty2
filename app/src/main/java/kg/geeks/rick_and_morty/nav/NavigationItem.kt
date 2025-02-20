package kg.geeks.rick_and_morty.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavigationItem(val route: String, val label: String, val icon: ImageVector) {
    Characters("characters", "Characters", Icons.Default.Person),
    Locations("locations", "Locations", Icons.Default.Place),
    Episodes("episodes", "Episodes", Icons.Default.List),
    FavoriteCharacters("favorite_characters", "FavoriteCharacters", Icons.Default.FavoriteBorder)
}