package br.com.androidtest.features.myPlan.data.datasource

import br.com.androidtest.core.util.DataError
import br.com.androidtest.core.util.Result
import br.com.androidtest.features.myData.data.dto.MyDataDto
import br.com.androidtest.features.myPlan.data.dto.MyPlanDto

interface IMyPlanRemoteDataSource {
    suspend fun fetchMyPlanNP(): Result<MyPlanDto, DataError.Remote>
    suspend fun fetchMyPlanRW(): Result<MyPlanDto, DataError.Remote>
}