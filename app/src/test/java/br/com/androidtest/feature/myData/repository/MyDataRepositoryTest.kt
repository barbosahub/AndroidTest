package br.com.androidtest.feature.myData.repository

import br.com.androidtest.core.util.DataError
import br.com.androidtest.core.util.Result
import br.com.androidtest.features.myData.domain.model.*
import br.com.androidtest.features.myData.domain.repository.IMyDataRepository

class MyDataRepositoryTest : IMyDataRepository {
    var shouldReturnError: Boolean = false

    override suspend fun fetchMyDataNP(): Result<MyData, DataError.Remote> {
        return if (shouldReturnError) {
            Result.Error(DataError.Remote.UNKNOWN)
        } else {
            Result.Success(
                MyData(
                    screen = Screen(
                        title = "Meu Plano",
                        profile = User(
                            id = "1",
                            name = "Rui Barbosa",
                            avatarUrl = "https://example.com/avatar.png"
                        ),
                        options = listOf(
                            Option(
                                iconUrl = "https://example.com/icon1.png",
                                title = "Option 1",
                                action = "ACTION_1"
                            ),
                            Option(
                                iconUrl = "https://example.com/icon2.png",
                                title = "Option 2",
                                action = "ACTION_2"
                            )
                        )
                    )
                )
            )
        }
    }

    override suspend fun fetchMyDataRW(): Result<MyData, DataError.Remote> {
        return if (shouldReturnError) {
            Result.Error(DataError.Remote.UNKNOWN)
        } else {
            Result.Success(
                MyData(
                    content = Content(
                        user = User(
                            id = "1",
                            name = "Rui Barbosa",
                            age = "30",
                            documentNumber = "12345678901",
                            documentNumberMasked = "123.456.***-**",
                            avatarUrl = "https://example.com/avatar.png"
                        )
                    )
                )
            )
        }
    }
}