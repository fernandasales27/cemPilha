package lf1.plp.functional1.expression;

import lf1.plp.expressions1.util.Tipo;
import lf1.plp.functional1.util.TipoPilha;
import lf1.plp.expressions2.expression.ExpUnaria;
import lf1.plp.expressions2.expression.Valor;
import lf1.plp.expressions2.expression.ValorPilha;
import lf1.plp.expressions2.expression.Expressao;
import lf1.plp.expressions2.memory.AmbienteCompilacao;
import lf1.plp.expressions2.memory.AmbienteExecucao;
import lf1.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf1.plp.expressions2.memory.VariavelJaDeclaradaException;

public class ExpTop extends ExpUnaria {

    public ExpTop(Expressao expPilha) {
        super(expPilha, "top");
    }

    @Override
    public Valor avaliar(AmbienteExecucao ambiente)
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

        ValorPilha pilha = (ValorPilha) getExp().avaliar(ambiente);
        return pilha.top(); // não modifica a pilha
    }

    @Override
    protected boolean checaTipoElementoTerminal(AmbienteCompilacao ambiente)
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

        Tipo tipoPilha = getExp().getTipo(ambiente);

        if (!(tipoPilha instanceof TipoPilha)) {
            System.err.println("Erro de Tipo: 'top' esperado em uma pilha. Encontrado: " + tipoPilha.getNome());
            return false;
        }
        return true;
    }

    @Override
    public Tipo getTipo(AmbienteCompilacao ambiente)
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

        TipoPilha tipoPilha = (TipoPilha) getExp().getTipo(ambiente);
        return tipoPilha.getSubTipo(); // Retorna o tipo do elemento da pilha
    }

    @Override
    public ExpTop clone() {
        return new ExpTop(getExp().clone());
    }
}
