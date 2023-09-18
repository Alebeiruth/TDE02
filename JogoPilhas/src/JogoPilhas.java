import java.util.Random;
import java.util.Scanner;

public class JogoPilhas {
    // Método que move os elementos de pilhaOrigem para pilhaDestino
// Método que move os elementos de pilhaOrigem para pilhaDestino
// Método que move os elementos de pilhaOrigem para pilhaDestino
    private static void solucaoAutomatica(Pilha pilhaOrigem, Pilha pilhaDestino, boolean crescente) {
        Pilha pilhaAuxiliar = new Pilha(pilhaOrigem.getTamanhoMaximo());

        // Move todos os elementos de pilhaOrigem para pilhaAuxiliar
        while (!pilhaOrigem.isEmpty()) {
            int elemento = pilhaOrigem.pop();
            pilhaAuxiliar.push(elemento);
        }

        // Move os elementos de pilhaAuxiliar de volta para pilhaDestino, mantendo a ordem
        while (!pilhaAuxiliar.isEmpty()) {
            int elemento = pilhaAuxiliar.pop();
            boolean movido = false;

            while (!pilhaDestino.isEmpty() &&
                    ((crescente && pilhaDestino.peek() > elemento) ||
                            (!crescente && pilhaDestino.peek() < elemento))) {
                int temp = pilhaDestino.pop();
                pilhaOrigem.push(temp);
                movido = true;
            }

            if (!movido) {
                pilhaDestino.push(elemento);
            } else {
                pilhaDestino.push(elemento);
                while (!pilhaOrigem.isEmpty()) {
                    int temp = pilhaOrigem.pop();
                    pilhaDestino.push(temp);
                }
            }
        }

        // Calcula a quantidade mínima de movimentos
        int tamanhoPilhaOrigem = pilhaOrigem.getTamanhoMaximo();
        int movimentosMinimos = (int) Math.pow(2, tamanhoPilhaOrigem) - 1;

        System.out.println("Quantidade mínima de movimentos: " + movimentosMinimos);
    }


    // Método que retorna a pilha correspondente ao número passado como argumento
    private static Pilha getPilha(int numeroPilha, Pilha pilha1, Pilha pilha2, Pilha pilha3) {
        switch (numeroPilha) {
            case 1:
                return pilha1;
            case 2:
                return pilha2;
            case 3:
                return pilha3;
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Pede ao usuário para digitar o tamanho das pilhas
        System.out.println("Digite o tamanho das pilhas: ");
        int tamanhoPilhas = scanner.nextInt();
        scanner.nextLine();

        // Cria três pilhas com o tamanho informado
        Pilha pilha1 = new Pilha(tamanhoPilhas);
        Pilha pilha2 = new Pilha(tamanhoPilhas);
        Pilha pilha3 = new Pilha(tamanhoPilhas);

        // Preenche pilha1 com números inteiros aleatórios entre 1 e 100
        for (int i = 0; i < tamanhoPilhas; i++) {
            pilha1.push(random.nextInt(100) + 1);
        }

        // Imprime as pilhas iniciais
        System.out.println("Pilha 1 inicial: ");
        pilha1.imprimirPilha();
        pilha2.imprimirPilha();
        pilha3.imprimirPilha();

        int jogadas = 0; // Contador de jogadas
        boolean crescente = true; // Flag para ordenação (crescente ou decrescente)

        // Pede ao usuário para escolher a ordenação
        System.out.println("Escolha a ordenação:");
        System.out.println("1 - Crescente");
        System.out.println("2 - Decrescente");
        int escolha = scanner.nextInt();
        scanner.nextLine();
        if (escolha == 2) {
            crescente = false;
        }

        // Loop principal do jogo
        while (true) {
            System.out.println("\nOpções:");
            System.out.println("0 - Sair");
            System.out.println("1 - Movimentar");
            System.out.println("2 - Solução automática");
            System.out.println("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) {
                break; // Sai do loop se a opção for 0
            } else if (opcao == 1) {
                System.out.println("Digite a pilha de origem (1, 2 ou 3): ");
                int origem = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Digite a pilha de destino (1, 2 ou 3): ");
                int destino = scanner.nextInt();
                scanner.nextLine();

                // Obtém as pilhas de origem e destino
                Pilha pilhaOrigem = getPilha(origem, pilha1, pilha2, pilha3);
                Pilha pilhaDestino = getPilha(destino, pilha1, pilha2, pilha3);

                if (pilhaOrigem != null && pilhaDestino != null) {
                    // Verifica se a movimentação é válida
                    if (!pilhaOrigem.isEmpty() && (pilhaDestino.isEmpty() ||
                            (crescente && pilhaOrigem.peek() >= pilhaDestino.peek()) ||
                            (!crescente && pilhaOrigem.peek() <= pilhaDestino.peek()))) {
                        int elemento = pilhaOrigem.pop(); // Move o elemento
                        pilhaDestino.push(elemento);
                        jogadas++; // Incrementa o contador de jogadas

                        System.out.println("Movimento realizado com sucesso.");
                        System.out.println("Pilhas após a movimentação:");
                        pilha1.imprimirPilha();
                        pilha2.imprimirPilha();
                        pilha3.imprimirPilha();

                        // Verifica se a ordenação foi concluída
                        if ((crescente && pilha1.isFullAndSorted(true) ||
                                pilha2.isFullAndSorted(true) ||
                                pilha3.isFullAndSorted(true)) ||
                                (!crescente && pilha1.isFullAndSorted(false) ||
                                        pilha2.isFullAndSorted(false) ||
                                        pilha3.isFullAndSorted(false))) {
                            System.out.println("Ordenação concluída em " + jogadas + " jogadas.");
                            System.out.println("Pressione Enter para sair.");
                            scanner.nextLine();
                            break; // Sai do loop se a ordenação foi concluída
                        }
                    } else {
                        System.out.println("Movimento inválido. O elemento só pode ser colocado sobre um número maior ou em uma pilha vazia.");
                    }
                } else {
                    System.out.println("Pilha de origem ou pilha de destino inválida.");
                }
            } else if (opcao == 2) {
                Pilha pilhaOrigem = null, pilhaDestino = null;

                if (crescente) {
                    // Escolhe a pilha de origem e destino automaticamente (ordenação crescente)
                    if (!pilha1.isEmpty() && (pilha2.isEmpty() || pilha1.peek() <= pilha2.peek())) {
                        pilhaOrigem = pilha1;
                        pilhaDestino = pilha2;
                    } else if (!pilha1.isEmpty() && (pilha3.isEmpty() || pilha1.peek() <= pilha3.peek())) {
                        pilhaOrigem = pilha1;
                        pilhaDestino = pilha3;
                    } else if (!pilha2.isEmpty() && (pilha3.isEmpty() || pilha2.peek() <= pilha3.peek())) {
                        pilhaOrigem = pilha2;
                        pilhaDestino = pilha3;
                    }
                } else {
                    // Escolhe a pilha de origem e destino automaticamente (ordenação decrescente)
                    if (!pilha1.isEmpty() && (pilha2.isEmpty() || pilha1.peek() >= pilha2.peek())) {
                        pilhaOrigem = pilha1;
                        pilhaDestino = pilha2;
                    } else if (!pilha1.isEmpty() && (pilha3.isEmpty() || pilha1.peek() >= pilha3.peek())) {
                        pilhaOrigem = pilha1;
                        pilhaDestino = pilha3;
                    } else if (!pilha2.isEmpty() && (pilha3.isEmpty() || pilha2.peek() >= pilha3.peek())) {
                        pilhaOrigem = pilha2;
                        pilhaDestino = pilha3;
                    }
                }

                if (pilhaOrigem != null && pilhaDestino != null) {
                    // Tenta encontrar uma solução automática
                    solucaoAutomatica(pilhaOrigem, pilhaDestino, crescente);
                    jogadas++; // Incrementa o contador de jogadas

                    System.out.println("Solução automática realizada com sucesso.");
                    System.out.println("Pilhas após a movimentação:");
                    pilha1.imprimirPilha();
                    pilha2.imprimirPilha();
                    pilha3.imprimirPilha();

                    // Verifica se a ordenação foi concluída
                    if ((crescente && pilha1.isFullAndSorted(true) ||
                            pilha2.isFullAndSorted(true) ||
                            pilha3.isFullAndSorted(true)) ||
                            (!crescente && pilha1.isFullAndSorted(false) ||
                                    pilha2.isFullAndSorted(false) ||
                                    pilha3.isFullAndSorted(false))) {
                        System.out.println("Ordenação concluída em " + jogadas + " jogadas.");
                        System.out.println("Pressione Enter para sair.");
                        scanner.nextLine();
                        break; // Sai do loop se a ordenação foi concluída
                    }
                } else {
                    System.out.println("Não foi possível encontrar uma solução automática.");
                }
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
