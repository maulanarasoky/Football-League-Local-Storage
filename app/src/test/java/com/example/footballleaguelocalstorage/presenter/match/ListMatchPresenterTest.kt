package com.example.footballleaguelocalstorage.presenter.match

import com.example.footballleaguelocalstorage.api.ApiRepository
import com.example.footballleaguelocalstorage.coroutines.TestContextProvider
import com.example.footballleaguelocalstorage.interfaces.league.match.LeagueMatchView
import com.example.footballleaguelocalstorage.model.match.FootballLeagueMatch
import com.example.footballleaguelocalstorage.model.team.FootballTeamData
import com.example.footballleaguelocalstorage.response.match.ListMatchResponse
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

class ListMatchPresenterTest {

    @Mock
    private lateinit var view: LeagueMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    @Mock
    private lateinit var presenter: ListMatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = ListMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getLastMatchList() {
        val matchData: MutableList<FootballLeagueMatch> = mutableListOf()
        val teamData: MutableList<FootballTeamData> = mutableListOf()
        val responseMatch = ListMatchResponse(matchData)
        val responseTeam = TeamResponse(teamData)
        val idLeague = "4328"
        val nameLeague = "English Premiere League"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    ListMatchResponse::class.java
                )
            ).thenReturn(responseMatch)

            Mockito.`when`(
                gson.fromJson(
                    "",
                    TeamResponse::class.java
                )
            ).thenReturn(responseTeam)

            presenter.getLastMatchList(idLeague, nameLeague)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchList(matchData, teamData)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getNextMatchList() {
        val matchData: MutableList<FootballLeagueMatch> = mutableListOf()
        val teamData: MutableList<FootballTeamData> = mutableListOf()
        val responseMatch = ListMatchResponse(matchData)
        val responseTeam = TeamResponse(teamData)
        val idLeague = "4328"
        val nameLeague = "English Premiere League"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    ListMatchResponse::class.java
                )
            ).thenReturn(responseMatch)

            Mockito.`when`(
                gson.fromJson(
                    "",
                    TeamResponse::class.java
                )
            ).thenReturn(responseTeam)

            presenter.getNextMatchList(idLeague, nameLeague)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchList(matchData, teamData)
            Mockito.verify(view).hideLoading()
        }
    }
}