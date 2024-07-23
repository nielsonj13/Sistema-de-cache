package Projeto;

import java.util.LinkedList;

public class TabelaHash<K, V> {
    private ListaEncadeada<K, V>[] tabela;
    private int tamanho;

    public TabelaHash(int capacidade) {
        tabela = new ListaEncadeada[capacidade];
        tamanho = 0;
        for (int i = 0; i < capacidade; i++) {
            tabela[i] = new ListaEncadeada<>();
        }
    }

    public void inserir(K chave, V valor) {
        int indice = hash(chave);
        tabela[indice].inserir(chave, valor);
        tamanho++;
    }

    public V buscar(K chave) {
        int indice = hash(chave);
        return tabela[indice].buscar(chave);
    }

    public void remover(K chave) {
        int indice = hash(chave);
        tabela[indice].remover(chave);
        tamanho--;
    }

    public void imprimirTabela() {
        for (int i = 0; i < tabela.length; i++) {
            System.out.print("Índice " + i + ": ");
            tabela[i].imprimirLista();
        }
    }

    private int hash(K chave) {
        return Math.abs(chave.hashCode() % tabela.length);
    }
}

class ListaEncadeada<K, V> {
    private class No {
        K chave;
        V valor;
        No proximo;

        No(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
            this.proximo = null;
        }
    }

    private No cabeca;

    public ListaEncadeada() {
        cabeca = null;
    }

    public void inserir(K chave, V valor) {
        No novoNo = new No(chave, valor);
        novoNo.proximo = cabeca;
        cabeca = novoNo;
    }

    public V buscar(K chave) {
        No atual = cabeca;
        while (atual != null) {
            if (atual.chave.equals(chave)) {
                return atual.valor;
            }
            atual = atual.proximo;
        }
        return null;
    }

    public void remover(K chave) {
        No atual = cabeca;
        No anterior = null;
        while (atual != null && !atual.chave.equals(chave)) {
            anterior = atual;
            atual = atual.proximo;
        }
        if (atual != null) {
            if (anterior == null) {
                cabeca = atual.proximo;
            } else {
                anterior.proximo = atual.proximo;
            }
        }
    }

    public void imprimirLista() {
        No atual = cabeca;
        while (atual != null) {
            System.out.print("[" + atual.chave + ": " + atual.valor + "] ");
            atual = atual.proximo;
        }
        System.out.println();
    }
}
