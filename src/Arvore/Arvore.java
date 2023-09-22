// - Header -
// Atividade 1 de Estrutura de Dados 2 -
// Grupo:
// Enzo Galeazzo Casagrande - 32209606
// Rafael Marques Cruz Russo - 32220294
// Marcos Carvalho Júnior - 32234120
// - End Header-
package Arvore;

import Node.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Arvore {
    private Node root;

    public Arvore(){
        this(null);
    }
    public Arvore (Node root){
        this.root = root;
    }

    /**
     *
     * @return Retorna o nó raíz da Arvore
     */
    public Node getRoot() {
        return root;
    }

    /**
     * O objetivo deste método é verificar se à Árvore foi iniciada corretamente
     * @return Retorna true caso a Arvore tenha raíz
     */
    public boolean hasRoot(){
        if(this.root != null){
            return true;
        }
        return false;
    }

    /**
     *
     * @param root Um nó do tipo Operador ou Operando
     */
    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * O Objetivo desse método estático é verificar se a expressão inserida é valida para dar continuidade à criação da árvore
     * @param expression String de expressão aritimética
     * @return boolean
     */
    public static boolean isValidExpression(String expression){
        String regex = "^[0-9*\\/+\\-\\(\\)]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);

        return matcher.matches();
    }

    /**
     * O objetivo desse método estático é criar uma árvore a partir de uma expressão aritimética inserida - assemelha-se a um construtor;
     * @param tree Arvore que irá receber a construção;
     * @param expression Expressão aritimética;
     * @throws IllegalArgumentException em caso de expressão vazia
     * @return Arvore de expressão aritimética | Em caso de falha retorna uma árvore vazia
     */
    public static Arvore createTree( Arvore tree, String expression ){
        if(expression == null || expression == ""){
            throw new IllegalArgumentException("Variável expressão não iniciada");
        }
        Operando nodeOperando;
        Operador nodeOperador;
        int i=0;
        int index;
        String numero="";
        do {
            if((i==0 && expression.charAt(0) != '(')){
                index =0;
                while(isNumber(expression.charAt(i+index))){
                    numero = numero + expression.charAt(i+index);
                    index++;
                }
                i+=index;
                tree = new Arvore(new Operando(Float.parseFloat(numero)));
            }
            else{
             try{
                 index = 0;
                 numero="";
                 for(int j =index; j<expression.length(); j++){
                     if(isNumber(expression.charAt(i+index))){
                        numero = numero + expression.charAt(i+j);
                        index++;
                     }
                     if (index + i >= expression.length()) break;
                 }
                 nodeOperando =  new Operando(Float.parseFloat(numero));
                 if(index>0){
                     i+=index;
                 }else{
                     i++;
                 }
                 if(!tree.hasRoot()){
                     tree = new Arvore(nodeOperando);
                 }
                 else if(!tree.root.hasLeftChild()){
                     tree.root.setLeft(nodeOperando);
                 }
                 else if(!tree.root.hasRightChild()){
                     tree.root.setRight(nodeOperando);
                 }else{
                     throw new IOException("Erro ao criar Arvore");
                 }
             }catch (IOException e){
                 System.out.println(e.getMessage());
                 return new Arvore();
             }catch (NumberFormatException e){
                 if(!tree.hasRoot() || expression.charAt(i) == ')'){
                     i++;
                 }
                 else if(expression.charAt(i) == '('){
                     int aux =i+1;
                     String auxStr ="";
                     Arvore auxTree = new Arvore();
                     while(expression.charAt(aux)!= ')'){
                         auxStr = auxStr + expression.charAt(aux);
                         aux++;
                     }
                     auxTree = createTree(auxTree,auxStr);
                     if(!tree.getRoot().hasLeftChild()){
                         tree.getRoot().setLeft(auxTree.getRoot());
                     }
                     else if(!tree.getRoot().hasRightChild()){
                         tree.getRoot().setRight(auxTree.getRoot());
                     }
                  i = aux;
                 }else{
                     nodeOperador = new Operador(expression.charAt(i));
                     nodeOperador.setLeft(tree.root);
                     tree.setRoot(nodeOperador);
                     i++;
                 }

             }
            }

        }while(i<expression.length());
        return tree;
    }

    /**
     *
     * @param number Verifica se o char é valido para ser parseado para float
     * @return boolean
     */
    private static boolean isNumber(char number){
        try{
            Float.parseFloat(String.valueOf(number));
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public String inOrderTraversal(){
        return inOrderTraversalHelper(root);
    }

    private String inOrderTraversalHelper(Node node){
        try{
            StringBuilder sb = new StringBuilder();

            sb.append(inOrderTraversalHelper(node.getLeft()));
            sb.append(node.visitar() + " ");
            sb.append(inOrderTraversalHelper(node.getRight()));

            return sb.toString();
        }catch(NullPointerException e){
            return "";
        }
    }

    public String preOrderTraversal(){
        return preOrderTraversalHelper(root);
    }

    private String preOrderTraversalHelper(Node node){
        try{
            StringBuilder sb = new StringBuilder();

            sb.append(node.visitar() + " ");
            sb.append(preOrderTraversalHelper(node.getLeft()));
            sb.append(preOrderTraversalHelper(node.getRight()));

            return sb.toString();
        }catch (NullPointerException e){
            return "";
        }
    }

    public String postOrderTraversal(){
        return postOrderTraversalHelper(root);
    }

    private String postOrderTraversalHelper(Node node) {
        try{
            StringBuilder sb = new StringBuilder();
            sb.append(postOrderTraversalHelper(node.getLeft()));
            sb.append(postOrderTraversalHelper(node.getRight()));
            sb.append(node.visitar() + " ");

            return sb.toString();
        }catch (NullPointerException e){
            return "";
        }
    }

    public String levelOrderTraversal(){
        return levelOrderTraversalHelper(root);
    }

    private String levelOrderTraversalHelper(Node node){
        try{
            StringBuilder sb = new StringBuilder();

            Queue<Node> queue = new LinkedList<>();
            queue.add(node);
            while(!queue.isEmpty()){
                Node visited = queue.remove();
                sb.append(visited.visitar() + " ");
                if(visited.hasLeftChild()){
                    queue.add(visited.getLeft());
                }
                if(visited.hasRightChild()){
                    queue.add(visited.getRight());
                }
            }
            return sb.toString();
        }catch (NullPointerException e){
            return "";
        }
    }
}
