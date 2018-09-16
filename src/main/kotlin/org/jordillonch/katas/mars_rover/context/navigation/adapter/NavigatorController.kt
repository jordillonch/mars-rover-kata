import io.ktor.application.call
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.put
import org.jordillonch.commons.cqrs.command.domain.CommandBus
import org.jordillonch.katas.mars_rover.context.navigation.adapter.NavigationRouteRequest
import org.jordillonch.katas.mars_rover.context.navigation.adapter.toCommand
import org.jordillonch.katas.mars_rover.context.navigation.domain.navigation_system.NavigateResponse

fun Routing.navigator(commandBus: CommandBus) {
    put("/mars-rover/navigation/routes") {
        //        try {
        val response = commandBus.handle<NavigateResponse>(call.receive<NavigationRouteRequest>().toCommand())
        call.respond(OK, response)
//        } catch (e: UnableToSaveBlockedUsers) {
//            call.respond(InternalServerError, e.toControllerError())
//        }
    }
}
