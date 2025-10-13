package lf1.plp.expressions2.expression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lf1.plp.expressions1.util.Tipo;
import lf1.plp.expressions2.expression.Expressao;
import lf1.plp.expressions2.expression.ValorConcreto;
import lf1.plp.expressions2.memory.AmbienteCompilacao;
import lf1.plp.expressions2.memory.AmbienteExecucao;
import lf1.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf1.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf1.plp.functional1.util.PilhaVaziaException;
import lf1.plp.functional1.util.TipoPilha;

/**
 * Representa um valor do tipo Pilha (Stack).
 * A estrutura segue o padrão LIFO (Last-In, First-Out).
 */
public class ValorPilha extends ValorConcreto<List<Expressao>> {

    /**
     * O elemento no topo da pilha.
     */
    private Expressao topo;

    /**
     * O restante da pilha após a remoção do topo.
     */
    private ValorPilha resto;

    /**
     * Construtor privado para forçar o uso do Factory Method 'getInstancia'.
     * @param valor A representação interna da pilha como uma lista.
     */
    private ValorPilha(List<Expressao> valor) {
        super(valor);
    }

    /**
     * Factory Method para criar instâncias de ValorPilha.
     *
     * @param topo  O elemento a ser colocado no topo da pilha. Se null, a pilha é vazia.
     * @param resto O restante da pilha.
     * @return Uma nova instância de ValorPilha.
     */
    public static ValorPilha getInstancia(Expressao topo, ValorPilha resto) {
        List<Expressao> listaInterna = new ArrayList<>();
        ValorPilha instancia;

        if (topo == null) {
            // Cria uma pilha vazia
            instancia = new ValorPilha(listaInterna);
        } else {
            // Adiciona o topo
            listaInterna.add(topo);
            // Adiciona os elementos do resto da pilha
            if (resto != null && !resto.isEmpty()) {
                listaInterna.addAll(resto.valor());
            }
            instancia = new ValorPilha(listaInterna);
            instancia.topo = topo;
            instancia.resto = resto;
        }
        return instancia;
    }

    /**
     * Empilha um novo elemento no topo da pilha (operação push).
     * Esta operação é imutável, retornando uma nova instância da pilha.
     *
     * @param novoTopo O elemento a ser adicionado.
     * @return Uma nova pilha com o elemento adicionado no topo.
     */
    public ValorPilha empilha(Expressao novoTopo) {
        // A instância atual (this) se torna o 'resto' da nova pilha.
        return ValorPilha.getInstancia(novoTopo, this);
    }

    /**
     * Remove o elemento do topo da pilha (operação pop).
     * Retorna o restante da pilha após a remoção.
     *
     * @return O restante da pilha.
     * @throws PilhaVaziaException se a pilha estiver vazia.
     */
    public ValorPilha desempilha() throws PilhaVaziaException {
        if (isEmpty()) {
            throw new PilhaVaziaException();
        }
        return this.resto;
    }
    
    /**
     * Retorna o elemento do topo da pilha sem removê-lo (operação peek).
     *
     * @return O elemento do topo.
     * @throws PilhaVaziaException se a pilha estiver vazia.
     */
    public Expressao getTopo() throws PilhaVaziaException {
        if (isEmpty()) {
            throw new PilhaVaziaException();
        }
        return this.topo;
    }

    /**
     * Retorna o restante da pilha (todos os elementos exceto o topo).
     * @return O restante da pilha.
     */
    public ValorPilha getResto() {
        return this.resto;
    }

    /**
     * Verifica se a pilha está vazia.
     *
     * @return true se a pilha estiver vazia, false caso contrário.
     */
    public boolean isEmpty() {
        return this.topo == null;
    }

    @Override
    public boolean checaTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        // Se a pilha é vazia, a checagem de tipo passa.
        if (isEmpty()) {
            return true;
        }

        // Se a pilha tem apenas um elemento, a checagem passa.
        if (getResto() == null || getResto().isEmpty()) {
            return getTopo().checaTipo(amb);
        }

        // Obtém o tipo do topo e o tipo do sub-pilha (resto)
        Tipo tipoTopo = getTopo().getTipo(amb);
        TipoPilha tipoResto = (TipoPilha) getResto().getTipo(amb);

        // Verifica se o tipo do topo é igual ao tipo dos elementos do resto
        if (!tipoTopo.eIgual(tipoResto.getSubTipo())) {
            return false;
        }

        // Continua a checagem recursivamente para o resto da pilha
        return getResto().checaTipo(amb);
    }

    @Override
    public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        if (this.isEmpty()) {
             // Retorna um tipo Pilha genérico se estiver vazia
            return new TipoPilha();
        }
        // O tipo da pilha é definido pelo tipo do seu elemento do topo
        return new TipoPilha(this.getTopo().getTipo(amb));
    }
    
    @Override
    public ValorPilha clone() {
        return ValorPilha.getInstancia(this.topo, this.resto);
    }
    
    @Override
    public String toString() {
        // A representação interna já está na ordem correta (topo é o primeiro elemento)
        return valor().toString();
    }
}