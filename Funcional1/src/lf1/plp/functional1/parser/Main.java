package lf1.plp.functional1.parser;

import java.io.FileInputStream;

import lf1.plp.expressions2.expression.Valor;
import lf1.plp.functional1.Programa;
public class Main {
    public static void main(String[] args) {
        try {
            // Se quiser passar o arquivo pelo console:
            // java Main input.txt
            // se precisar mude o caminho para o input de sua máquina
            String arquivo = (args.length > 0) ? args[0] : "C:\\Users\\ricar\\Downloads\\cemPilha\\Funcional1\\input";

            FileInputStream fis = new FileInputStream(arquivo);

            Func1Parser parser = new Func1Parser(fis);

            // Faz o parsing → retorna Programa
            Programa programa = parser.PPrograma();

            // Executa o programa
            Valor resultado = programa.executar();

            // Imprime o resultado no CMD
            // System.out.println("Pilha = " + resultado);
            System.out.println(resultado);

        } catch (Exception e) {
            System.out.println("Erro ao executar:");
            e.printStackTrace();
        }
    }
}
