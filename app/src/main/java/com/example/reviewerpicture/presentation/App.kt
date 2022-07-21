package com.example.reviewerpicture.presentation

import android.app.Application
import com.example.reviewerpicture.presentation.di.Injector
import com.example.reviewerpicture.presentation.di.core.AppComponent
import com.example.reviewerpicture.presentation.di.core.AppModule
import com.example.reviewerpicture.presentation.di.core.DaggerAppComponent
import com.example.reviewerpicture.presentation.di.listing.ListingSubComponent

class App : Application(), Injector {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .build()
    }


    override fun createListingSubComponent(): ListingSubComponent {
        return appComponent.listingSubComponent().create()
    }
}