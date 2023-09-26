// - Header -
// Atividade 1 de Estrutura de Dados 2 -
// Grupo:
// Enzo Galeazzo Casagrande - 32209606
// Rafael Marques Cruz Russo - 32220294
// Marcos Carvalho Júnior - 32234120
// - End Header-
import java.util.Scanner;

import static Arvore.Arvore.*;
import Arvore.*;

public class Main {
    public static void main(String[] args) {
        int choice;
        String expressao = "";
        Arvore tree = new Arvore();

        Scanner scanner = new Scanner(System.in);
        //Loop principal onde ocorre as decisões do menu;
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

            switch (choice) {
                case 1 -> {
                    System.out.println("Insira expressão aritimética: ");
                    expressao = scanner.nextLine();
                    expressao = expressao.replaceAll("\\s","");
                    if (!isValidExpression(expressao)) {
                        System.out.println("Expressão inválida tente novamente");
                        expressao = "";
                        break;
                    }
                    ;
                }
                case 2 -> {
                    System.out.println("Criando árvore binária de expressão aritimética...");
                    try {
                        tree = new Arvore(expressao);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> {
                    //Mostra arvore
                    System.out.println("Selecione opção de exibição da árvore: ");
                    System.out.println("1. In Order");
                    System.out.println("2. Pre Order");
                    System.out.println("3. Post Order");
                    System.out.println("4. Level Order");
                    try {
                        choice = Integer.parseInt(scanner.nextLine());
                    }catch (NumberFormatException e){
                        System.out.println("Por favor Inserir um valor numérico valido");
                    }
                    switch (choice){
                        case 1 ->{
                            System.out.println(tree.inOrderTraversal());
                        }
                        case 2->{
                            System.out.println( tree.preOrderTraversal());
                        }
                        case 3->{
                            System.out.println(tree.postOrderTraversal());
                        }
                        case 4->{
                            System.out.println(tree.levelOrderTraversal());
                        }
                        default -> System.out.println("Por favor inserir uma opção válida entre as apresentadas");
                    }
                } case 4 -> System.out.println("Valor da expressão é: " + tree.getRoot().visitar());
                case 5 -> System.exit(0);
                case -1 -> {
                }
                default -> System.out.println("Por favor inserir uma opção válida entre as apresentadas");
            }
        }
    }
}