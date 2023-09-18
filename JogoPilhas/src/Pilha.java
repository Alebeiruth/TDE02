// Classe que implementa uma pilha usando uma lista encadeada
class Pilha {
    private Node topo; // Referência para o topo da pilha
    private int tamanhoMaximo; // Tamanho máximo da pilha

    // Construtor: inicializa a pilha
    public Pilha(int tamanhoMaximo) {
        this.topo = null; // Inicializa o topo como nulo (pilha vazia)
        this.tamanhoMaximo = tamanhoMaximo; // Define o tamanho máximo da pilha
    }

    // Verifica se a pilha está vazia
    public boolean isEmpty() {
        return topo == null;
    }

    // Adiciona um elemento no topo da pilha
    public void push(int valor) {
        if (!isFull()) {
            Node novoNode = new Node(valor);
            novoNode.proximo = topo;
            topo = novoNode;
        }
    }

    // Remove e retorna o elemento do topo da pilha
    public int pop() {
        if (!isEmpty()) {
            int valor = topo.valor;
            topo = topo.proximo;
            return valor;
        }
        return -1; // Retorna -1 se a pilha estiver vazia
    }

    // Retorna o valor do elemento no topo da pilha sem removê-lo
    public int peek() {
        if (!isEmpty()) {
            return topo.valor;
        }
        return -1; // Retorna -1 se a pilha estiver vazia
    }

    // Imprime os elementos da pilha
    public void imprimirPilha() {
        Node atual = topo;
        while (atual != null) {
            System.out.print(atual.valor + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }

    // Verifica se os elementos estão ordenados (crescente ou decrescente)
    public boolean isSorted(boolean crescente) {
        if (isEmpty() || topo.proximo == null) {
            return true; // Pilha vazia ou com apenas um elemento está sempre ordenada
        }

        Node atual = topo;
        while (atual.proximo != null) {
            if ((crescente && atual.valor > atual.proximo.valor) ||
                    (!crescente && atual.valor < atual.proximo.valor)) {
                return false; // Se encontrar um par desordenado, retorna falso
            }
            atual = atual.proximo;
        }

        return true; // Se percorreu toda a pilha sem encontrar desordem, está ordenada
    }

    // Verifica se a pilha está cheia
    public boolean isFull() {
        int tamanho = 0;
        Node atual = topo;
        while (atual != null) {
            tamanho++;
            atual = atual.proximo;
        }
        return tamanho == tamanhoMaximo; // Compara o tamanho atual com o tamanho máximo
    }

    // Verifica se a pilha está cheia e ordenada
    public boolean isFullAndSorted(boolean crescente) {
        return isFull() && isSorted(crescente);
    }

    // Retorna o tamanho máximo da pilha
    public int getTamanhoMaximo() {
        return tamanhoMaximo;
    }
}