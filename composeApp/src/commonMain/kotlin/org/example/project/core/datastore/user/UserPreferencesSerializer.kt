package org.example.project.core.datastore.user

import androidx.datastore.core.Serializer
import io.github.aakira.napier.Napier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object UserPreferencesSerializer: Serializer<DataStoreUserSerial> {
    override val defaultValue: DataStoreUserSerial
        get() = DataStoreUserSerial()

    override suspend fun readFrom(input: InputStream): DataStoreUserSerial {
        return try {
            Json.decodeFromString(
                deserializer = DataStoreUserSerial.serializer(),
                string = input.readBytes().decodeToString()
            )
        }catch (e: SerializationException){
            Napier.e("USER CREDENTIAL SERIALIZER FAILED ${e.message}")
            DataStoreUserSerial()
        }
    }

    override suspend fun writeTo(
        t: DataStoreUserSerial,
        output: OutputStream
    ) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = DataStoreUserSerial.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }

}