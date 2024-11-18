package com.sbm.sonarqube.chaiscript.sensors;

// Importing necessary classes for metrics, plugin configurations, validators, and SonarQube APIs
import com.sbm.sonarqube.chaiscript.metrics.ChaiScriptMetrics;
import com.sbm.sonarqube.chaiscript.plugin.PluginConfig;
import com.sbm.sonarqube.chaiscript.validation.*;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;
import org.sonar.api.rule.RuleKey;

import javax.annotation.Nonnull;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Sensor implementation for analyzing ChaiScript files
public class ChaiScriptSensor implements Sensor {

    // Plugin configuration to determine which validators are enabled
    private final PluginConfig pluginConfig = new PluginConfig();

    @Override
    public void describe(@Nonnull SensorDescriptor descriptor) {
        descriptor.name("ChaiScript Sensor"); // Define the sensor's name for SonarQube
    }

    @Override
    public void execute(@Nonnull SensorContext context) {
        // Base directory for the analysis
        String sourceDir = context.fileSystem().baseDir().getPath();
        Path path = Paths.get(sourceDir);

        // Initialize the validators based on the plugin configuration
        List<ChaiScriptValidator> validators = new ArrayList<>();
        if (pluginConfig.isSyntaxValidatorEnabled()) validators.add(new SyntaxValidator()); // Add syntax validator
        if (pluginConfig.isPasswordValidatorEnabled()) validators.add(new PasswordValidator()); // Add password validator
        if (pluginConfig.isDuplicateVariableNameValidatorEnabled()) validators.add(new DuplicateVariableNameValidator()); // Add duplicate variable name validator
        if (pluginConfig.isDuplicateFunctionNameValidatorEnabled()) validators.add(new DuplicateFunctionNameValidator()); // Add duplicate function name validator

        // Process each file in the source directory
        try (Stream<Path> paths = Files.walk(path)) {
            paths.filter(Files::isRegularFile) // Filter to include only files
                 .filter(p -> p.toString().endsWith(".chai")) // Analyze only .chai files
                 .forEach(file -> analyzeChaiScriptFile(context, file, validators)); // Analyze each file
        } catch (Exception e) {
            System.err.println("Error walking through the files: " + e.getMessage()); // Handle errors during file traversal
        }
    }

    // Analyze a specific ChaiScript file
    private void analyzeChaiScriptFile(SensorContext context, Path file, List<ChaiScriptValidator> validators) {
        try {
            String content = new String(Files.readAllBytes(file)); // Read the file content

            // Run each validator on the file content
            for (ChaiScriptValidator validator : validators) {
                try {
                    validator.validate(content); // Validate content using the current validator
                } catch (Exception e) {
                    createNewIssue(context, file, e.getMessage(), validator.getRuleKey()); // Create an issue for validation failure
                }
            }

            // Calculate coverage as a simple measure of lines
            int lines = countLines(file);
            context.newMeasure()
                   .forMetric(ChaiScriptMetrics.COVERAGE) // Associate coverage metric
                   .on(context.fileSystem().inputFile(context.fileSystem().predicates().hasPath(file.toString()))) // Bind to the analyzed file
                   .withValue(Math.min(100.0, Math.max(0.0, lines))) // Ensure the value is between 0.0 and 100.0
                   .save(); // Save the measure

        } catch (Exception e) {
            System.err.println("Unexpected error processing file " + file + ": " + e.getMessage()); // Handle unexpected errors
        }
    }

    // Count the number of lines in a file
    private int countLines(Path file) {
        try (Stream<String> lines = Files.lines(file)) {
            return (int) lines.count(); // Count the lines in the file
        } catch (Exception e) {
            System.err.println("Failed to count lines for file " + file + ": " + e.getMessage()); // Handle line counting errors
            return 0;
        }
    }

    // Create a new issue in SonarQube for a validation failure
    private void createNewIssue(SensorContext context, Path file, String message, RuleKey ruleKey) {
        NewIssue newIssue = context.newIssue().forRule(ruleKey); // Create a new issue for the rule

        // Define the location and message for the issue
        NewIssueLocation newIssueLocation = newIssue.newLocation()
                .on(context.fileSystem().inputFile(context.fileSystem().predicates().hasPath(file.toString())))
                .message(message);

        newIssue.at(newIssueLocation).save(); // Save the issue in SonarQube
    }
}
