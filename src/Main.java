import java.util.Scanner;

import static Arvore.Arvore.*;
import Arvore.*;

public class Main {
    public static void main(String[] args) {
        int choice;
        String expressao = "";
        Arvore tree = new Arvore();

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("\n\n\nÁrvore de operações aritiméticas: ");
            System.out.println("1. Inserir expressão aritimética");
            System.out.println("2. Criar árvore binária de expresão aritimética");
            System.out.println("3. Exibir árvore binária de expressão aritimética");
            System.out.println("4. Calcular expressão");
            System.out.println("5. Sair");
            System.out.print("Insira sua opção: ");

            try{
                choice = Integer.parseInt(scanner.nextLine());
            }catch(NumberFormatException e){
                System.out.println("Por favor Inserir um valor numérico valido");
                choice = -1;
            }

            switch (choice){
                case 1:
                    System.out.println("Insira expressão aritimética: ");
                    expressao =  scanner.nextLine();
                    if(!isValidExpression(expressao)){
                        System.out.println("Expressão inválida tente novamente");
                        expressao ="";
                        break;
                    };
                    break;
                case 2:
                    System.out.println("Criando árvore binária de expressão aritimética...");
                    try{
                        tree = createTree(tree,expressao);
                    }catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    break;
                case 3:
                    //Mostra arvore
                    break;
                case 4:
                    System.out.println("Valor da expressão é: " + tree.getRoot().visitar());
                    break;
                case 5:
                    System.exit(0);
                    break;
                case -1:
                    break;
                default:
                    System.out.println("Por favor inserir uma opção válida entre as apresentadas");
            }
        }
    }
}