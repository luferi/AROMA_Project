// Generated from C:/LDRStuff/Private/MyDevel/AROMA/AROMA_Languages/DynamicRecipe/src/main/antlr/DynamicRecipe.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DynamicRecipeParser}.
 */
public interface DynamicRecipeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#recipe}.
	 * @param ctx the parse tree
	 */
	void enterRecipe(DynamicRecipeParser.RecipeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#recipe}.
	 * @param ctx the parse tree
	 */
	void exitRecipe(DynamicRecipeParser.RecipeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#description}.
	 * @param ctx the parse tree
	 */
	void enterDescription(DynamicRecipeParser.DescriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#description}.
	 * @param ctx the parse tree
	 */
	void exitDescription(DynamicRecipeParser.DescriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#ingredients}.
	 * @param ctx the parse tree
	 */
	void enterIngredients(DynamicRecipeParser.IngredientsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#ingredients}.
	 * @param ctx the parse tree
	 */
	void exitIngredients(DynamicRecipeParser.IngredientsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#ingredient}.
	 * @param ctx the parse tree
	 */
	void enterIngredient(DynamicRecipeParser.IngredientContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#ingredient}.
	 * @param ctx the parse tree
	 */
	void exitIngredient(DynamicRecipeParser.IngredientContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#ingredient_ratio}.
	 * @param ctx the parse tree
	 */
	void enterIngredient_ratio(DynamicRecipeParser.Ingredient_ratioContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#ingredient_ratio}.
	 * @param ctx the parse tree
	 */
	void exitIngredient_ratio(DynamicRecipeParser.Ingredient_ratioContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#mix_function_info}.
	 * @param ctx the parse tree
	 */
	void enterMix_function_info(DynamicRecipeParser.Mix_function_infoContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#mix_function_info}.
	 * @param ctx the parse tree
	 */
	void exitMix_function_info(DynamicRecipeParser.Mix_function_infoContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#resources}.
	 * @param ctx the parse tree
	 */
	void enterResources(DynamicRecipeParser.ResourcesContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#resources}.
	 * @param ctx the parse tree
	 */
	void exitResources(DynamicRecipeParser.ResourcesContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#resource}.
	 * @param ctx the parse tree
	 */
	void enterResource(DynamicRecipeParser.ResourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#resource}.
	 * @param ctx the parse tree
	 */
	void exitResource(DynamicRecipeParser.ResourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(DynamicRecipeParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(DynamicRecipeParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(DynamicRecipeParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(DynamicRecipeParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#range}.
	 * @param ctx the parse tree
	 */
	void enterRange(DynamicRecipeParser.RangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#range}.
	 * @param ctx the parse tree
	 */
	void exitRange(DynamicRecipeParser.RangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#numeric_range}.
	 * @param ctx the parse tree
	 */
	void enterNumeric_range(DynamicRecipeParser.Numeric_rangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#numeric_range}.
	 * @param ctx the parse tree
	 */
	void exitNumeric_range(DynamicRecipeParser.Numeric_rangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#interval_range}.
	 * @param ctx the parse tree
	 */
	void enterInterval_range(DynamicRecipeParser.Interval_rangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#interval_range}.
	 * @param ctx the parse tree
	 */
	void exitInterval_range(DynamicRecipeParser.Interval_rangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#enumerated_range}.
	 * @param ctx the parse tree
	 */
	void enterEnumerated_range(DynamicRecipeParser.Enumerated_rangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#enumerated_range}.
	 * @param ctx the parse tree
	 */
	void exitEnumerated_range(DynamicRecipeParser.Enumerated_rangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#flow}.
	 * @param ctx the parse tree
	 */
	void enterFlow(DynamicRecipeParser.FlowContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#flow}.
	 * @param ctx the parse tree
	 */
	void exitFlow(DynamicRecipeParser.FlowContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaration(DynamicRecipeParser.VariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaration(DynamicRecipeParser.VariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#numericVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterNumericVariableDeclaration(DynamicRecipeParser.NumericVariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#numericVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitNumericVariableDeclaration(DynamicRecipeParser.NumericVariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#booleanVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterBooleanVariableDeclaration(DynamicRecipeParser.BooleanVariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#booleanVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitBooleanVariableDeclaration(DynamicRecipeParser.BooleanVariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#relationalOperator}.
	 * @param ctx the parse tree
	 */
	void enterRelationalOperator(DynamicRecipeParser.RelationalOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#relationalOperator}.
	 * @param ctx the parse tree
	 */
	void exitRelationalOperator(DynamicRecipeParser.RelationalOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#stringVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStringVariableDeclaration(DynamicRecipeParser.StringVariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#stringVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStringVariableDeclaration(DynamicRecipeParser.StringVariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#flowDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFlowDeclaration(DynamicRecipeParser.FlowDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#flowDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFlowDeclaration(DynamicRecipeParser.FlowDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#sequence}.
	 * @param ctx the parse tree
	 */
	void enterSequence(DynamicRecipeParser.SequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#sequence}.
	 * @param ctx the parse tree
	 */
	void exitSequence(DynamicRecipeParser.SequenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(DynamicRecipeParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(DynamicRecipeParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(DynamicRecipeParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(DynamicRecipeParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#parallel}.
	 * @param ctx the parse tree
	 */
	void enterParallel(DynamicRecipeParser.ParallelContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#parallel}.
	 * @param ctx the parse tree
	 */
	void exitParallel(DynamicRecipeParser.ParallelContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#repetition}.
	 * @param ctx the parse tree
	 */
	void enterRepetition(DynamicRecipeParser.RepetitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#repetition}.
	 * @param ctx the parse tree
	 */
	void exitRepetition(DynamicRecipeParser.RepetitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#decision}.
	 * @param ctx the parse tree
	 */
	void enterDecision(DynamicRecipeParser.DecisionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#decision}.
	 * @param ctx the parse tree
	 */
	void exitDecision(DynamicRecipeParser.DecisionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#decisionTrue}.
	 * @param ctx the parse tree
	 */
	void enterDecisionTrue(DynamicRecipeParser.DecisionTrueContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#decisionTrue}.
	 * @param ctx the parse tree
	 */
	void exitDecisionTrue(DynamicRecipeParser.DecisionTrueContext ctx);
	/**
	 * Enter a parse tree produced by {@link DynamicRecipeParser#decisionFalse}.
	 * @param ctx the parse tree
	 */
	void enterDecisionFalse(DynamicRecipeParser.DecisionFalseContext ctx);
	/**
	 * Exit a parse tree produced by {@link DynamicRecipeParser#decisionFalse}.
	 * @param ctx the parse tree
	 */
	void exitDecisionFalse(DynamicRecipeParser.DecisionFalseContext ctx);
}