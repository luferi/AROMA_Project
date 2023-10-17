import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String fileName = "C:\\Users\\Luiri60\\Desktop\\AromaRecipe1.txt";
        DynamicRecipeLexer lexer = new DynamicRecipeLexer(CharStreams.fromFileName(fileName));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        DynamicRecipeParser parser = new DynamicRecipeParser(tokens);
        ParseTree tree = parser.recipe();
        ParseTreeWalker walker = new ParseTreeWalker();
        SymbolDefinitionPhase def = new SymbolDefinitionPhase();
        walker.walk(def, tree);
    }
}
