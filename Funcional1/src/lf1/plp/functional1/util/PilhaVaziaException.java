package lf1.plp.functional1.util;

public class PilhaVaziaException extends RuntimeException{
    
    public PilhaVaziaException(){
        super("Não se pode realizar a operação com a Pilha Vazia!");
    }
    
}
