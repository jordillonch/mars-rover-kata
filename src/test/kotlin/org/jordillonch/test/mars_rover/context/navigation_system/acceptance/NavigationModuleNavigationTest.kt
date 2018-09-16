package org.jordillonch.test.mars_rover.context.navigation_system.acceptance

import io.ktor.http.HttpMethod.Companion.Put
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import org.jordillonch.test.AcceptanceTest
import org.junit.Assert
import java.util.UUID

class NavigationModuleNavigationTest :
        AcceptanceTest(
                {
                    should("send navigate commands to mars rover") {
                        with(testApplicationEngine) {
                            val id = UUID.randomUUID()
                            with(handleRequest(Put, "/mars-rover/navigation/routes") {
                                addHeader("Accept", "application/json")
                                addHeader("Content-Type", "application/json")
                                setBody("""{
    "route_id": "$id",
    "commands": [
      "FORWARD",
      "FORWARD",
      "FORWARD"
    ]
}
""")
                            }) {
                                Assert.assertEquals(HttpStatusCode.OK, response.status())
                                Assert.assertEquals("""{
  "id": "$id",
  "status": "success"
}""", response.content)
                            }
                        }
                    }
                })
