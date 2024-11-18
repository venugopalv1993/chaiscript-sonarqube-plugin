package com.sbm.sonarqube.chaiscript.quality;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.rule.RuleStatus;
import org.sonar.api.rules.RuleType;

import javax.annotation.Nonnull; // Import Nonnull annotation


public class ChaiScriptRulesDefinition implements RulesDefinition {

    public static final String REPOSITORY_KEY = "chaiscript-repo"; // Key for the rule repository
    public static final String REPOSITORY_NAME = "ChaiScript Rules"; // Name of the repository

    @Override
    public void define(@Nonnull Context context) {
        // Create the new repository
        NewRepository repository = context.createRepository(REPOSITORY_KEY, "chai") // Use the correct language code
                .setName(REPOSITORY_NAME);

        // Register the predefined rules
        registerRules(repository);

        // Finalize the repository definition
        repository.done(); // Finish defining the repository
    }

    private void registerRules(NewRepository repository) {
        // Register the NoPasswordRule
        repository.createRule(ChaiScriptRuleKeys.NO_PASSWORD.rule()) // Use the rule key directly
            .setName("No Password in Code") // Set the name of the rule
            .setHtmlDescription("This rule checks for the presence of the word 'password' in the code, which may indicate sensitive information.") // Set the HTML description
            .setStatus(RuleStatus.READY) // Set the rule status
            .setSeverity("CRITICAL") // Set severity
            .setType(RuleType.VULNERABILITY); // Set as Security issue

        // Duplicate function name rule
        repository.createRule(ChaiScriptRuleKeys.DUPLICATE_VARIABLE_NAME.rule())
            .setName("Duplicate Variable Name")
            .setHtmlDescription("Duplicate variable name in ChaiScript.")
            .setStatus(RuleStatus.READY) // Set the rule status
            .setSeverity("MAJOR")
            .setType(RuleType.CODE_SMELL);  // Set as Code Smell (Maintainability)

        repository.createRule(ChaiScriptRuleKeys.SYNTAX_ERROR.rule()) // Example for a syntax error rule
            .setName("Syntax Error")
            .setHtmlDescription("Detects syntax errors in ChaiScript.")
            .setStatus(RuleStatus.READY)
            .setSeverity("BLOCKER")
            .setType(RuleType.BUG);  // Set as Bug (Reliability)

        repository.createRule(ChaiScriptRuleKeys.DUPLICATE_FUNCTION_NAME.rule())
            .setName("Duplicate Function Name")
            .setHtmlDescription("Duplicate function name in ChaiScript.")
            .setStatus(RuleStatus.READY) // Set the rule status
            .setSeverity("MAJOR")
            .setType(RuleType.CODE_SMELL);  // Set as Code Smell (Maintainability)


    }

}
