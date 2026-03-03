package br.com.androidtest.features.myData.data.datasource

import android.content.Context
import br.com.androidtest.core.util.DataError
import br.com.androidtest.core.util.Result
import br.com.androidtest.features.myData.data.dto.MyDataDto
import br.com.androidtest.features.myData.domain.model.MyData
import kotlinx.serialization.json.Json

class MyDataRemoteDataSourceImpl(
    private val context: Context
) : IMyDataRemoteDataSource {

    val path = "my_data"

    override suspend fun fetchMyDataNP(): Result<MyDataDto, DataError.Remote> {
        return loadMockJson("$path/my_data_new.json")
    }

    override suspend fun fetchMyDataRW(): Result<MyDataDto, DataError.Remote> {
        return loadMockJson("$path/my_data_old.json")
    }

    private fun loadMockJson(fileName: String): Result<MyDataDto, DataError.Remote> {
        return try {
            val jsonText = context.assets.open(fileName).bufferedReader().use { it.readText() }

            val myDataDto = JsonProvider.json.decodeFromString<MyDataDto>(jsonText)

            Result.Success(myDataDto)
        } catch (e: Exception) {
            Result.Error(DataError.Remote.UNKNOWN)
        }
    }
}


object JsonProvider {
    val json = Json {
        ignoreUnknownKeys = true
        isLenient = true       // opcional, útil para JSON não rigoroso
        prettyPrint = true     // opcional, se quiser logs bonitos
    }
}