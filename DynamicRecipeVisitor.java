// Generated from C:/LDRStuff/Private/MyDevel/AROMA/AROMA_Languages/DynamicRecipe/src/main/antlr/DynamicRecipe.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DynamicRecipeParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DynamicRecipeVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#recipe}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecipe(DynamicRecipeParser.RecipeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#description}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescription(DynamicRecipeParser.DescriptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#ingredients}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIngredients(DynamicRecipeParser.IngredientsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#ingredient}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIngredient(DynamicRecipeParser.IngredientContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#ingredient_ratio}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIngredient_ratio(DynamicRecipeParser.Ingredient_ratioContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#mix_function_info}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMix_function_info(DynamicRecipeParser.Mix_function_infoContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#resources}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResources(DynamicRecipeParser.ResourcesContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#resource}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResource(DynamicRecipeParser.ResourceContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(DynamicRecipeParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(DynamicRecipeParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#range}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRange(DynamicRecipeParser.RangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#numeric_range}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeric_range(DynamicRecipeParser.Numeric_rangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#interval_range}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterval_range(DynamicRecipeParser.Interval_rangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#enumerated_range}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumerated_range(DynamicRecipeParser.Enumerated_rangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#flow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlow(DynamicRecipeParser.FlowContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(DynamicRecipeParser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#numericVariableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericVariableDeclaration(DynamicRecipeParser.NumericVariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#booleanVariableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanVariableDeclaration(DynamicRecipeParser.BooleanVariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#relationalOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalOperator(DynamicRecipeParser.RelationalOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#stringVariableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringVariableDeclaration(DynamicRecipeParser.StringVariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#flowDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlowDeclaration(DynamicRecipeParser.FlowDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#sequence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSequence(DynamicRecipeParser.SequenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(DynamicRecipeParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(DynamicRecipeParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#parallel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParallel(DynamicRecipeParser.ParallelContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#repetition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepetition(DynamicRecipeParser.RepetitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#decision}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecision(DynamicRecipeParser.DecisionContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#decisionTrue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecisionTrue(DynamicRecipeParser.DecisionTrueContext ctx);
	/**
	 * Visit a parse tree produced by {@link DynamicRecipeParser#decisionFalse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecisionFalse(DynamicRecipeParser.DecisionFalseContext ctx);
}