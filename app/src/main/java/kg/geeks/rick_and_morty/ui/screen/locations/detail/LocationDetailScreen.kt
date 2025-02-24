package kg.geeks.rick_and_morty.ui.screen.locations.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kg.geeks.rick_and_morty.R
import kg.geeks.rick_and_morty.data.paging.location.LocationDetailState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LocationDetailScreen(
    locationId: Int,
    paddingValues: PaddingValues,
    onBackClick: () -> Unit,
    locationDetailViewModel: LocationDetailViewModel = koinViewModel()
) {
    val state = locationDetailViewModel.state.collectAsState().value

    LaunchedEffect(locationId) {
        locationDetailViewModel.getLocationById(locationId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .padding(paddingValues)
    ) {
        when (state) {
            is LocationDetailState.Loading -> {
                Text("Loading location details")
            }

            is LocationDetailState.Success -> {
                val location = state.location

                Text(
                    text = "Name: ${location.name}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(12.dp),
                    color = Color.Black
                )
                Text(
                    text = "Type: ${location.type}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(12.dp),
                    color = Color.Black
                )
                Text(
                    text = "Dimension: ${location.dimension}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(12.dp),
                    color = Color.Black
                )
            }

            is LocationDetailState.Error -> {
                Text("Error: ${state.message}")
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text(text = "Back", fontSize = 16.sp)
        }
    }
}
