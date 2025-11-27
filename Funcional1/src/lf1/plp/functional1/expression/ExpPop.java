package lf1.plp.functional1.expression;

import lf1.plp.expressions1.util.Tipo;
import lf1.plp.functional1.util.TipoPilha; // Mantido
import lf1.plp.expressions2.expression.ExpUnaria; // Alterado!
import lf1.plp.expressions2.expression.Expressao;
import lf1.plp.expressions2.expression.Valor;
import lf1.plp.expressions2.expression.ValorPilha; // Mantido
import lf1.plp.expressions2.memory.AmbienteCompilacao;
import lf1.plp.expressions2.memory.AmbienteExecucao;
import lf1.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf1.plp.expressions2.memory.VariavelNaoDeclaradaException;
// Pode ser necessário importar PilhaVaziaException se o pop() de ValorPilha lançar
// import lf1.plp.functional1.util.PilhaVaziaException;

/**
 * Representa a expressão unária 'pop', que remove o elemento do topo da pilha.
 * Herda de ExpUnaria.
 */
public class ExpPop extends ExpUnaria {

    /**
     * Construtor da ExpPop.
     * @param expPilha A expressão que deve ser avaliada para uma ValorPilha.
     */
    public ExpPop(Expressao expPilha) {
        // Passa a expressão para a classe base e define "pop" como o operador
        super(expPilha, "pop"); 
    }

    /**
     * Avalia a expressão 'pop'.
     */
    @Override
    public Valor avaliar(AmbienteExecucao ambiente) 
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        
        // 1. Avalia a sub-expressão (herdada) para obter a pilha
        ValorPilha pilha = (ValorPilha) getExp().avaliar(ambiente);
        
        // 2. Retorna a NOVA pilha resultante do pop (imutabilidade)
        // Se seu pilha.pop() pode lançar PilhaVaziaException, adicione no throws
        return pilha.pop(); // ou pilha.pop() dependendo de como nomeou
    }

    /**
     * Checa o tipo do elemento terminal (a pilha).
     * A classe base ExpUnaria já vai checar o tipo da sub-expressão.
     * Este método só precisa garantir que o tipo é o esperado (Pilha).
     */
    @Override
    protected boolean checaTipoElementoTerminal(AmbienteCompilacao amb)
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        
        Tipo tipoPilha = getExp().getTipo(amb);

        if (tipoPilha.eIgual(new TipoPilha())) {
            return true;
        }

        // Erro se não for uma pilha
        System.err.println("Erro de Tipo: 'pop' esperado em uma pilha. Encontrado: " + tipoPilha.getNome());
        return false;
    }

    /**
     * Retorna o tipo da expressão 'pop', que é uma pilha do mesmo tipo.
     */
    @Override
    public Tipo getTipo(AmbienteCompilacao amb) 
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        
        // O resultado de um 'pop' é uma pilha do mesmo tipo da original.
        return getExp().getTipo(amb);
    }

    /**
     * Clona a expressão.
     * A classe base ExpUnaria provavelmente já faz isso, 
     * mas se precisar sobrescrever, seria assim.
     */
    @Override
    public ExpPop clone() {
        return new ExpPop(getExp().clone());
    }
}