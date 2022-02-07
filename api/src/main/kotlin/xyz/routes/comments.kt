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
    get("/comments/{id}") {
        commentsFunction(call)
    }
}

private fun returnPage(extractor: CommentsExtractor, url:String? = "", id: String? = ""): ListExtractor.InfoItemsPage<CommentsInfoItem> {
    return if (url == "" && id == "" || url == null &&  id == null){
        extractor.fetchPage()
        extractor.initialPage
    }
    else {
        extractor.getPage(Page(url, id))
    }
}

suspend fun commentsFunction(call:ApplicationCall){
    val url = call.parameters["url"]
    val id = call.parameters["id"]

    YouTube.getCommentsExtractor("https://www.youtube.com/watch?v=${call.parameters["id"]}")
        .also {
        call.respond(returnPage(it,url,id))
    }
}