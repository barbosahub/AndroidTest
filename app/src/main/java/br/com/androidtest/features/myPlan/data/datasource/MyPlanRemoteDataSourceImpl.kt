package br.com.androidtest.features.myPlan.data.datasource

import android.content.Context
import br.com.androidtest.core.util.DataError
import br.com.androidtest.core.util.Result
import br.com.androidtest.features.myData.data.datasource.IMyDataRemoteDataSource
import br.com.androidtest.features.myData.data.dto.MyDataDto
import br.com.androidtest.features.myPlan.data.dto.MyPlanDto
import kotlinx.serialization.json.Json

class MyPlanRemoteDataSourceImpl(
    private val context: Context
) : IMyPlanRemoteDataSource {

    val path = "my_plan"

    override suspend fun fetchMyPlanNP(): Result<MyPlanDto, DataError.Remote> {
        return loadMockJson("$path/my_plan_new.json")
    }

    override suspend fun fetchMyPlanRW(): Result<MyPlanDto, DataError.Remote> {
        return loadMockJson("$path/my_plan_old.json")
    }

    private fun loadMockJson(fileName: String): Result<MyPlanDto, DataError.Remote> {
        return try {
            val jsonText = context.assets.open(fileName).bufferedReader().use { it.readText() }

            val myPlanDto = JsonProvider.json.decodeFromString<MyPlanDto>(jsonText)

            Result.Success(myPlanDto)
        } catch (e: Exception) {
            Result.Error(DataError.Remote.UNKNOWN)
        }
    }
}

object JsonProvider {
    val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        prettyPrint = true
    }
}