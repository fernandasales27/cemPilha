package lf1.plp.expressions2.expression;

import java.util.Stack;

import lf1.plp.expressions1.util.Tipo;
import lf1.plp.functional1.util.TipoPilha;
import lf1.plp.expressions2.memory.AmbienteCompilacao; 
import lf1.plp.expressions2.memory.AmbienteExecucao;
import lf1.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf1.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf1.plp.functional1.util.PilhaVaziaException;

public class ValorPilha extends ValorConcreto<Stack<Valor>> {

    private TipoPilha tipoPilha;

    /**
     * Construtor para uma pilha vazia (usado por ExpEmptyStack).
     * O tipo será polimórfico (stack(?)) por padrão.
     */
    public ValorPilha() {
        super(new Stack<Valor>());
        this.tipoPilha = new TipoPilha(); // Cria com TipoPolimorfico por padrão
    }
    
    /**
     * Construtor para uma pilha com tipo já conhecido.
     * Usado internamente por push/pop.
     */
    public ValorPilha(Stack<Valor> valor, TipoPilha tipo) {
        super(valor);
        this.tipoPilha = tipo;
    }

    // FIX 3: Signature changed to AmbienteCompilacao
    // O 'amb' é ignorado, pois um valor conhece seu próprio tipo.
    public Tipo getTipo(AmbienteCompilacao amb) {
        return tipoPilha;
    }
    
    // FIX 4: Removed runtime type-checking.
    // O tipo da pilha NUNCA muda em tempo de execução.
    // A checagem de tipo já foi feita em ExpPush.checaTipo.
    public ValorPilha push(Valor elemento) { 
        Stack<Valor> novaStack = (Stack<Valor>) this.valor().clone();
        novaStack.push(elemento);
        
        // Retorna uma NOVA pilha com o TIPO ANTIGO.
        return new ValorPilha(novaStack, this.tipoPilha);
    }

    public ValorPilha pop() throws PilhaVaziaException {
        if (this.valor().isEmpty()) {
            throw new PilhaVaziaException();
        }
        Stack<Valor> novaStack = (Stack<Valor>) this.valor().clone();
        novaStack.pop();
        return new ValorPilha(novaStack, this.tipoPilha);
    }

    public Valor top() throws IllegalStateException {
        if (this.valor().isEmpty()) {
            throw new PilhaVaziaException();
        }
        return this.valor().peek();
    }

    public ValorBooleano isEmpty() {
        return new ValorBooleano(this.valor().isEmpty());
    }

    @Override
    public String toString() {
        return this.valor().isEmpty() ? "PilhaVazia" : "Pilha: " + this.valor();
        // this.valor().toString();
    }

    // FIX 5: Added clone() method
    @Override
    public ValorPilha clone() {
        Stack<Valor> novaStack = (Stack<Valor>) this.valor().clone();
        return new ValorPilha(novaStack, this.tipoPilha);
    }
}