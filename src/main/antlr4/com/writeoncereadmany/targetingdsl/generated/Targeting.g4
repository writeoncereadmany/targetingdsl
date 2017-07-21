grammar Targeting;

// LEXING

IDENTIFIER : [a-zA-Z]+;
WHITESPACE : ' ' -> skip;

// PARSING

targeting
    : clause+ ;

clause : path condition                                 # case
       | 'either' targeting ('or' targeting)+ 'end'     # alternative
       ;

path : 'imp.' IDENTIFIER ('.' IDENTIFIER)* ;

condition
    : 'contains' value   # contains
    | '=' value          # equal
    | 'in' values        # membership
    ;

value : IDENTIFIER;

values : '[' IDENTIFIER (',' IDENTIFIER)+ ']';