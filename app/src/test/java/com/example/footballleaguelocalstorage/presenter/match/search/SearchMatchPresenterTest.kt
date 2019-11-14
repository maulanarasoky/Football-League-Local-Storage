package com.example.footballleaguelocalstorage.presenter.match.search

import com.example.footballleaguelocalstorage.api.ApiRepository
import com.example.footballleaguelocalstorage.coroutines.TestContextProvider
import com.example.footballleaguelocalstorage.interfaces.match.search.SearchMatchView
import com.example.footballleaguelocalstorage.model.match.FootballLeagueMatch
import com.example.footballleaguelocalstorage.model.team.FootballTeamData
import com.example.footballleaguelocalstorage.response.search.SearchMatchResponse
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

class SearchMatchPresenterTest {

    @Mock
    private lateinit var view: SearchMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    @Mock
    private lateinit var presenter: SearchMatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = SearchMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun searchMatch() {
        val matchData: MutableList<FootballLeagueMatch> = mutableListOf()
        val teamData: MutableList<FootballTeamData> = mutableListOf()
        val responseMatch = SearchMatchResponse(matchData)
        val responseTeam = TeamResponse(teamData)
        val searchMatch = "Arsenal vs Chelsea"
        val league = "English Premiere League"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    SearchMatchResponse::class.java
                )
            ).thenReturn(responseMatch)

            Mockito.`when`(
                gson.fromJson(
                    "",
                    TeamResponse::class.java
                )
            ).thenReturn(responseTeam)

            presenter.searchMatch(searchMatch, league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchList(matchData, teamData)
            Mockito.verify(view).hideLoading()
        }
    }
}