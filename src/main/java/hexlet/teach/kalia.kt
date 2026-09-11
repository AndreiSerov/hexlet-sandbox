package heaxlet.teach


import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
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
        install(ContentNegotiation) {
            json(
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
        val kla: HttpStatement = client.prepareGet("https://ktor.io/docs/welcome.html")
        withContext(Dispatchers.IO) {
            println("kalia blai before")
            Thread.sleep(10L)
            println(kla.body<String>().split("\n")[0])
        }

        val response = client.prepareRequest("https://en.wikipedia.org/w/api.php?action=query&prop=revisions&titles=Populus&rvslots=*&rvprop=content&formatversion=2") {
            method = HttpMethod.Get
        }
            .execute()
        response.bodyAsText(Charsets.UTF_8)
    }

    println("kalia after")
}
