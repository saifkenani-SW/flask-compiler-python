from antlr4 import InputStream, CommonTokenStream
from gen.TemplateLexer import TemplateLexer
from gen.TemplateParser import TemplateParser

def main():
    with open("samples/input1.tpl", "r", encoding="utf-8") as f:
        text = f.read()

    lexer = TemplateLexer(InputStream(text))
    tokens = CommonTokenStream(lexer)
    parser = TemplateParser(tokens)

    tree = parser.document()
    print(tree.toStringTree(recog=parser))

if __name__ == "__main__":
    main()
