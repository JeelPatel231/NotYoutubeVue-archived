package xyz.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.schabi.newpipe.extractor.*
import org.schabi.newpipe.extractor.search.SearchExtractor
import xyz.gson

fun Route.search() {
    get("/search/{query}/{pagenum}") {
        searchQueryFunction(call)
    }
}

// TODO: someway to store next page url so instantaneous pagination
fun returnNthPage(extractor:SearchExtractor,number: Int): ListExtractor.InfoItemsPage<InfoItem> {
    var pageContent = extractor.initialPage
    (0 until number).forEach { _ ->
        pageContent = extractor.getPage(pageContent.nextPage)
    }
    return pageContent
}

suspend fun searchQueryFunction(call:ApplicationCall){
    val pagenum = call.parameters["pagenum"]!!.toInt()
    val extractor =  ServiceList.YouTube.getSearchExtractor(
        call.parameters["query"], emptyList(), ""
    ).also { it.fetchPage() }

    call.respondText(gson.toJson(returnNthPage(extractor,pagenum).items))
}