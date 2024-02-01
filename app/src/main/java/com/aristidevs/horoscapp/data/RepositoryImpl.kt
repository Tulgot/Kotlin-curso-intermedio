package com.aristidevs.horoscapp.data

import android.util.Log
import com.aristidevs.horoscapp.data.network.HoroscopeApiService
import com.aristidevs.horoscapp.domain.Repository
import com.aristidevs.horoscapp.domain.model.PredictionModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: HoroscopeApiService) : Repository {

    private val TAG: String = "getprediction"

    override suspend fun getPrediction(sign: String): PredictionModel? {
        runCatching { apiService.getHoroscope(sign) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i(TAG, "getPrediction: A ocurrido un error ${it.message}") }
        return null
    }
}