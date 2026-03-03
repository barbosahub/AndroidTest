package br.com.androidtest.core.util

sealed interface DataError : Error {
    enum class Remote : DataError {
        REQUEST_TIMEOUT,
        NO_INTERNET,
        SERVER,
        SERIALIZATION,
        UNKNOWN,
        UNAUTHORIZED
    }

    enum class LocalDataBase : DataError {
        DISK_FULL,
        UNKNOWN
    }

    enum class LocalPreferences : DataError {
        SERIALIZATION_ERROR,
        IO_ERROR,
        CORRUPTION_ERROR,
        KEY_NOT_FOUND,
        UNKNOWN
    }
}

