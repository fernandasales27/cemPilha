*Projeto PLP*


## CemPilha

### Disciplina

(IN1007) Paradigmas de Linguagens de Programação - Cin/UFPE

### Professor

Augusto Sampaio

### Equipe

Maria Fernanda de Lima Oliveira Sales| mflos@cin.ufpe.br

Jóison Oliveira Pereira | jop@cin.ufpe.br

*Ricardo Pereira Lins | rpl3@cin.ufpe.br

### Contextualização

Em linguagens funcionais, uma estrutura de pilha é útil para gerenciar dados de forma LIFO (Last In, First Out) e controlar a execução de funções, incluindo chamadas recursivas. O objetivo deste projeto é estender a Linguagem Funcional 1 (LF1) para permitir que expressões possam manipular pilhas, adicionando operações como push, pop e top.

A estrutura deve permitir que:

Elementos sejam inseridos na pilha (push) e removidos da pilha (pop).


O valor do topo da pilha seja lido (top) sem remover o elemento.


Funções possam receber uma pilha como argumento, manipular seus elementos e retornar uma nova pilha.


Os valores da pilha poderem ser invertidos.


Somar os valores da pilha.


### Escopo do Projeto

**Estender a Linguagem Funcional 1** apresentada na disciplina para que aa linguagem suporte operadores e funções de pilha, mantendo a semântica funcional.




## BNF

Programa ::= Expressao

Expressao ::= Valor 
            | ExpUnaria 
            | ExpBinaria 
            | ExpDeclaracao 
            | Id 
            | Aplicacao 
            | IfThenElse
            | OperacaoPilha

Valor ::= ValorConcreto
ValorConcreto ::= ValorInteiro | ValorBooleano | ValorString

ExpUnaria ::= "-" Expressao 
            | "not" Expressao 
            | "length" Expressao

ExpBinaria ::= Expressao "+" Expressao 
             | Expressao "-" Expressao 
             | Expressao "and" Expressao 
             | Expressao "or" Expressao 
             | Expressao "==" Expressao 
             | Expressao "++" Expressao

ExpDeclaracao ::= "let" DeclaracaoFuncional "in" Expressao
DeclaracaoFuncional ::= DecVariavel | DecFuncao | DecComposta
DecVariavel ::= "var" Id "=" Expressao
DecFuncao ::= "fun" ListaParametro "=" Expressao
DecComposta ::= DeclaracaoFuncional "," DeclaracaoFuncional

ListaParametro ::= Parametro | Parametro ListaParametro
Parametro ::= ParametroObrigatorio

Aplicacao ::= Id"(" ListExp ")"
ListExp ::= Expressao | Expressao, ListExp

IfThenElse ::= "if" Expressao "then" Expressao "else" Expressao

OperacaoPilha ::= "push" "(" Expressao "," Expressao ")"
                 | "pop" "(" Expressao ")"
                 | "top" "(" Expressao ")"
                 | "emptyStack"



## Slides da apresentação

Disponível 

## Publicando o site


