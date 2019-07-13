// Generated by delombok at Sun Mar 24 19:34:08 CDT 2019
package com.eden.orchid.impl.publication;

import com.caseyjbrooks.clog.Clog;
import com.eden.common.util.EdenUtils;
import com.eden.orchid.api.OrchidContext;
import com.eden.orchid.api.options.annotations.Description;
import com.eden.orchid.api.options.annotations.Option;
import com.eden.orchid.api.publication.OrchidPublisher;
import com.eden.orchid.utilities.InputStreamPrinter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.util.concurrent.Executors;

@Description(value = "Run arbitrary shell scripts.", name = "Script")
public class ScriptPublisher extends OrchidPublisher {
    private final String resourcesDir;
    @Option
    @Description("The executable name")
    @NotBlank(message = "Must set the command to run.")
    private String[] command;
    @Option
    @Description("The working directory of the script to run")
    private String cwd;

    @Inject
    public ScriptPublisher(@Named("src") String resourcesDir) {
        super("script", 100);
        this.resourcesDir = resourcesDir;
    }

    @Override
    public void publish(OrchidContext context) {
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(command);
            String directory;
            if (!EdenUtils.isEmpty(cwd)) {
                directory = cwd;
                if (directory.startsWith("~")) {
                    directory = System.getProperty("user.home") + directory.substring(1);
                }
            } else {
                directory = resourcesDir;
            }
            builder.directory(new File(directory));
            Clog.i("[{}]> {}", directory, String.join(" ", command));
            Process process = builder.start();
            Executors.newSingleThreadExecutor().submit(new InputStreamPrinter(process.getInputStream()));
            process.waitFor();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String[] getCommand() {
        return this.command;
    }

    public void setCommand(final String[] command) {
        this.command = command;
    }

    public String getCwd() {
        return this.cwd;
    }

    public void setCwd(final String cwd) {
        this.cwd = cwd;
    }
}
