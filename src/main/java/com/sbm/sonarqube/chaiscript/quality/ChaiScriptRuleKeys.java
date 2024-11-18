package com.sbm.sonarqube.chaiscript.quality;

// Importing the RuleKey class to define rule keys for the ChaiScript repository
import org.sonar.api.rule.RuleKey;

// Class to define and centralize rule keys for the ChaiScript repository
public class ChaiScriptRuleKeys {

    // Constant for the repository key, uniquely identifying the rules repository
    public static final String REPOSITORY_KEY = "chaiscript-repo";

    // Define rule keys using the repository key and unique rule identifiers
    public static final RuleKey SYNTAX_ERROR = RuleKey.of(REPOSITORY_KEY, "syntax-error"); // Rule for syntax errors
    public static final RuleKey NO_PASSWORD = RuleKey.of(REPOSITORY_KEY, "no-password"); // Rule for detecting passwords
    public static final RuleKey DUPLICATE_VARIABLE_NAME = RuleKey.of(REPOSITORY_KEY, "duplicate-variable-name"); // Rule for duplicate variable names
    public static final RuleKey DUPLICATE_FUNCTION_NAME = RuleKey.of(REPOSITORY_KEY, "duplicate-function-name"); // Rule for duplicate function names
}
