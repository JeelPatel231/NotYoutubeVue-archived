package xyz.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.schabi.newpipe.extractor.ServiceList

fun Route.video() {
    get("/video/{id}") {
        videoFunction(call)
    }
}

suspend fun videoFunction(call: ApplicationCall){
    try {
        ServiceList.YouTube.getStreamExtractor("https://www.youtube.com/watch?v=${call.parameters["id"]}")
        .also {
            it.fetchPage()
            call.respond(it.videoStreams)
        }
    }
    catch (e:Exception){
        call.respond(mapOf("error" to e))
    }}
//        .also { it.videoStreams.forEach { kek -> println(kek.url) } }
//        .also { call.respond(gson.toJson(mapOf(
//            "videoStreams" to it.videoStreams,
//            "description" to it.description,
//            "name" to it.name,
//            "likeCount" to it.likeCount,
//            "streamType" to it.streamType,
//            "hlsUrl" to it.hlsUrl,
//            "dashMpdUrl" to it.dashMpdUrl,
//            "relatedItems" to it.relatedItems
//        ))) }
