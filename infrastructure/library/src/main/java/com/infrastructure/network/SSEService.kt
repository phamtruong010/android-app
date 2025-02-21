package com.infrastructure.network


import android.util.Log
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources
import java.util.concurrent.TimeUnit

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val text: String,
)

class SSEService {

    private val sseClient = OkHttpClient.Builder()
        .connectTimeout(6, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.MINUTES)
        .writeTimeout(10, TimeUnit.MINUTES)
        .build()

    private val sseEventSourceListeners = mutableListOf<EventSource>()

    fun sub(url: String, onMessage: (Message) -> Unit): EventSource {
        val sseRequest = Request.Builder()
            .url("https://dev2-biz-api.equix.app/v1/portfolio?account_id=0223344")
            .header("Accept", "application/json")
            .addHeader("Accept", "text/event-stream")
            .addHeader("authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ0cnVvbmcucGhhbUBub3Z1cy1maW50ZWNoLmNvbSIsInN1YiI6ImVxMTcyMjM5NDA5MTc3MSIsImV4cCI6MTczMjY5NDU1MC42OTcsImRldmljZV9pZCI6IjIxYzNiZTk0LWNiOTktNDBhZS1hZDBiLTgyNWVhMDcyMGQ1NCIsImlhdCI6MTczMjY5MzM1MH0.TXi15_OJT8gQA830qlsvuW8F2ZCTFzxnv94mN_VTRZ6gyUWQbFPQCySq4PAElSLi9BDHcm_LDUxrelUWn6IxlsLCJOe5hmnFOTCSJs9QESP4-qltJT9tHdYJ-su9PWtLHttInaJovnelA_rOzBZ4ARezJ9MR7OrneuHJHmA_0KQN5zkfHBH9q7983P0a4bAA0s2A3qE-Jtm84jcBn7vOdipSTz533puzG2KUeEAtZgKVFP8LaRXNke3U_wGGXdGXuXc0PTGNsfkwGk4ra--sUxV-WO1UBw58y9LiJGS-Qo6UVfLiaM6w8LEyyKWUOU7ZNnTGc8MKTddoWIJU5n_PyOUX-dRvrptOslE2nnVsEwtpx46e-UE604qCTboWPoKnqDz1FJ8Z6C1Iy490mdYkvUVQnMLFZ35ep26Ux9LeW2-LYFz98z7kvKQR-JSHmIetWfoM86O_m-fMoijF6a3phds4KCpPSfl9n8pjVz1CutlAynKuRaJ1QCT5zPLPORSm-jcQF7tokD1_RdjNzIP3MlyFlSLo56deMMhLu5G49WBbFWSgE3Fqur2jePuTYC3_Md8ZCF3p5fksUk7xaswPNAh55LFCzRbob4SyAfNQEeySJveH9Sy47uiXlklMrWz7W_S5LV9W_0hCCEw_FsYo9ZUEeIuFUdM75zWTkYRSsMY")
            .build()
        val listener = object : EventSourceListener() {
            override fun onEvent(
                eventSource: EventSource,
                id: String?,
                type: String?,
                data: String,
            ) {
                super.onEvent(eventSource, id, type, data)
                Log.d("alitest", "onEvent: $data")
//                if (data.isNotEmpty()) {
//                    if (data.startsWith("[") and data.endsWith("]")) {
//
//                    } else if (data.startsWith("{") and data.endsWith("}")) {
//                        val msgData: Message = Json.decodeFromString(data)
////                        onMessage(msgData)
//
//                    }
//                }
            }

            override fun onFailure(eventSource: EventSource, t: Throwable?, response: Response?) {
                super.onFailure(eventSource, t, response)
                Log.d("alitest", "onFailure: $t")
            }
        }

        
        return try {
            EventSources.createFactory(sseClient)
                .newEventSource(request = sseRequest, listener = listener)
        } catch (e: Exception) {
            Log.e("SSEService", "Error creating EventSource: ${e.message}")
            throw e
        }
    }

    fun unsub(listener: EventSource) {
        listener.cancel()
        sseEventSourceListeners.remove(listener)
    }


    companion object {
        private const val GET_MESSAGES_URL = "http://192.168.0.21:3000/messages"
        private const val POST_MESSAGE_URL = "http://192.168.0.21:3000/messages"
    }
}