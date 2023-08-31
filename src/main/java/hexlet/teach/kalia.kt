package heaxlet.teach


import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

object KtorClient {
    private const val AUTHORIZATION_HEADER = "Authorization"
    private var API_KEY: String = "Your API Key here"

    private val client = HttpClient(Android) {
//        defaultRequest {
//            header(AUTHORIZATION_HEADER, "BEARER $API_KEY")
//        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                kotlinx.serialization.json.Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    explicitNulls = false
                })
        }
    }

    val instance
        get() = client
    val getInstance = client
}

class Kalia(val a: Int, val c: Long) {
    val b: Int
        get() = a * c.toInt()
}

fun main() {
    val client = KtorClient.getInstance


    val res = runBlocking {
        val kla: HttpStatement = client.get("https://ktor.io/docs/welcome.html")
        withContext(Dispatchers.IO) {
            println("kalia blai before")
            Thread.sleep(10L)
            kla
        }

        val response = client.request<HttpStatement>("https://en.wikipedia.org/w/api.php?action=query&prop=revisions&titles=Populus&rvslots=*&rvprop=content&formatversion=2") {
            method = HttpMethod.Get
        }
            .execute()
        response.readText(Charsets.UTF_8)
    }

    println("kalia blai after")
}