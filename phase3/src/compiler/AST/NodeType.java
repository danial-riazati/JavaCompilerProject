//TODO
package compiler.AST;

/**
 * An enum to declare type of an AST node
 */
public enum NodeType {
    /*Mathematics operations*/
    ADDITION,
    SUBTRACTION,
    MULTIPLICATION,
    DIVISION,
    MOD,
    NEGATIVE,
    READ_INTEGER,
    READ_LINE,
    NEW_ARRAY,
    NEW_IDENTIFIER,

    ITOB,
    ITOD,
    DTOI,
    BTOI,
    LVALUE,
    CALL,
    EMPTY_ARRAY,

    /*Condition operations*/
    LESS_THAN,
    LESS_THAN_OR_EQUAL,
    GREATER_THAN,
    GREATER_THAN_OR_EQUAL,
    EQUAL,
    NOT_EQUAL,

    /*Logical operations*/
    BOOLEAN_AND,
    BOOLEAN_NOT,
    BOOLEAN_OR,

    /*Types*/
    BOOLEAN_TYPE,
    DOUBLE_TYPE,
    CHAR_TYPE,
    INT_TYPE,
    FLOAT_TYPE,
    LONG_TYPE,
    STRING_TYPE,
    VOID,
    AUTO_TYPE,

    THIS,

    /*Declarations*/
    FIELD_DECLARATION,
    LOCAL_VAR_DECLARATION,
    VARIABLE_DECLARATION,
    VARIABLE_CONST_DECLARATION,
    METHOD_DECLARATION,
    Class_DECLARATION,
    DECLARATIONS,

    /*Assignments*/
    ASSIGN,
    ADD_ASSIGN,
    SUB_ASSIGN,
    MULT_ASSIGN,
    DIV_ASSIGN,

    /*Statements*/
    STATEMENT,
    STATEMENTS,
    EXPRESSION_STATEMENT,
    BREAK_STATEMENT,
    CONTINUE_STATEMENT,
    RETURN_STATEMENT,
    IF_STATEMENT,
    REPEAT_STATEMENT,
    SWITCH_STATEMENT,
    CASE_STATEMENT,
    FOR_STATEMENT,
    WHILE_STATEMENT,
    PRINT_STATEMENT,
    LITERAL,
    ARGUMENT,
    ARGUMENTS,
    EMPTY_STATEMENT,
    IDENTIFIER,
    METHOD_ACCESS,
    PRIVATE_ACCESS,
    PUBLIC_ACCESS,
    PROTECTED_ACCESS,
    VARIABLES,
    ACTUALS,
    PARAMETER,
    PARAMETERS,
    BLOCK,
    VAR_USE,
    CLASS,
    Interface,
    NULL_LITERAL,
    EXTEND,
    IMPLEMENT,
    FIELDS,
    PROTOTYPES,
    PROTOTYPE,
    EXPRESSIONS,
    START,
    LINE,
    FUNC
}
