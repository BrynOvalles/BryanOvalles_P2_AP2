package edu.ucne.bryanovalles_p2_ap2.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.bryanovalles_p2_ap2.data.local.database.DataBase
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            appContext,
            DataBase::class.java,
            "Parcial.db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideDao(parcialDb: DataBase) = parcialDb.depositoDao()

    const val BASE_URL = ""

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

//    @Provides
//    @Singleton
//    fun provideApi(moshi: Moshi): EntidadApi {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .build()
//            .create(EntidadApi::class.java)
//    }
}