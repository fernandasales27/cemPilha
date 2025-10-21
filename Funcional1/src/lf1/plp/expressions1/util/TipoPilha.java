package lf1.plp.expressions1.util;

import lf1.plp.functional1.util.TipoPolimorfico;

public class TipoPilha implements Tipo {

    // O tipo dos elementos que esta pilha armazena.
    private Tipo tipoElemento;

    /**
     * Construtor de um tipo pilha.
     * @param tipoElemento O tipo dos elementos da pilha.
     */
    public TipoPilha(Tipo tipoElemento) {
        this.tipoElemento = tipoElemento;
    }

    /**
     * Construtor de uma pilha de tipo ainda não definido (polimórfico).
     * Usado para o 'emptyStack'.
     */
    public TipoPilha() {
        this.tipoElemento = new TipoPolimorfico();
    }

    public String getNome() {
        return "stack(" + tipoElemento.getNome() + ")";
    }

    public Tipo getTipoElemento() {
        return tipoElemento;
    }

    public boolean eBooleano() {
        return false;
    }

    public boolean eInteiro() {
        return false;
    }

    public boolean eString() {
        return false;
    }

    public boolean eValido() {
        return tipoElemento.eValido();
    }

    /**
     * Uma pilha é igual a outra se o tipo de seus elementos for igual.
     * A lógica polimórfica em 'eIgual' cuidará da inferência de tipo.
     * Ex: stack(?) é igual a stack(inteiro) e fará '?' ser 'inteiro'.
     */
    public boolean eIgual(Tipo tipo) {
        if (tipo instanceof TipoPilha) {
            TipoPilha outraPilha = (TipoPilha) tipo;
            // Usa a lógica de 'eIgual' do TipoPolimorfico para unificar os tipos
            return this.tipoElemento.eIgual(outraPilha.getTipoElemento());
        }
        if (tipo instanceof TipoPolimorfico) {
            return tipo.eIgual(this);
        }
        return false;
    }

    public Tipo intersecao(Tipo outroTipo) {
        if (outroTipo.eIgual(this)) {
            return this;
        }
        return null;
    }

    @Override
    public String toString() {
        return getNome();
    }
}