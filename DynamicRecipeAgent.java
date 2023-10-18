import jade.core.Agent;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.util.Map;

public class DynamicRecipeAgent extends Agent {

    @Override
    protected void setup() {
        String fileName = "C:\\Users\\Luiri60\\Desktop\\AromaRecipe1.txt";
        DynamicRecipeLexer lexer = null;
        try {
            lexer = new DynamicRecipeLexer(CharStreams.fromFileName(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        DynamicRecipeParser parser = new DynamicRecipeParser(tokens);
        ParseTree tree = parser.recipe();
        ParseTreeWalker walker = new ParseTreeWalker();
        SymbolDefinitionPhase def = new SymbolDefinitionPhase();
        walker.walk(def, tree);

        System.out.println("New Recipe uploaded!");
        System.out.println("Parsing Recipe with description:");
        System.out.println(def.getDescription());
        System.out.println("#########################");
        System.out.println("List of Ingredients for the recipe:");
        for(Ingredient ingredient : def.getIngredients().values()){
            System.out.println(ingredient.toString());
        }
        System.out.println("#########################");
        System.out.println("Preferred Ingredient ratio:");
        for(Map.Entry e : def.getRatio().entrySet()){
            System.out.println(e.getKey() + " " + e.getValue());
        }
        System.out.println("#########################");
        System.out.println("List of Resources for the recipe");
        for(Resource resource : def.getResources().values()){
            System.out.println(resource.toString());
        }
        System.out.println("#########################");


    }
}
