package lf1.plp.functional1.expression;

import lf1.plp.expressions1.util.Tipo;
import lf1.plp.expressions1.util.TipoPilha;
import lf1.plp.expressions2.expression.Expressao;
import lf1.plp.expressions2.expression.Valor;
import lf1.plp.expressions2.expression.ValorPilha;
import lf1.plp.expressions2.memory.AmbienteCompilacao;
import lf1.plp.expressions2.memory.AmbienteExecucao;
import lf1.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf1.plp.expressions2.memory.VariavelNaoDeclaradaException;

public class ExpPop implements Expressao {

    private Expressao expPilha;

    public ExpPop(Expressao expPilha) {
        this.expPilha = expPilha;
    }

    public Valor avaliar(AmbienteExecucao ambiente) 
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        
        ValorPilha pilha = (ValorPilha) expPilha.avaliar(ambiente);
        
        // Retorna a NOVA pilha resultante do pop (imutabilidade)
        return pilha.pop();
    }

    public boolean checaTipo(AmbienteCompilacao ambiente) 
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        
        boolean tipoOk = expPilha.checaTipo(ambiente);
        if (!tipoOk) return false;

        Tipo tipoPilha = expPilha.getTipo(ambiente);

        if (!(tipoPilha instanceof TipoPilha)) {
            System.err.println("Erro de Tipo: 'pop' esperado em um stack. Encontrado: " + tipoPilha.getNome());
            return false;
        }
        return true;
    }

    public Tipo getTipo(AmbienteCompilacao ambiente) 
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        // Pop retorna uma pilha do mesmo tipo
        return expPilha.getTipo(ambiente);
    }

    public Expressao reduzir(AmbienteExecucao ambiente) {
        this.expPilha = this.expPilha.reduzir(ambiente);
        return this;
    }
    
    public ExpPop clone() {
		return new ExpPop(expPilha.clone());
	}
}