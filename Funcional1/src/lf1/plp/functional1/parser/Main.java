package lf1.plp.functional1.parser;

import lf1.plp.expressions2.expression.Valor;
import lf1.plp.functional1.Programa;
import lf1.plp.functional1.parser.Func1Parser;
import java.io.FileInputStream;

import java.io.FileInputStream;
public class Main {
    public static void main(String[] args) {
        try {
            // Se quiser passar o arquivo pelo console:
            // java Main input.txt
            String arquivo = (args.length > 0) ? args[0] : "input";

            FileInputStream fis = new FileInputStream(arquivo);

            Func1Parser parser = new Func1Parser(fis);

            // Faz o parsing → retorna Programa
            Programa programa = parser.PPrograma();

            // Executa o programa
            Valor resultado = programa.executar();

            // Imprime o resultado no CMD
            System.out.println("Pilha = " + resultado);

        } catch (Exception e) {
            System.out.println("Erro ao executar:");
            e.printStackTrace();
        }
    }
}
