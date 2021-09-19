package com.asterisk.runtracker.di

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.asterisk.runtracker.R
import com.asterisk.runtracker.ui.MainActivity
import com.asterisk.runtracker.utils.Constants
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped

@Module
@InstallIn(ServiceComponent::class)
object ServiceModule {

    @ServiceScoped
    @Provides
    fun providesFusedLocationProviderClient(
        @ApplicationContext app: Context
    ) = FusedLocationProviderClient(app)

    @ServiceScoped
    @Provides
    fun providesMainActivityPendingIntent(
        @ApplicationContext app: Context
    ) = PendingIntent.getActivity(
        app, 0,
        Intent(app, MainActivity::class.java).also {
            it.action = Constants.ACTION_SHOW_TRACKING_FRAGMENT
        },
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    @ServiceScoped
    @Provides
    fun providesBaseNotificationBuilder(
        @ApplicationContext app: Context,
        pendingIntent: PendingIntent
    ) = NotificationCompat.Builder(app, Constants.NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_directions_run_black_24dp)
            .setContentTitle("Run Tracker")
            .setContentText("00:00:00")
            .setContentIntent(pendingIntent)

}