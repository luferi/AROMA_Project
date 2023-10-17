import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class SymbolDefinitionPhase extends DynamicRecipeBaseListener {

    Map<String, Symbol> symbols;

    String descritption;

    HashMap<String, Ingredient> ingredients;
    HashMap<String, Integer> ratio;
    Ingredient currentIngredient;
    Parameter currentParameter;

    String mixFunctionSignature;
    String mixFunctionPackage;

    HashMap<String, Resource> resources;

    Resource currentResource;

    IntervalRange currentIntervalRange;

    Stack<FlowDeclaration> flows;


    @Override
    public void exitRecipe(DynamicRecipeParser.RecipeContext ctx) {
        System.out.println("Description " + descritption);
        System.out.println("Ingredients " + ingredients);
        System.out.println("Ratio " + ratio);
        System.out.println("Mix function signature " + mixFunctionSignature);
        System.out.println("Mix function package " + mixFunctionPackage);
        System.out.println("Flows " + flows.size());
    }

    @Override
    public void enterDescription(DynamicRecipeParser.DescriptionContext ctx) {
        descritption = ctx.STRING_LITERAL().getText();
    }

    @Override
    public void enterIngredients(DynamicRecipeParser.IngredientsContext ctx) {
        ingredients = new HashMap<>();
    }

    @Override
    public void enterIngredient(DynamicRecipeParser.IngredientContext ctx) {
        currentIngredient = new Ingredient();
        currentIngredient.name = ctx.IDENTIFIER().getText();
    }

    @Override
    public void exitIngredient(DynamicRecipeParser.IngredientContext ctx) {
        ingredients.put(currentIngredient.name, currentIngredient);
        currentIngredient = null;
    }

    @Override
    public void enterNumeric_range(DynamicRecipeParser.Numeric_rangeContext ctx) {
        NumericRange range = new NumericRange(Integer.parseInt(ctx.NUMBER_LITERAL(0).getText()), Integer.parseInt(ctx.NUMBER_LITERAL(1).getText()));
        if (currentIngredient != null) {
            currentIngredient.range = range;
        } else if (currentIntervalRange != null) {
            currentIntervalRange.intervals.add(range);
        } else if (currentParameter != null) {
            currentParameter.range = range;
        }
    }

    @Override
    public void enterEnumerated_range(DynamicRecipeParser.Enumerated_rangeContext ctx) {
        EnumeratedRange enumeratedRange = new EnumeratedRange();
        enumeratedRange.values = new ArrayList<>();
        for (TerminalNode t : ctx.STRING_LITERAL())
            enumeratedRange.values.add(t.getText());
        if (currentIngredient != null) {
            currentIngredient.range = enumeratedRange;
        } else if (currentParameter != null) {
            currentParameter.range = enumeratedRange;
        }
    }

    @Override
    public void enterInterval_range(DynamicRecipeParser.Interval_rangeContext ctx) {
        IntervalRange intervalRange = new IntervalRange();
        intervalRange.intervals = new ArrayList<>();
        currentIntervalRange = intervalRange;
    }

    @Override
    public void exitInterval_range(DynamicRecipeParser.Interval_rangeContext ctx) {
        currentIntervalRange = null;
    }

    @Override
    public void enterIngredient_ratio(DynamicRecipeParser.Ingredient_ratioContext ctx) {
        ratio = new HashMap<>();
        for (int i = 0; i < ctx.IDENTIFIER().size(); i++) {
            ratio.put(ctx.IDENTIFIER().get(i).getText(), Integer.parseInt(ctx.NUMBER_LITERAL().get(i).getText()));
        }
    }


    @Override
    public void enterMix_function_info(DynamicRecipeParser.Mix_function_infoContext ctx) {
        mixFunctionSignature = ctx.STRING_LITERAL(0).getText();
        mixFunctionPackage = ctx.STRING_LITERAL(1).getText();
    }


    @Override
    public void enterResources(DynamicRecipeParser.ResourcesContext ctx) {
        resources = new HashMap<String, Resource>();
    }

    @Override
    public void enterResource(DynamicRecipeParser.ResourceContext ctx) {
        Resource res = new Resource();
        res.name = ctx.IDENTIFIER().getText();
        res.functions = new ArrayList<Function>();
        currentResource = res;
    }

    @Override
    public void exitResource(DynamicRecipeParser.ResourceContext ctx) {
        resources.put(currentResource.name, currentResource);
        currentResource = null;
    }


    @Override
    public void enterFunction(DynamicRecipeParser.FunctionContext ctx) {
        Function fun = new Function();
        fun.name = ctx.IDENTIFIER().getText();
        currentResource.functions.add(fun);
    }

    @Override
    public void enterParameter(DynamicRecipeParser.ParameterContext ctx) {
        Function fun = currentResource.functions.getLast();
        Parameter parameter = new Parameter();
        if (ctx.INPUT_PARAMETER() != null) {
            parameter.name = ctx.IDENTIFIER().getText();
            if (ctx.BOOL() != null) {
                parameter.type = AromaType.BOOL;
            } else if (ctx.NUMBER() != null) {
                parameter.type = AromaType.NUMBER;
            } else if (ctx.STRING() != null) {
                parameter.type = AromaType.STRING;
            } else if (ctx.ENUMERATION() != null) {
                parameter.type = AromaType.ENUMERATION;
            }
            if (fun.inputParameters == null)
                fun.inputParameters = new ArrayList<>();
            fun.inputParameters.add(parameter);
        } else if (ctx.OUTPUT_PARAMETER() != null) {
            parameter.name = ctx.IDENTIFIER().getText();
            if (ctx.BOOL() != null) {
                parameter.type = AromaType.BOOL;
            } else if (ctx.NUMBER() != null) {
                parameter.type = AromaType.NUMBER;
            } else if (ctx.STRING() != null) {
                parameter.type = AromaType.STRING;
            } else if (ctx.ENUMERATION() != null) {
                parameter.type = AromaType.ENUMERATION;
            }
            if (fun.outputParameters == null)
                fun.outputParameters = new ArrayList<>();
            fun.outputParameters.add(parameter);
        }
        currentParameter = parameter;
    }

    @Override
    public void exitParameter(DynamicRecipeParser.ParameterContext ctx) {
        currentParameter = null;
    }

    @Override
    public void enterVariableDeclaration(DynamicRecipeParser.VariableDeclarationContext ctx) {
        symbols = new HashMap<>();
    }


    @Override
    public void enterBooleanVariableDeclaration(DynamicRecipeParser.BooleanVariableDeclarationContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        Symbol boolVar = new Symbol(name, AromaType.BOOL);
        if (ctx.BOOL_LITERAL() != null) {
            boolVar.value = ctx.BOOL_LITERAL().getText();
        }
        symbols.put(name, boolVar);
    }

    @Override
    public void enterNumericVariableDeclaration(DynamicRecipeParser.NumericVariableDeclarationContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        Symbol numVar = new Symbol(name, AromaType.NUMBER);
        if (ctx.NUMBER_LITERAL() != null) {
            numVar.value = ctx.NUMBER_LITERAL().getText();
        }
        symbols.put(name, numVar);
    }

    @Override
    public void enterStringVariableDeclaration(DynamicRecipeParser.StringVariableDeclarationContext ctx) {
        String name = ctx.IDENTIFIER().getText();
        Symbol numVar = new Symbol(name, AromaType.NUMBER);
        if (ctx.STRING_LITERAL() != null) {
            numVar.value = ctx.STRING_LITERAL().getText();
        }
        symbols.put(name, numVar);
    }

    @Override
    public void enterSequence(DynamicRecipeParser.SequenceContext ctx) {
        Sequence seq = new Sequence();
        if (flows == null) {
            flows = new Stack<>();
            flows.push(seq);
        } else {
            FlowDeclaration lastflow = flows.peek();
            lastflow.addFlow(seq);
            flows.push(seq);
        }
    }

    @Override
    public void exitSequence(DynamicRecipeParser.SequenceContext ctx) {
        if (flows.size() > 1)
            flows.pop();
    }

    @Override
    public void enterRepetition(DynamicRecipeParser.RepetitionContext ctx) {
        Repetition repetition = new Repetition();
        repetition.operand1 = ctx.IDENTIFIER(0).getText();
        if(ctx.IDENTIFIER(1) != null){
            repetition.operand2 = ctx.IDENTIFIER(1).getText();
        }else{
            repetition.numberLiteral2 = ctx.NUMBER_LITERAL().getText();
        }
        repetition.operator = ctx.relationalOperator().getText();

        if (flows == null) {
            flows = new Stack<>();
            flows.push(repetition);
        } else {
            FlowDeclaration lastflow = flows.peek();
            lastflow.addFlow(repetition);
            flows.push(repetition);
        }
    }

    @Override
    public void exitRepetition(DynamicRecipeParser.RepetitionContext ctx) {
        if (flows.size() > 1)
            flows.pop();
    }

    @Override
    public void enterParallel(DynamicRecipeParser.ParallelContext ctx) {
        Parallel pll = new Parallel();
        if (flows == null) {
            flows = new Stack<>();
            flows.push(pll);
        } else {
            FlowDeclaration lastflow = flows.peek();
            lastflow.addFlow(pll);
            flows.push(pll);
        }
    }

    @Override
    public void exitParallel(DynamicRecipeParser.ParallelContext ctx) {
        if (flows.size() > 1)
            flows.pop();
    }

    @Override
    public void enterDecision(DynamicRecipeParser.DecisionContext ctx) {
        Decision decision = new Decision();
        decision.operand1 = ctx.IDENTIFIER(0).getText();
        if(ctx.IDENTIFIER(1) != null){
            decision.operand2 = ctx.IDENTIFIER(1).getText();
        }else{
            decision.numberLiteral2 = ctx.NUMBER_LITERAL().getText();
        }
        decision.operator = ctx.relationalOperator().getText();

        if (flows == null) {
            flows = new Stack<>();
            flows.push(decision);
        } else {
            FlowDeclaration lastflow = flows.peek();
            lastflow.addFlow(decision);
            flows.push(decision);
        }
    }

    @Override
    public void exitDecision(DynamicRecipeParser.DecisionContext ctx) {
        if (flows.size() > 1)
            flows.pop();
    }

    @Override
    public void enterFunctionCall(DynamicRecipeParser.FunctionCallContext ctx) {
        Atomic atomic = new Atomic();
        atomic.params = new ArrayList<>();
        atomic.resource = ctx.IDENTIFIER(0).getText();
        atomic.function = ctx.IDENTIFIER(1).getText();
        for (int i = 0; i < ctx.literal().size(); i++){
            atomic.params.add(ctx.literal(i).getText());
        }
        flows.peek().addFlow(atomic);
    }
}
