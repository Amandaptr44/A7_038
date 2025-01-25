package com.example.ujianakhir

import com.example.ujianakhir.di.AppContainer
import com.example.ujianakhir.di.TerapiContainer

import android.app.Application


class TerapiApplications : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = TerapiContainer()
    }
}
