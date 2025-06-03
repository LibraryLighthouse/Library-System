package com.example.librarylighthouse.Util

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.net.NetworkCapabilities
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresPermission

object NetworkUtils {
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    @SuppressLint("ServiceCast")
    fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

}

