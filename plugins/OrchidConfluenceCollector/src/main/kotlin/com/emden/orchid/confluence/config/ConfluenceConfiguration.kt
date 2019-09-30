package com.emden.orchid.confluence.config

import com.eden.orchid.api.options.annotations.Description
import com.eden.orchid.api.options.annotations.Option

class ConfluenceConfiguration {

    @Option
    @Description("The API Url of the Confluence installation")
    lateinit var url: String;

    @Option
    @Description("The username to authenticate with basic authentication at confluence")
    lateinit var username: String;

    @Option
    @Description("The password to authenticate with basic authentication at confluence")
    lateinit var password: String;

    @Option
    @Description("The reference to this confluence server")
    lateinit var key: String;

}