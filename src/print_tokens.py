from antlr4 import InputStream
from gen.TemplateLexer import TemplateLexer

def main():
    with open("samples/input1.tpl", "r", encoding="utf-8") as f:
        text = f.read()

    lexer = TemplateLexer(InputStream(text))
    toks = lexer.getAllTokens()
    for t in toks:
        print(f"{t.line}:{t.column}\t{lexer.symbolicNames[t.type]}\t{t.text!r}")

if __name__ == "__main__":
    main()
