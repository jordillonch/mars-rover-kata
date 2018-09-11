package org.jordillonch.katas.mars_rover.application.acceptance

import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import org.jordillonch.katas.mars_rover.AcceptanceTest
import org.junit.Assert

class ApplicationTest :
        AcceptanceTest(
                {
                    should("check application is up and running") {
                        with(testApplicationEngine) {
                            with(handleRequest(HttpMethod.Get, "/health") {
                            }) {
                                Assert.assertEquals(HttpStatusCode.OK, response.status())
                            }
                        }
                    }
                })
