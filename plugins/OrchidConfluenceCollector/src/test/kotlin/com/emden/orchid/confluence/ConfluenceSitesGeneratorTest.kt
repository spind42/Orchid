package com.emden.orchid.confluence

import com.eden.orchid.testhelpers.OrchidIntegrationTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Tests behavior of Presentations generator")
class PresentationsGeneratorTest : OrchidIntegrationTest(ConfluenceModul()) {


    @Test
    //@DisplayName("Test")
    fun test01() {


        configObject("confluence",
                """
                |{
                | "confluenceServers" : [
                |   {
                |       "url" : "http://localhost:8090/rest/api/",
                |       "username" : "admin",
                |       "password" : "admin",
                |       "key" : "server1
                |   }
                | ]
                    }""".trimMargin()
        )




    }


}