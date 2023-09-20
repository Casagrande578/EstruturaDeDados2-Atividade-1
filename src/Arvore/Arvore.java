package Arvore;

import Node.*;

import java.io.IOException;
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

    public Node getRoot() {
        return root;
    }
    public boolean hasRoot(){
        if(this.root != null){
            return true;
        }
        return false;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public static boolean isValidExpression(String expression){
        String regex = "^[0-9*\\/+\\-\\(\\)]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);

        return matcher.matches();
    }

    public static Arvore createTree( Arvore tree, String expression ){
        if(expression == null || expression == ""){
            throw new IllegalArgumentException("Variavel expressão não iniciada");
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

    private static boolean isNumber(char number){
        try{
            Float.parseFloat(String.valueOf(number));
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
