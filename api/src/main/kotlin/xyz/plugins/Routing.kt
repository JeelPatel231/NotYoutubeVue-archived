package xyz.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import xyz.routes.search
import xyz.routes.video

fun Application.configureRouting() {
    routing {
        search()
        video()
//        todo
//        comments()
    }
}
