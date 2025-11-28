package lf1.plp.functional1.expression;

import java.util.Stack;

import lf1.plp.expressions1.util.Tipo;
import lf1.plp.expressions1.util.TipoPrimitivo;
import lf1.plp.functional1.util.TipoPilha;
import lf1.plp.expressions2.expression.ExpUnaria;
import lf1.plp.expressions2.expression.Expressao;
import lf1.plp.expressions2.expression.Valor;
import lf1.plp.expressions2.expression.ValorInteiro;
import lf1.plp.expressions2.expression.ValorPilha;
import lf1.plp.expressions2.memory.AmbienteCompilacao;
import lf1.plp.expressions2.memory.AmbienteExecucao;
import lf1.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf1.plp.expressions2.memory.VariavelNaoDeclaradaException;

/**
 * ExpSumAll - Soma todos os elementos da pilha e retorna um único valor inteiro.
 */
public class ExpSumAll extends ExpUnaria {

    public ExpSumAll(Expressao expPilha) {
        super(expPilha, "sumAll");
    }

    @Override
    public Valor avaliar(AmbienteExecucao ambiente)
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

        Valor valorAvaliado = getExp().avaliar(ambiente);

        if (!(valorAvaliado instanceof ValorPilha)) {
            throw new RuntimeException("sumAll só pode ser aplicado sobre uma pilha.");
        }

        ValorPilha pilha = (ValorPilha) valorAvaliado;
        Stack<Valor> elementos = pilha.valor(); 

        int soma = 0;
        for (Valor v : elementos) {
            if (!(v instanceof ValorInteiro)) {
                throw new RuntimeException("sumAll só pode somar elementos inteiros.");
            }
            soma += ((ValorInteiro) v).valor();
        }

        return new ValorInteiro(soma);
    }

    @Override
    protected boolean checaTipoElementoTerminal(AmbienteCompilacao ambiente)
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

        Tipo tipo = getExp().getTipo(ambiente);

        if (!(tipo instanceof TipoPilha)) {
            System.err.println("Erro de tipo: 'sumAll' esperado sobre uma pilha. Encontrado: "
                            + tipo.getNome());
            return false;
        }

        TipoPilha tipoPilha = (TipoPilha) tipo;
        if (!tipoPilha.getSubTipo().equals(TipoPrimitivo.INTEIRO)) {
            System.err.println("Erro de tipo: 'sumAll' funciona apenas com pilha de inteiros. Encontrado: "
                            + tipoPilha.getSubTipo().getNome());
            return false;
        }

        return true;
    }


    @Override
    public Tipo getTipo(AmbienteCompilacao ambiente)
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        return TipoPrimitivo.INTEIRO;
    }

    @Override
    public ExpSumAll clone() {
        return new ExpSumAll(getExp().clone());
    }
}
