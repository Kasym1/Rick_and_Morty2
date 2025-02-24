package kg.geeks.rick_and_morty.data.paging.location

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kg.geeks.rick_and_morty.data.api.LocationApiService
import kg.geeks.rick_and_morty.model.LocationModel
import kg.geeks.rick_and_morty.model.toLocationModel

class LocationPagingSource(
    private val apiService: LocationApiService
) : PagingSource<Int, LocationModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationModel> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getAllLocations(currentPage)
            LoadResult.Page(
                data = response.locationsResponse.map { it.toLocationModel() },
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (response.pagingInfo.nextPage.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LocationModel>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}