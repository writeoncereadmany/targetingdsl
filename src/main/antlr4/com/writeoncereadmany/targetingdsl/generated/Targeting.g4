grammar Targeting;

// LEXING

IDENTIFIER : [a-zA-Z]+;
WHITESPACE : ' ' -> skip;

// PARSING

targeting
    : clauses                                       # cases
    | 'either' clauses ('or' clauses)+ 'end'        # alternatives
    ;

clauses : clause+ ;

clause : path condition;

path : 'imp.' IDENTIFIER ('.' IDENTIFIER)* ;

condition
    : 'contains' value   # contains
    | '=' value          # equal
    | 'in' values        # membership
    ;

value : IDENTIFIER;

values : '[' IDENTIFIER (',' IDENTIFIER)+ ']';