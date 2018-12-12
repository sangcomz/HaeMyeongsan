package xyz.sangcomz.haemyeongsan

import com.github.pgutkowski.kgraphql.KGraphQL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.*
import io.ktor.gson.GsonConverter
import io.ktor.gson.gson
import io.ktor.http.HttpMethod
import io.ktor.locations.Locations
import io.ktor.request.receive
import io.ktor.response.header
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.sessions.*
import io.ktor.util.hex
import org.koin.core.Koin
import org.koin.dsl.module.applicationContext
import org.koin.dsl.module.module
import org.koin.log.PrintLogger
import org.koin.standalone.StandAloneContext.startKoin
import xyz.sangcomz.haemyeongsan.database.UsersDatabase
import xyz.sangcomz.haemyeongsan.database.UsersStorage
import xyz.sangcomz.haemyeongsan.graphql.AppSchema
import xyz.sangcomz.haemyeongsan.model.HaeUser
import java.text.DateFormat
import java.time.Duration
import javax.crypto.spec.SecretKeySpec

class HaeMyeongsanApp {

    private val mainModule = module {
        factory { Gson() }
        factory { AppSchema(get()) }
        factory { UsersDatabase() as UsersStorage }
    }

    @Suppress("unused")
    fun Application.main() {
        Koin.logger = PrintLogger()
        startKoin(listOf(mainModule))

        install(DefaultHeaders)
        install(CORS) {
            method(HttpMethod.Options)
            method(HttpMethod.Get)
            method(HttpMethod.Post)
            anyHost()
            allowCredentials = true
            maxAge = Duration.ofDays(1)
        }
        install(CallLogging)
        install(Locations)
        install(ConditionalHeaders)
        install(PartialContent)
        install(ContentNegotiation) {
            gson {
                setDateFormat(DateFormat.LONG)
                setPrettyPrinting()
            }
        }
    }
}
