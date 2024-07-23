package Projeto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Testando TabelaHash
        TabelaHash<String, String> tabelaHash = new TabelaHash<>(10);
        tabelaHash.inserir("sessao1", "dados_usuario1");
        tabelaHash.inserir("sessao2", "dados_usuario2");
        tabelaHash.inserir("sessao3", "dados_usuario3");
        tabelaHash.inserir("sessao4", "dados_usuario4");

        System.out.println("Conteúdo da Tabela Hash:");
        tabelaHash.imprimirTabela();
        System.out.println("--------------------------------------------");

        System.out.println("Buscar Dados de Sessão:");
        System.out.println("sessao1: " + tabelaHash.buscar("sessao1"));
        System.out.println("sessao2: " + tabelaHash.buscar("sessao2"));
        System.out.println("sessao3: " + tabelaHash.buscar("sessao3"));

        tabelaHash.remover("sessao2");

        System.out.println("Conteúdo da Tabela Hash após remover sessao2:");
        tabelaHash.imprimirTabela();
        System.out.println("sessao2: " + tabelaHash.buscar("sessao2"));

        Scanner scanner = new Scanner(System.in);
        CacheLRU<String, String> cacheLRU = new CacheLRU<>(3);

        while (true) {
            exibirMenu();
            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    System.out.print("Digite a sessão: ");
                    String sessaoInserir = scanner.nextLine();
                    System.out.print("Digite os dados do usuário: ");
                    String dadosInserir = scanner.nextLine();
                    cacheLRU.inserir(sessaoInserir, dadosInserir);
                    System.out.println("Sessão e dados inseridos no cache.");
                    break;
                case 2:
                    System.out.print("Digite a sessão para buscar: ");
                    String sessaoBuscar = scanner.nextLine();
                    String dados = cacheLRU.buscar(sessaoBuscar);
                    if (dados != null) {
                        System.out.println("Dados encontrados: " + dados);
                    } else {
                        System.out.println("Sessão não encontrada no cache.");
                    }
                    break;
                case 3:
                    System.out.print("Digite a sessão para remover: ");
                    String sessaoRemover = scanner.nextLine();
                    cacheLRU.remover(sessaoRemover);
                    break;
                case 4:
                    System.out.println("Conteúdo do Cache LRU:");
                    cacheLRU.imprimirCache();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("Menu:");
        System.out.println("1. Inserir no Cache");
        System.out.println("2. Buscar no Cache");
        System.out.println("3. Remover do Cache");
        System.out.println("4. Imprimir Cache");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }
}
