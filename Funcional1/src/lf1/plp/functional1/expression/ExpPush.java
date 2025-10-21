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

public class ExpPush implements Expressao {

    private Expressao expPilha;
    private Expressao expElemento;

    public ExpPush(Expressao expPilha, Expressao expElemento) {
        this.expPilha = expPilha;
        this.expElemento = expElemento;
    }

    @Override
    public Valor avaliar(AmbienteExecucao ambiente) 
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        
        ValorPilha pilha = (ValorPilha) expPilha.avaliar(ambiente);
        Valor elemento = expElemento.avaliar(ambiente);
        
        // CORREÇÃO: O método push em ValorPilha agora só aceita 1 argumento.
        return pilha.push(elemento);
    }

    @Override
    public boolean checaTipo(AmbienteCompilacao ambiente) 
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        
        boolean tipoOk = expPilha.checaTipo(ambiente) && expElemento.checaTipo(ambiente);
        if (!tipoOk) return false;

        Tipo tipoPilha = expPilha.getTipo(ambiente);
        Tipo tipoElemento = expElemento.getTipo(ambiente);

        if (!(tipoPilha instanceof TipoPilha)) {
            System.err.println("Erro de Tipo: 'push' esperado em um stack. Encontrado: " + tipoPilha.getNome());
            return false;
        }

        TipoPilha tipoPilhaConcreto = (TipoPilha) tipoPilha;
        tipoOk = tipoPilhaConcreto.getTipoElemento().eIgual(tipoElemento);
        
        if (!tipoOk) {
             System.err.println("Erro de Tipo: 'push' em stack(" + tipoPilhaConcreto.getTipoElemento().getNome() 
                + ") com elemento " + tipoElemento.getNome());
        }
        return tipoOk;
    }

    @Override
    public Tipo getTipo(AmbienteCompilacao ambiente) 
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        // A inferência de tipo já ocorreu no checaTipo
        return expPilha.getTipo(ambiente);
    }

    @Override
    public Expressao reduzir(AmbienteExecucao ambiente) {
        this.expPilha = this.expPilha.reduzir(ambiente);
        this.expElemento = this.expElemento.reduzir(ambiente);
        return this;
    }
    
    @Override
    public ExpPush clone() {
        return new ExpPush(expPilha.clone(), expElemento.clone());
    }
}