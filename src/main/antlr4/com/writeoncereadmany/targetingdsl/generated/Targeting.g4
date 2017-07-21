grammar Targeting;

// LEXING

IDENTIFIER : [a-zA-Z]+;
WHITESPACE : ' ' -> skip;

// PARSING

targeting : clause+ ;

clause : path condition;

path : 'imp.' IDENTIFIER ('.' IDENTIFIER)* ;

condition
    : single_operator value
    | list_operator values;

single_operator
    : 'contains'
    | '=';

list_operator : 'in';

value : IDENTIFIER;

values : '[' IDENTIFIER (',' IDENTIFIER)+ ']';