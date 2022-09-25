package com.royal.tiger.androidweatherapp.domain.location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}