package Projeto;

import java.util.HashMap;
import java.util.Map;

public class CacheLRU<K, V> {
    private final int capacidade;
    private final Map<K, No<K, V>> mapa;
    private final No<K, V> cabeca;
    private final No<K, V> cauda;

    public CacheLRU(int capacidade) {
        this.capacidade = capacidade;
        this.mapa = new HashMap<>();
        this.cabeca = new No<>(null, null);
        this.cauda = new No<>(null, null);
        cabeca.proximo = cauda;
        cauda.anterior = cabeca;
    }

    public V buscar(K chave) {
        No<K, V> no = mapa.get(chave);
        if (no == null) {
            return null;
        }
        moverParaCabeca(no);
        return no.valor;
    }

    public void inserir(K chave, V valor) {
        No<K, V> no = mapa.get(chave);
        if (no == null) {
            No<K, V> novoNo = new No<>(chave, valor);
            mapa.put(chave, novoNo);
            adicionarNo(novoNo);
            if (mapa.size() > capacidade) {
                No<K, V> cauda = removerCauda();
                mapa.remove(cauda.chave);
            }
        } else {
            no.valor = valor;
            moverParaCabeca(no);
        }
    }

    public void remover(K chave) {
        No<K, V> no = mapa.get(chave);
        if (no != null) {
            removerNo(no);
            mapa.remove(chave);
            System.out.println("Chave removida do cache: " + chave);
        } else {
            System.out.println("Chave não encontrada no cache: " + chave);
        }
    }

    private void adicionarNo(No<K, V> no) {
        no.proximo = cabeca.proximo;
        no.proximo.anterior = no;
        cabeca.proximo = no;
        no.anterior = cabeca;
    }

    private void removerNo(No<K, V> no) {
        no.anterior.proximo = no.proximo;
        no.proximo.anterior = no.anterior;
        no.anterior = null;
        no.proximo = null; // opcional: pode não ser necessário zerar as referências
    }

    private void moverParaCabeca(No<K, V> no) {
        removerNo(no);
        adicionarNo(no);
    }

    private No<K, V> removerCauda() {
        No<K, V> no = cauda.anterior;
        removerNo(no);
        return no;
    }

    public void imprimirCache() {
        No<K, V> atual = cabeca.proximo;
        while (atual != cauda) {
            System.out.println("[" + atual.chave + ": " + atual.valor + "]");
            atual = atual.proximo;
        }
    }

    private static class No<K, V> {
        K chave;
        V valor;
        No<K, V> anterior, proximo;

        No(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
        }
    }
}
