package kg.geeks.rick_and_morty.data.paging.episode

import android.graphics.pdf.LoadParams
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kg.geeks.rick_and_morty.data.api.EpisodeApiService
import kg.geeks.rick_and_morty.model.EpisodeModel
import kg.geeks.rick_and_morty.model.toEpisodeModel

class EpisodePagingSource(
    private val apiService: EpisodeApiService
) : PagingSource<Int, EpisodeModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeModel> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getAllEpisodes(currentPage)
            LoadResult.Page(
                data = response.episodesResponse.map { it.toEpisodeModel() },
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (response.pagingInfo.nextPage.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, EpisodeModel>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}