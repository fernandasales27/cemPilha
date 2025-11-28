package lf1.plp.functional1.expression;

import lf1.plp.expressions1.util.Tipo;
import lf1.plp.functional1.util.TipoPilha;    
import lf1.plp.functional1.util.TipoPolimorfico; 
import lf1.plp.expressions2.expression.ExpBinaria; 
import lf1.plp.expressions2.expression.Expressao;
import lf1.plp.expressions2.expression.Valor;
import lf1.plp.expressions2.expression.ValorPilha;
import lf1.plp.expressions2.memory.AmbienteCompilacao;
import lf1.plp.expressions2.memory.AmbienteExecucao;
import lf1.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf1.plp.expressions2.memory.VariavelNaoDeclaradaException;

/**
 * Representa a expressão binária 'push', que adiciona um elemento ao topo da pilha.
 * Herda de ExpBinaria.
 * O operando da esquerda (esq) é a pilha.
 * O operando da direita (dir) é o elemento.
 */
public class ExpPush extends ExpBinaria {

    /**
     * Construtor da ExpPush.
     * @param expPilha Expressão da pilha (operando esquerdo)
     * @param expElemento Expressão do elemento (operando direito)
     */
    public ExpPush(Expressao expPilha, Expressao expElemento) {
        // Passa as expressões para a classe base e define "push" como o operador
        super(expPilha, expElemento, "push"); 
    }

    /**
     * Avalia a expressão 'push'.
     */
    @Override
    public Valor avaliar(AmbienteExecucao ambiente) 
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        
        // 1. Avalia as sub-expressões (herdadas)
        ValorPilha pilha = (ValorPilha) getEsq().avaliar(ambiente);
        Valor elemento = (Valor) getDir().avaliar(ambiente);
        
        // 2. Retorna a NOVA pilha resultante do push (imutabilidade)
        return pilha.push(elemento); // Assumindo que o método em ValorPilha é 'push'
    }

    /**
     * Checa os tipos dos elementos terminais (pilha e elemento).
     * A classe base ExpBinaria já checa os tipos de getEsq() e getDir().
     */
    @Override
    protected boolean checaTipoElementoTerminal(AmbienteCompilacao amb)
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

        Tipo tipoPilha = getEsq().getTipo(amb);
        Tipo tipoElemento = getDir().getTipo(amb);

        // 1. Verifica se o operando da esquerda é, de fato, uma pilha
        if (!(tipoPilha instanceof TipoPilha)) {
            System.err.println("Erro de Tipo: 'push' esperado em uma pilha. Encontrado: " + tipoPilha.getNome());
            return false;
        }

        TipoPilha tipoPilhaConcreto = (TipoPilha) tipoPilha;
        Tipo tipoElementoDaPilha = tipoPilhaConcreto.getSubTipo(); // Ou getTipoElemento()

        // 2. Verifica se o tipo do elemento é compatível com o tipo da pilha
        boolean tipoOk;

        // Se a pilha for vazia, seu tipo de elemento será polimórfico (null ou TipoPolimorfico)
        // Nesse caso, ela pode aceitar qualquer elemento.
        if (tipoElementoDaPilha == null || tipoElementoDaPilha instanceof TipoPolimorfico) {
            tipoOk = true; 
        } else {
            // Se a pilha não for vazia, o tipo do elemento deve ser igual
            tipoOk = tipoElementoDaPilha.eIgual(tipoElemento);
        }
        
        if (!tipoOk) {
             System.err.println("Erro de Tipo: 'push' em pilha(" + tipoElementoDaPilha.getNome() 
                   + ") com elemento " + tipoElemento.getNome());
        }
        return tipoOk;
    }

    /**
     * Retorna o tipo da expressão 'push'.
     * O resultado é uma pilha cujo tipo de elemento é o do elemento que foi inserido.
     */
    @Override
    public Tipo getTipo(AmbienteCompilacao amb) 
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        Tipo tipoElemento = getDir().getTipo(amb);
        return new TipoPilha(tipoElemento);
    }

    @Override
    public ExpPush clone() {
        return new ExpPush(getEsq().clone(), getDir().clone());
    }
}