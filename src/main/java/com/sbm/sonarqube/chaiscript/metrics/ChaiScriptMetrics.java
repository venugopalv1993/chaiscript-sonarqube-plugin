package com.sbm.sonarqube.chaiscript.metrics;

// Importing required SonarQube API classes to define and manage custom metrics
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metric.ValueType;
import org.sonar.api.measures.Metrics;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

// Class implementing the Metrics interface to define custom metrics for ChaiScript
public class ChaiScriptMetrics implements Metrics {

    // Constant for the domain name under which metrics will be grouped
    private static final String DOMAIN_NAME = "ChaiScript";

    // Custom metric definition for ChaiScript coverage as a percentage
    public static final Metric<Serializable> COVERAGE = new Metric.Builder(
            "chaiscript_coverage",       // Unique key for the metric
            "ChaiScript Coverage",       // Display name of the metric
            ValueType.PERCENT            // Value type (percentage) for the metric
        )
        .setDescription("Line coverage of ChaiScript files") // Description of the metric
        .setDomain(DOMAIN_NAME)                              // Grouping under the specified domain
        .setQualitative(true)                                // Indicates the metric is qualitative
        .setDirection(Metric.DIRECTION_BETTER)               // Higher values indicate better quality
        .setBestValue(90.0)                                  // Best possible value for the metric
        .setWorstValue(0.0)                                  // Worst possible value for the metric
        .create();                                           // Finalizes and creates the metric

    @SuppressWarnings("rawtypes") // Suppresses raw type warnings for the List<Metric> return type
    @Override
    public List<Metric> getMetrics() {
        // Returns a list of metrics defined in this class
        return Arrays.asList(COVERAGE);
    }
}
