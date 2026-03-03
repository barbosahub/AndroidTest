package br.com.androidtest.features.myPlan.data.repository

import android.graphics.Region
import br.com.androidtest.core.util.DataError
import br.com.androidtest.features.myData.data.datasource.IMyDataRemoteDataSource
import br.com.androidtest.features.myData.data.dto.MyDataDto
import br.com.androidtest.features.myData.domain.model.MyData
import br.com.androidtest.features.myData.domain.repository.IMyDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.map
import br.com.androidtest.core.util.Result
import br.com.androidtest.core.util.map
import br.com.androidtest.features.myData.data.mapper.toDomainData
import br.com.androidtest.features.myPlan.data.datasource.IMyPlanRemoteDataSource
import br.com.androidtest.features.myPlan.data.mapper.toDomainPlan
import br.com.androidtest.features.myPlan.domain.model.MyPlan
import br.com.androidtest.features.myPlan.domain.repository.IMyPlanRepository


class MyPlanRepositoryImpl(
    private val remoteDataSource: IMyPlanRemoteDataSource,
) : IMyPlanRepository {

    override suspend fun fetchMyPlanNP(): Result<MyPlan, DataError.Remote> {
        return withContext(Dispatchers.IO) {
            remoteDataSource.fetchMyPlanNP().map { it.toDomainPlan() }
        }
    }

    override suspend fun fetchMyPlanRW(): Result<MyPlan, DataError.Remote> {
        return withContext(Dispatchers.IO) {
            remoteDataSource.fetchMyPlanRW().map { it.toDomainPlan() }
        }
    }
}