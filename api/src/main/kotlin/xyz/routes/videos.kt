package xyz.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.schabi.newpipe.extractor.ServiceList.YouTube
import org.schabi.newpipe.extractor.timeago.patterns.it

fun Route.video() {
    get("/video/{id}") {
        videoFunction(call)
    }
}

suspend fun videoFunction(call: ApplicationCall){
    try {
        YouTube.getStreamExtractor("https://www.youtube.com/watch?v=${call.parameters["id"]}")
        .also {
            it.fetchPage()
            call.respond(mapOf(
                "videoStreams" to it.videoStreams,
                "videoinfo" to mapOf(
                    "description" to it.description,
                    "name" to it.name,
                    "likeCount" to it.likeCount,
                    "streamType" to it.streamType,
                    "viewCount" to it.viewCount,
                    "textualUploadDate" to it.textualUploadDate,
                    "uploaderName" to it.uploaderName,
                    "uploaderAvatarUrl" to it.uploaderAvatarUrl,
                    "isUploaderVerified" to it.isUploaderVerified
                ),
                "hlsUrl" to it.hlsUrl,
                "dashMpdUrl" to it.dashMpdUrl,
                "relatedItems" to it.relatedItems
            ))
        }
    }
    catch (e:Exception){
        call.respond(mapOf("error" to e))
    }}
