package xyz

import com.google.gson.Gson
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.schabi.newpipe.extractor.NewPipe
import xyz.plugins.configureRouting
import xyz.utils.CustomDownloader


lateinit var gson: Gson;

fun main() {
    initialize()
    embeddedServer(Netty, port = 8081, host = "0.0.0.0") {
        configureRouting ()
    }.start(wait = true)
}

fun initialize() {
    gson = Gson()
    NewPipe.init(CustomDownloader.getInstance())
}