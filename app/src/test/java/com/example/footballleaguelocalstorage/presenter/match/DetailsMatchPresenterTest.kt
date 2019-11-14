package com.example.footballleaguelocalstorage.presenter.match

import com.example.footballleaguelocalstorage.api.ApiRepository
import com.example.footballleaguelocalstorage.coroutines.TestContextProvider
import com.example.footballleaguelocalstorage.interfaces.match.DetailsMatchView
import com.example.footballleaguelocalstorage.model.match.DetailsMatch
import com.example.footballleaguelocalstorage.response.match.DetailsResponse
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailsMatchPresenterTest {

    @Mock
    private lateinit var view: DetailsMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    @Mock
    private lateinit var presenter: DetailsMatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = DetailsMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getDetailsMatch() {
        val matchData: MutableList<DetailsMatch> = mutableListOf()
        val responseMatch = DetailsResponse(matchData)
        val idMatch = "441613"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    DetailsResponse::class.java
                )
            ).thenReturn(responseMatch)

            presenter.getDetailsMatch(idMatch)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showDetailsMatch(matchData)
            Mockito.verify(view).hideLoading()
        }
    }
}