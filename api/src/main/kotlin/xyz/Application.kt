package xyz

import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.*
import org.schabi.newpipe.extractor.NewPipe
import xyz.plugins.configureRouting
import xyz.utils.CustomDownloader
import java.text.DateFormat

fun main() {
    initialize()
    embeddedServer(Netty, port = 8081, host = "0.0.0.0") {
        install(DefaultHeaders)
        install(CallLogging)
        install(ContentNegotiation) {
            gson {
                setDateFormat(DateFormat.LONG)
                setPrettyPrinting()
            }
        }
        configureRouting()
    }.start(wait = true)
}

fun initialize() {
    NewPipe.init(CustomDownloader.getInstance())
}