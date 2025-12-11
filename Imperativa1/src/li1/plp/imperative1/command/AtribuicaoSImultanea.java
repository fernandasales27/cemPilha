package li1.plp.imperative1.command;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import li1.plp.expressions2.expression.Expressao;
import li1.plp.expressions2.expression.Id;
import li1.plp.expressions2.memory.VariavelJaDeclaradaException;
import li1.plp.expressions2.memory.VariavelNaoDeclaradaException;
import li1.plp.expressions2.expression.Valor;
import li1.plp.imperative1.memory.AmbienteCompilacaoImperativa;
import li1.plp.imperative1.memory.AmbienteExecucaoImperativa;

public class AtribuicaoSimultanea implements Comando {

    private List<Id> ids;
    private List<Expressao> exps;

    public AtribuicaoSimultanea(List<Id> ids, List<Expressao> exps) {
        this.ids = ids;
        this.exps = exps;
    }

    @Override
    public AmbienteExecucaoImperativa executar(AmbienteExecucaoImperativa ambiente)
            throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException {

        // 1. verificar tamanhos iguais
        if (ids.size() != exps.size()) {
            throw new Error("Tamanhos iguais");
        }

        // 2. verificar duplicatas
        Set<String> nomes = new HashSet<>();
        for (Id id : ids) {
            if (nomes.contains(id.toString())) {
                throw new Error("tem duplicadas");
            }
            nomes.add(id.toString());
        }

        // 3. Avaliar todas as expressões ANTES de alterar o ambiente
        List<Valor> valores = new ArrayList<>();
        for (Expressao e : exps) {
            valores.add(e.avaliar(ambiente));
        }

        // 4. Fazer todas as atualizações simultaneamente
        for (int i = 0; i < ids.size(); i++) {
            ambiente.changeValor(ids.get(i), valores.get(i));
        }

        return ambiente;
    }

    @Override
    public boolean checaTipo(AmbienteCompilacaoImperativa ambiente)
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

        if (ids.size() != exps.size()) {
            return false;
        }

        // verificação de duplicatas
        Set<String> nomes = new HashSet<>();
        for (Id id : ids) {
            if (nomes.contains(id.toString())) {
                return false;
            }
            nomes.add(id.toString());
        }

        // checar cada expressão e compatibilidade
        for (int i = 0; i < ids.size(); i++) {
            Id id = ids.get(i);
            Expressao e = exps.get(i);

            if (!e.checaTipo(ambiente)) return false;

            if (!id.getTipo(ambiente).eIgual(e.getTipo(ambiente)))
                return false;
        }

        return true;
    }
}
