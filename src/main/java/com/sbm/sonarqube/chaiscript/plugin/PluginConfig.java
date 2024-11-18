package com.sbm.sonarqube.chaiscript.plugin;

// Class representing the configuration settings for the plugin
public class PluginConfig {

    // Flags to enable or disable specific validators for ChaiScript analysis
    private final boolean syntaxValidatorEnabled = true; // Syntax validation enabled
    private final boolean passwordValidatorEnabled = true; // Password validation enabled
    private final boolean duplicateVariableNameValidatorEnabled = true; // Duplicate variable name validation enabled
    private final boolean duplicateFunctionNameValidatorEnabled = true; // Duplicate function name validation enabled
    private final boolean duplicateCodeBlockValidatorEnabled = true; // Duplicate code block validation enabled

    // Getter method to check if syntax validation is enabled
    public boolean isSyntaxValidatorEnabled() {
        return syntaxValidatorEnabled;
    }

    // Getter method to check if password validation is enabled
    public boolean isPasswordValidatorEnabled() {
        return passwordValidatorEnabled;
    }

    // Getter method to check if duplicate variable name validation is enabled
    public boolean isDuplicateVariableNameValidatorEnabled() {
        return duplicateVariableNameValidatorEnabled;
    }

    // Getter method to check if duplicate function name validation is enabled
    public boolean isDuplicateFunctionNameValidatorEnabled() {
        return duplicateFunctionNameValidatorEnabled;
    }

    // Getter method to check if duplicate code block validation is enabled
    public boolean isDuplicateCodeBlockValidatorEnabled() {
        return duplicateCodeBlockValidatorEnabled;
    }
}
