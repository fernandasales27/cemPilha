package lf1.plp.functional1.expression;

import lf1.plp.expressions1.util.Tipo;
/* Precisamos importar os novos tipos */
import lf1.plp.expressions1.util.TipoPilha;
import lf1.plp.expressions2.expression.Valor;
/* Precisamos importar o novo valor */
import lf1.plp.expressions2.expression.ValorPilha;
import lf1.plp.expressions2.expression.Expressao;
import lf1.plp.expressions2.memory.AmbienteCompilacao;
import lf1.plp.expressions2.memory.AmbienteExecucao;
import lf1.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf1.plp.expressions2.memory.VariavelNaoDeclaradaException;

public class ExpEmptyStack implements Expressao {

    public Valor avaliar(AmbienteExecucao ambiente) 
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        // Retorna uma nova instância de ValorPilha vazia
        return new ValorPilha();
    }

    public boolean checaTipo(AmbienteCompilacao ambiente) 
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        // Uma pilha vazia é sempre bem tipada
        return true;
    }

    public Tipo getTipo(AmbienteCompilacao ambiente) 
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        // Retorna uma pilha de tipo polimórfico (pilha de '?')
        return new TipoPilha();
    }

    public Expressao reduzir(AmbienteExecucao ambiente) {
        return this;
    }
    
    public ExpEmptyStack clone() {
		return this;
	}
}