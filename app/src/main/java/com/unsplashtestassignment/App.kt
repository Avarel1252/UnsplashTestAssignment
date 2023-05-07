package com.unsplashtestassignment

import android.app.Application
import android.content.Context
import com.unsplashtestassignment.data.PhotosRepository
import com.unsplashtestassignment.data.room.PhotosDatabase
import kotlin.reflect.KClass

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        Locator.register<Context>(this)
        Locator.register(PhotosDatabase.create(locate()))
        Locator.register(PhotosRepository(locate()))
    }
}

object Locator {
    private val instances = mutableMapOf<KClass<*>, Any>()
    inline fun <reified T : Any> register(instance: T) = register(T::class, instance)

    fun <T : Any> register(kClass: KClass<T>, instance: T) {
        instances[kClass] = instance
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> get(kClass: KClass<T>): T = instances[kClass] as T
}

inline fun <reified T : Any> locate() = Locator.get(T::class)

inline fun <reified T : Any> locateLazy(): Lazy<T> = lazy { Locator.get(T::class) }