lexer grammar JinjaFlaskLexer;

ID: [a-zA-Z_][a-zA-Z_0-9-.$]*;
NUMBER: [0-9]+ ('.' [0-9]+)?;
STRING: '\'' ( ~['\\\r\n] | '\\' . )* '\'' | '"' ( ~["\\\r\n] | '\\' . )* '"';