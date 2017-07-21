grammar Targeting;

// LEXING

IDENTIFIER : [a-zA-Z]+;
WHITESPACE : ' ' -> skip;

// PARSING

targeting : clause+ ;

clause : path operator value;

path : 'imp.' IDENTIFIER ('.' IDENTIFIER)* ;

operator : 'contains';

value : IDENTIFIER;