from antlr4 import FileStream, CommonTokenStream
from src.gen.TemplateLexer import TemplateLexer
from src.gen.TemplateParser import TemplateParser

def main():
    input_stream = FileStream("samples/input1.tpl", encoding="utf-8")
    lexer = TemplateLexer(input_stream)
    stream = CommonTokenStream(lexer)

    parser = TemplateParser(stream)
    tree = parser.template()  # ✅ قاعدة البداية

    print(tree.toStringTree(recog=parser))

if __name__ == "__main__":
    main()
