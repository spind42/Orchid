package com.emden.orchid.confluence

import com.eden.orchid.api.OrchidContext
import com.eden.orchid.api.generators.OrchidGenerator
import com.eden.orchid.api.options.annotations.Description
import com.eden.orchid.api.options.annotations.Option
import com.eden.orchid.api.theme.pages.OrchidPage
import com.emden.orchid.confluence.config.ConfluenceConfiguration
import com.emden.orchid.confluence.config.ConfluenceSite
import com.emden.orchid.confluence.model.ConfluenceSitesModel
import java.util.stream.Stream
import javax.inject.Inject

@Description("Imports sites from an confluence wiki", name = "Confluence")
class ConfluenceSitesGenerator
@Inject
constructor(
        context: OrchidContext,
        val model: ConfluenceSitesModel
) : OrchidGenerator(context, GENERATOR_KEY, OrchidGenerator.PRIORITY_DEFAULT) {

    companion object {
        const val GENERATOR_KEY = "confluence"
    }

    @Option
    @Description("A list of confluence servers connect to")
    lateinit var confluenceServers: List<ConfluenceConfiguration>

    @Option
    @Description("A list of sites to import into orchid")
    lateinit var confluenceSites: List<ConfluenceSite>

    override fun startIndexing(): MutableList<out OrchidPage> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun startGeneration(pages: Stream<out OrchidPage>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}