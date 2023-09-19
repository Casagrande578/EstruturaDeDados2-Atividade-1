package Node;

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
                throw new ArithmeticException("Operador Inválido");
        }
    }

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
