package br.com.androidtest.features.myData.data.datasource

import br.com.androidtest.core.util.DataError
import br.com.androidtest.core.util.Result
import br.com.androidtest.features.myData.data.dto.MyDataDto

interface IMyDataRemoteDataSource {
    suspend fun fetchMyDataNP(): Result<MyDataDto, DataError.Remote>
    suspend fun fetchMyDataRW(): Result<MyDataDto, DataError.Remote>
}