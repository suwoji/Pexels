package com.example.pexels.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun InternetConnection(context: Context): Boolean {
    var hasInternetConnection = false
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
        hasInternetConnection = when {
            hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
    return hasInternetConnection
}