package com.royal.tiger.androidweatherapp.data.location

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.royal.tiger.androidweatherapp.domain.location.Location
import com.royal.tiger.androidweatherapp.domain.location.LocationTracker
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultLocationTracker @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
): LocationTracker {
    override suspend fun getCurrentLocation(): Location? {
        val hasAccessFineLocation = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasAccessCoarseLocation = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
            locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if(!hasAccessFineLocation || !hasAccessCoarseLocation || !isGpsEnabled) {
            return null
        }

        return suspendCancellableCoroutine { cont ->
            locationClient.lastLocation.apply {
                if(isComplete) {
                    if (isSuccessful) {
                        cont.resume(
                            Location(
                                latitude = result.latitude,
                                longitude = result.longitude
                            )
                        )
                    } else {
                        cont.resume(null)
                    }

                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    it?.let {
                        cont.resume(Location(
                            latitude = it.latitude,
                            longitude = it.longitude
                        ))
                    } ?: cont.resume(null)
                }
                addOnFailureListener {
                    cont.resume(null)
                }
                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }
}