// - Header -
// Atividade 1 de Estrutura de Dados 2 -
// Grupo:
// Enzo Galeazzo Casagrande - 32209606
// Rafael Marques Cruz Russo - 32220294
// Marcos Carvalho Júnior - 32234120
// - End Header-
package Node;
//Classe abstrata com o objetivo de definir os métodos e construtores genéricos das classes Operador e Operando;
public abstract class Node {
    private float data;
    protected Node parent;
    protected Node left;
    protected Node right;

    public Node(){
        this(null);
    }
    public Node(Float data){
        this.data = data;
    }

    public Float getData() {
        return data;
    }

    public Node getLeft() {
        return left;
    }

    public Node getParent() {
        return parent;
    }

    public Node getRight() {
        return right;
    }

    public void setData(Float data) {
        this.data = data;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    /**
     * Método que verifica se o nó possui nó filho a esquerda
     * @return boolean
     */
    public boolean hasLeftChild(){
        if(this.left != null){
            return true;
        }
        return false;
    }

    /**
     * Método que verifica se o nó possui nó filho a direita
     * @return boolean
     */
    public boolean hasRightChild(){
        if(this.right != null){
            return true;
        }
        return false;
    }

    /**
     * Método que retorna o valor da informação do nó
     * @return Valor numérico contendo a informação do nó
     */
    public float visitar(){
        return this.getData();
    }

    @Override
    public String toString(){
        return (
                "\nData: " + this.data
                + "\nParent: " + this.parent
                + "\nLeft: " + this.left
                + "\nRight: " +this.right
                );
    }
}
