package lf1.plp.functional1.util;

import lf1.plp.expressions1.util.Tipo;
import lf1.plp.functional1.util.TipoPolimorfico;

public class TipoPilha implements Tipo{
	/**
	 * Subtipo - tipo dos elementos da pilha
	 */
	private Tipo subTipo;

	/**
	 * cria uma inst�ncia de tipoLista sem subTipo
	 * 
	 */
	public TipoPilha() {
		this.subTipo = new TipoPolimorfico();
	}

	/**
	 * cria uma inst�ncia de tipoLista com o seu subtipo
	 * 
	 * @param subTipo
	 */
	public TipoPilha(Tipo subTipo) {
		this.subTipo = subTipo;
	}

	/**
	 * Retorna o tipo dos elementos de uma lista
	 * 
	 * @return tipo dos elementos da lista
	 */
	public Tipo getSubTipo() {
		return subTipo;
	}

	public boolean eBooleano() {
		return false;
	}

	public boolean eIgual(Tipo tipo) {
		if (tipo instanceof TipoPilha) {
			TipoPilha lista = (TipoPilha) tipo;
			return lista.subTipo.eIgual(this.subTipo);
		}

		return tipo.eIgual(this);
	}

	public boolean eInteiro() {
		return false;
	}

	public boolean eString() {
		return false;
	}

	public boolean eValido() {
		return subTipo.eValido();
	}

	public String getNome() {
		return "[" + subTipo.getNome() + "]";
	}

    public Tipo intersecao(Tipo outroTipo) {
		if (outroTipo instanceof TipoPilha) {
			TipoPilha outraLista = (TipoPilha) outroTipo;
			return this.subTipo.intersecao(outraLista.subTipo);
		}

		return outroTipo.intersecao(this);
	}
}
