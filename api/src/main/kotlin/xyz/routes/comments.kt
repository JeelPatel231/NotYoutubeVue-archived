package xyz.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.schabi.newpipe.extractor.ListExtractor
import org.schabi.newpipe.extractor.Page
import org.schabi.newpipe.extractor.ServiceList.YouTube
import org.schabi.newpipe.extractor.comments.CommentsExtractor
import org.schabi.newpipe.extractor.comments.CommentsInfoItem

fun Route.comments() {
    get("/comments/{videoid}") {
        commentsFunction(call)
    }
}

private fun returnPage(extractor: CommentsExtractor, url:String? = "", id: String?): ListExtractor.InfoItemsPage<CommentsInfoItem> {
    return if (id == null){
        extractor.fetchPage()
        extractor.initialPage
    }
    else {
        extractor.getPage(Page(url, id))
    }
}

suspend fun commentsFunction(call:ApplicationCall){
    val videoId = call.parameters["videoid"]
    val url = "https://www.youtube.com/watch?v=$videoId"
    var pageId = call.parameters["id"]
    pageId = if(pageId == "" || pageId == "null") null else pageId

    YouTube.getCommentsExtractor(url)
        .also {
            call.respond(returnPage(it,url,pageId))
        }
}