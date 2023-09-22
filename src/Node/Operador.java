// - Header -
// Atividade 1 de Estrutura de Dados 2 -
// Grupo:
// Enzo Galeazzo Casagrande - 32209606
// Rafael Marques Cruz Russo - 32220294
// Marcos Carvalho Júnior - 32234120
// - End Header-
package Node;
//Classe operador com o objetivo de conter as informações e métodos de um operador aritimético
/**
 * @author Enzo Galeazzo Casagrande - 32209606
 * @author Rafael Marques Cruz Russo - 32220294
 * @author Marcos Carvalho Junior - 32234120
 */
public class Operador extends Node{
    private char operador;

    public Operador(char operador){
        super(0.0f);
        this.operador = operador;
    }

    public char getOperador(){
        return this.operador;
    };

    public void setOperador(char operador){
        this.operador = operador;
    }

    /**
     * Método responsável por definir e executar a operação de acordo com o operador armazenado no nó
     * @throws ArithmeticException caso o operador não esteja entre os definidos [*,-,+,/]
     * @return Valor numérico resultado da operação entre os nós filhos do mesmo
     */
    private float operacao(){
        switch (this.operador){
            case '+':
                return super.getLeft().visitar() + super.getRight().visitar();
            case '-':
                return super.getLeft().visitar() - super.getRight().visitar();
            case '*':
                return super.getLeft().visitar() * super.getRight().visitar();
            case '/':
                return super.getLeft().visitar() / super.getRight().visitar();
            default:
                throw new ArithmeticException("Operação Inválida");
        }
    }

    /**
     * Sobrescrita do método que traz o valor do nó - no caso do nó operador busca o resultado da operação
     * @return Valor numérico resultado da operação do nó | Em caso de operação não concluída retorna o valor "999999"
     */
    @Override
    public float visitar(){
        try{
            return operacao();
        }catch (ArithmeticException e){
            System.out.println(e.getMessage());
            return 999999;
        }
    }
    @Override
    public String toString(){
        return "Data: " + this.operador;
    }
}
