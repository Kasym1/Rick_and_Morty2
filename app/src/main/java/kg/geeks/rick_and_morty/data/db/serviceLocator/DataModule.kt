package kg.geeks.rick_and_morty.data.db.serviceLocator

import androidx.room.Room
import kg.geeks.rick_and_morty.BuildConfig
import kg.geeks.rick_and_morty.data.api.CharacterApiService
import kg.geeks.rick_and_morty.data.api.EpisodeApiService
import kg.geeks.rick_and_morty.data.api.LocationApiService
import kg.geeks.rick_and_morty.data.db.AppDatabase
import kg.geeks.rick_and_morty.data.repository.CharactersRepository
import kg.geeks.rick_and_morty.data.repository.EpisodesRepository
import kg.geeks.rick_and_morty.data.repository.FavoriteCharactersRepository
import kg.geeks.rick_and_morty.data.repository.LocationsRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    //retrofit
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }

    //room
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "app_database").build() }
    single { get<AppDatabase>().favoriteCharacterDao() }

    //api
    single { get<Retrofit>().create(CharacterApiService::class.java) }
    single { get<Retrofit>().create(LocationApiService::class.java) }
    single { get<Retrofit>().create(EpisodeApiService::class.java) }

    //repo
    single { CharactersRepository(get()) }
    single { LocationsRepository(get()) }
    single { EpisodesRepository(get()) }
    single { FavoriteCharactersRepository(get()) }
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}