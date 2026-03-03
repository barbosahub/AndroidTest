package br.com.androidtest.features.myData.data.repository

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


class MyDataRepositoryImpl(
    private val remoteDataSource: IMyDataRemoteDataSource,
) : IMyDataRepository {


    override suspend fun fetchMyDataNP(): Result<MyData, DataError.Remote> {
        return withContext(Dispatchers.IO) {
            remoteDataSource.fetchMyDataNP().map { it.toDomainData() }
        }
    }

    override suspend fun fetchMyDataRW(): Result<MyData, DataError.Remote> {
        return withContext(Dispatchers.IO) {
            remoteDataSource.fetchMyDataRW().map { it.toDomainData() }
        }
    }


}