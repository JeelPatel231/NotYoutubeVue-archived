package xyz.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.schabi.newpipe.extractor.InfoItem
import org.schabi.newpipe.extractor.ListExtractor
import org.schabi.newpipe.extractor.Page
import org.schabi.newpipe.extractor.ServiceList
import org.schabi.newpipe.extractor.search.SearchExtractor

fun Route.search() {
    get("/search/{query}") {
        searchQueryFunction(call)
    }
}

fun returnPage(extractor: SearchExtractor,url:String? = "",id: String? = ""): ListExtractor.InfoItemsPage<InfoItem> {
    return if (url == "" && id == "" || url == null &&  id == null){
        extractor.fetchPage()
        extractor.initialPage
    }
    else {
        extractor.getPage(Page(url, id))
    }
}

suspend fun searchQueryFunction(call:ApplicationCall){
    val url = call.parameters["url"]
    val id = call.parameters["id"]

    ServiceList.YouTube.getSearchExtractor(
        call.parameters["query"], emptyList(), ""
    ).also {
        call.respond(returnPage(it,url,id))
    }
}