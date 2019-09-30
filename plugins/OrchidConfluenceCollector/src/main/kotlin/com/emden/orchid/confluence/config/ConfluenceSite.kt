package com.emden.orchid.confluence.config

import com.eden.orchid.api.options.annotations.Description
import com.eden.orchid.api.options.annotations.Option



class ConfluenceSite {

    @Option
    @Description("confluence identifier of this site")
    lateinit var id: String

    @Option
    @Description("Orchid place")
    lateinit var orchidUrl: String

    @Option
    @Description("Import children")
    var importChildren: Boolean = false

}