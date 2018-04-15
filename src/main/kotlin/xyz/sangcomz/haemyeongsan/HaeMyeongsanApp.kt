package xyz.sangcomz.haemyeongsan

import com.google.gson.GsonBuilder
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.*
import io.ktor.gson.GsonConverter
import io.ktor.gson.gson
import io.ktor.http.HttpMethod
import io.ktor.request.receive
import io.ktor.response.header
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.sessions.*
import io.ktor.util.hex
import org.eclipse.jetty.server.session.Session
import xyz.sangcomz.haemyeongsan.model.Thought
import java.text.DateFormat
import java.time.Duration
import javax.crypto.spec.SecretKeySpec

class HaeMyeongsanApp {


    fun Application.main() {
        install(CORS) {
            method(HttpMethod.Options)
            method(HttpMethod.Get)
            method(HttpMethod.Post)
            anyHost()
            allowCredentials = true
            maxAge = Duration.ofDays(1)
        }
        install(DefaultHeaders)
        install(CallLogging)
        install(ConditionalHeaders)
        install(PartialContent)
        install(ContentNegotiation) {
            gson {
                setDateFormat(DateFormat.LONG)
                setPrettyPrinting()
            }
        }
        install(Routing) {
            get("/") {
                call.respondText("Hello, World!")
            }

            post("/") {
                val thought = call.receive<Thought>()
                println("thought :::: ${thought.date}")
                call.respondText("Hello, World! Post")
            }
        }
    }
}
