package lf1.plp.expressions2.expression; 

import java.util.Stack;
import lf1.plp.expressions1.util.Tipo;
import lf1.plp.expressions1.util.TipoPilha;
import lf1.plp.expressions2.memory.AmbienteCompilacao; 
import lf1.plp.expressions2.memory.AmbienteExecucao;
import lf1.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf1.plp.expressions2.memory.VariavelNaoDeclaradaException;

public class ValorPilha extends ValorConcreto<Stack<Valor>> {

    private TipoPilha tipoPilha;

    public ValorPilha() {
        super(new Stack<Valor>());
        this.tipoPilha = new TipoPilha(); 
    }
    
    /**
     * Construtor para uma pilha com tipo já conhecido.
     * Usado internamente por push/pop.
     */
    private ValorPilha(Stack<Valor> valor, TipoPilha tipo) {
        super(valor);
        this.tipoPilha = tipo;
    }


    public Tipo getTipo(AmbienteCompilacao amb) {
        return tipoPilha;
    }
    
    // O tipo da pilha NUNCA muda em tempo de execução.
    // A checagem de tipo já foi feita em ExpPush.checaTipo.
    public ValorPilha push(Valor elemento) { 
        Stack<Valor> novaStack = (Stack<Valor>) this.valor().clone();
        novaStack.push(elemento);
        
        // Retorna uma NOVA pilha com o TIPO ANTIGO.
        return new ValorPilha(novaStack, this.tipoPilha);
    }

    public ValorPilha pop() throws IllegalStateException {
        if (this.valor().isEmpty()) {
            throw new IllegalStateException("Runtime Error: pop em pilha vazia.");
        }
        Stack<Valor> novaStack = (Stack<Valor>) this.valor().clone();
        novaStack.pop();
        return new ValorPilha(novaStack, this.tipoPilha);
    }

    public Valor top() throws IllegalStateException {
        if (this.valor().isEmpty()) {
            throw new IllegalStateException("Runtime Error: top em pilha vazia.");
        }
        return this.valor().peek();
    }

    public ValorBooleano isEmpty() {
        return new ValorBooleano(this.valor().isEmpty());
    }

    @Override
    public String toString() {
        return this.valor().toString();
    }


    @Override
    public ValorPilha clone() {
        Stack<Valor> novaStack = (Stack<Valor>) this.valor().clone();
        return new ValorPilha(novaStack, this.tipoPilha);
    }
}