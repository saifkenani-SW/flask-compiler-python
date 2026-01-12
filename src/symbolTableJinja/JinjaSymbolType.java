package symbolTableJinja;

public enum JinjaSymbolType {
    BLOCK,          // {% block name %}
    MACRO,          // {% macro name() %}

    VARIABLE,       // {% set var = value %}
    LOOP_VARIABLE,  // {% for item in items %}
    PARAMETER,      // parameters in macros

    IMPORT,         // {% import "template.html" %}
    INCLUDE,        // {% include "partial.html" %}
    EXTENDS,        // {% extends "base.html" %}

    FUNCTION,       // {{ func() }}
    FILTER,         // {{ var|filter }}

    LITERAL,        // strings, numbers, booleans
    TEMPLATE_NAME   // أسماء القوالب
}

