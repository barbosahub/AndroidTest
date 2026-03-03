package br.com.androidtest.features.myPlan.domain.repository

import br.com.androidtest.core.util.DataError
import br.com.androidtest.core.util.Result
import br.com.androidtest.features.myPlan.domain.model.MyPlan

interface IMyPlanRepository {
    suspend fun fetchMyPlanNP(): Result<MyPlan, DataError.Remote>

    suspend fun fetchMyPlanRW(): Result<MyPlan, DataError.Remote>

}