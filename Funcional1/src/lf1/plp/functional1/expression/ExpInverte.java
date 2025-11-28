package lf1.plp.functional1.expression;

import java.util.Stack;

import lf1.plp.expressions1.util.Tipo;
import lf1.plp.functional1.util.TipoPilha;
import lf1.plp.expressions2.expression.ExpUnaria;
import lf1.plp.expressions2.expression.Expressao;
import lf1.plp.expressions2.expression.Valor;
import lf1.plp.expressions2.expression.ValorPilha;
import lf1.plp.expressions2.memory.AmbienteCompilacao;
import lf1.plp.expressions2.memory.AmbienteExecucao;
import lf1.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf1.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf1.plp.functional1.util.PilhaVaziaException;

/**
 * Expressão unária 'inverte' que inverte todos os elementos de uma pilha.
 */
public class ExpInverte extends ExpUnaria {

    /**
     * Construtor: recebe a expressão da pilha
     */
    public ExpInverte(Expressao expPilha) {
        super(expPilha, "inverte");
    }

    /**
     * Avaliação: retorna uma nova pilha com os elementos invertidos
     */
    @Override
    public Valor avaliar(AmbienteExecucao ambiente)
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

        ValorPilha pilhaOriginal = (ValorPilha) getExp().avaliar(ambiente);
        Stack<Valor> novaStack = (Stack<Valor>) pilhaOriginal.valor().clone();

        // Inverte os elementos
        Stack<Valor> pilhaInvertida = new Stack<>();
        while (!novaStack.isEmpty()) {
            pilhaInvertida.push(novaStack.pop());
        }

        // Retorna nova pilha mantendo o tipo original
        return new ValorPilha(pilhaInvertida, (TipoPilha) pilhaOriginal.getTipo(null));
    }

    /**
     * Checagem de tipo: garante que o operando é uma pilha
     */
    @Override
    protected boolean checaTipoElementoTerminal(AmbienteCompilacao ambiente)
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

        Tipo tipo = getExp().getTipo(ambiente);
        if (!(tipo instanceof TipoPilha)) {
            System.err.println("Erro de Tipo: 'inverte' esperado em uma pilha. Encontrado: " + tipo.getNome());
            return false;
        }
        return true;
    }

    /**
     * Tipo retornado: mesma pilha, tipo do elemento mantido
     */
    @Override
    public Tipo getTipo(AmbienteCompilacao ambiente)
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

        return getExp().getTipo(ambiente);
    }

    /**
     * Clone adequado da expressão
     */
    @Override
    public ExpInverte clone() {
        return new ExpInverte(getExp().clone());
    }
}
