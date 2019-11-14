package com.example.footballleaguelocalstorage.presenter.league

import com.example.footballleaguelocalstorage.api.ApiRepository
import com.example.footballleaguelocalstorage.coroutines.TestContextProvider
import com.example.footballleaguelocalstorage.interfaces.league.DetailsLeagueView
import com.example.footballleaguelocalstorage.model.team.FootballTeamData
import com.example.footballleaguelocalstorage.response.team.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailsLeaguePresenterTest {

    @Mock
    private lateinit var view: DetailsLeagueView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    @Mock
    private lateinit var presenter: DetailsLeaguePresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = DetailsLeaguePresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getTeamList() {
        val team: MutableList<FootballTeamData> = mutableListOf()
        val response = TeamResponse(team)
        val league = "English Premiere League"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    TeamResponse::class.java
                )
            ).thenReturn(response)

            presenter.getTeamList(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamList(team)
            Mockito.verify(view).hideLoading()
        }
    }
}