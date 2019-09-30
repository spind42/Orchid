package com.emden.orchid.confluence

import com.eden.orchid.api.generators.OrchidGenerator
import com.eden.orchid.api.registration.OrchidModule
import com.eden.orchid.utilities.addToSet

class ConfluenceModul : OrchidModule() {

    override fun configure() {
//        withResources(20)

        addToSet<OrchidGenerator, ConfluenceSitesGenerator>()
//        addToSet<OrchidPublisher, RequiredChangelogVersionPublisher>()
//        addToSet<OrchidComponent, ChangelogComponent>()
    }

}