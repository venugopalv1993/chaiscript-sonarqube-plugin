package com.sbm.sonarqube.chaiscript.validation;

import org.sonar.api.rule.RuleKey;

import com.sbm.sonarqube.chaiscript.quality.ChaiScriptRuleKeys;


import java.util.HashSet;

public class DuplicateVariableNameValidator implements ChaiScriptValidator {

    @Override
    public void validate(String content) throws Exception {
        HashSet<String> declaredVariables = new HashSet<>();
        
        // Split the content into lines for processing
        String[] lines = content.split("\\n");
        for (String line : lines) {
            line = line.trim();
            // Check for variable declarations (var, let, const)
            if (line.startsWith("var ") || line.startsWith("let ") || line.startsWith("const ")) {
                String variableName = extractVariableName(line);
                if (variableName != null && !declaredVariables.add(variableName)) {
                    throw new Exception("Duplicate declaration of variable: " + variableName);
                }
            }
        }
    }

    private String extractVariableName(String line) {
        // Extract variable name from the declaration line
        String[] parts = line.split("\\s+");
        if (parts.length >= 2) {
            return parts[1]; // Return the variable name (second part)
        }
        return null; // Not a valid declaration line
    }

    @Override
    public RuleKey getRuleKey() {
        return RuleKey.of(ChaiScriptRuleKeys.REPOSITORY_KEY, ChaiScriptRuleKeys.DUPLICATE_VARIABLE_NAME.rule());
    }
}
