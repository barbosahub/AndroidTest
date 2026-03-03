package br.com.androidtest.features.myData.domain.repository

import br.com.androidtest.core.util.DataError
import br.com.androidtest.features.myData.domain.model.MyData
import br.com.androidtest.core.util.Result

interface IMyDataRepository {
    suspend fun fetchMyDataNP(): Result<MyData, DataError.Remote>

    suspend fun fetchMyDataRW(): Result<MyData, DataError.Remote>

}