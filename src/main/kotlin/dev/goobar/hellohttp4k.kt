package dev.goobar

import dev.goobar.formats.kotlinXMessage
import dev.goobar.formats.kotlinXMessageLens
import org.http4k.core.*
import org.http4k.core.Method.GET
import org.http4k.core.Status.Companion.OK
import org.http4k.filter.DebuggingFilters.PrintRequest
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer

val app: HttpHandler = routes(
    "/ping" bind GET to ::ping,
    "/formats/json/kotlinx" bind GET to ::kotlinx,
    "/testing/strikt" bind GET to ::strikt
)

fun main() {
    val printingApp: HttpHandler = PrintRequest().then(app)

    val server = printingApp.asServer(Jetty(9000)).start()

    println("Server started on " + server.port())
}

private fun ping(request: Request) = Response(OK).body("pong")
private fun kotlinx(request: Request) = Response(OK).with(kotlinXMessageLens of kotlinXMessage)
private fun strikt(request: Request) = Response(OK).body("Echo '${request.bodyString()}'")
