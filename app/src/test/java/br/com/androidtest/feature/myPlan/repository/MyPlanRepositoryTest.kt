package br.com.androidtest.feature.myPlan.repository

import br.com.androidtest.core.util.DataError
import br.com.androidtest.core.util.Result
import br.com.androidtest.features.myPlan.domain.model.Content
import br.com.androidtest.features.myPlan.domain.model.ExtraPlay
import br.com.androidtest.features.myPlan.domain.model.ExtraPlayOption
import br.com.androidtest.features.myPlan.domain.model.HeaderItem
import br.com.androidtest.features.myPlan.domain.model.HeaderValue
import br.com.androidtest.features.myPlan.domain.model.MyPlan
import br.com.androidtest.features.myPlan.domain.model.Screen
import br.com.androidtest.features.myPlan.domain.repository.IMyPlanRepository

class MyPlanRepositoryTest : IMyPlanRepository {
    var shouldReturnError: Boolean = false

    override suspend fun fetchMyPlanNP(): Result<MyPlan, DataError.Remote> {
        return if (shouldReturnError) {
            Result.Error(DataError.Remote.UNKNOWN)
        } else {
            Result.Success(
                MyPlan(
                    screen = Screen(
                        name = "Meu Plano",
                        offerDisplay = "Oferta Teste",
                        planValue = "R$ 99,90",
                        header = listOf(
                            listOf(
                                HeaderItem(
                                    title = "Header 1",
                                    value = HeaderValue(
                                        text = "Valor",
                                        alignment = "LEFT"
                                    )
                                )
                            )
                        ),
                        extraPlay = ExtraPlay(
                            title = "Extras",
                            options = listOf(
                                ExtraPlayOption(
                                    description = "Extra Screen",
                                    url = "https://extrascreen.com"
                                )
                            )
                        )
                    )
                )
            )

        }
    }

    override suspend fun fetchMyPlanRW(): Result<MyPlan, DataError.Remote> {
        return if (shouldReturnError) {
            Result.Error(DataError.Remote.UNKNOWN)
        } else {
            Result.Success(
                MyPlan(
                    content = Content(
                        planValue = "R$ 39,99",
                        status = "Active",
                        phoneNumber = "(34) 99687-7876",
                        plan = 15,
                        bonus = 5,
                        extraPlayBaseUrl = "https://mondrian.claro.com.br/brands/app/32px-alternative",
                        extraPlay = listOf(
                            ExtraPlayOption(description = "tik-tok", url = "https://mondrian.claro.com.br/brands/app/32px-alternative/tik-tok.png"),
                            ExtraPlayOption(description = "facebook", url = "https://mondrian.claro.com.br/brands/app/32px-alternative/facebook.png"),
                            ExtraPlayOption(description = "instagram", url = "https://mondrian.claro.com.br/brands/app/32px-alternative/instagram.png"),
                            ExtraPlayOption(description = "twitter", url = "https://mondrian.claro.com.br/brands/app/32px-alternative/twitter.png"),
                            ExtraPlayOption(description = "waze", url = "https://mondrian.claro.com.br/brands/app/32px-alternative/waze.png"),
                            ExtraPlayOption(description = "youtube", url = "https://mondrian.claro.com.br/brands/app/32px-alternative/youtube.png")
                        )
                    )
                )
            )
        }
    }
}