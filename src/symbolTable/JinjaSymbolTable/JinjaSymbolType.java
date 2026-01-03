package symbolTable.JinjaSymbolTable;

// ===== الأنواع الأساسية =====
public enum JinjaSymbolType {
    // البلوكات
    BLOCK,          // {% block name %}
    MACRO,          // {% macro name() %}

    // المتغيرات
    VARIABLE,       // {% set var = value %}
    LOOP_VARIABLE,  // {% for item in items %}
    PARAMETER,      // parameters in macros

    // التضمينات
    IMPORT,         // {% import "template.html" %}
    INCLUDE,        // {% include "partial.html" %}
    EXTENDS,        // {% extends "base.html" %}

    // الوظائف
    FUNCTION,       // {{ func() }}
    FILTER,         // {{ var|filter }}

    // القيم
    LITERAL,        // strings, numbers, booleans
    TEMPLATE_NAME   // أسماء القوالب
}

