package me.dio.collections.list.ordenacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.SequencedCollection;
import java.util.stream.Collectors;

/**
 * Classe para ordenação de pessoas.
 * Implementa Sequenced Collections (Java 21+) e Streams para lógica moderna.
 */
public class OrdenacaoPessoas {
    // SequencedCollection garante que possamos reverter ou acessar extremos de forma determinística
    private final List<Pessoa> pessoaList;

    public OrdenacaoPessoas() {
        this.pessoaList = new ArrayList<>();
    }

    public void adicionarPessoa(String nome, int idade, double altura) {
        pessoaList.add(new Pessoa(nome, idade, altura));
    }

    /**
     * Ordena a lista por idade usando Streams e Comparable (ordenación natural).
     * Retorna uma lista inmutável para proteção de dados.
     */
    public List<Pessoa> ordenarPorIdade() {
        if (pessoaList.isEmpty()) throw new IllegalStateException("A lista está vazia!");
        
        return pessoaList.stream()
                .sorted()
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Ordena la lista por altura usando Comparator fluido.
     * Retorna una lista inmutable.
     */
    public List<Pessoa> ordenarPorAltura() {
        if (pessoaList.isEmpty()) throw new IllegalStateException("A lista está vazia!");

        return pessoaList.stream()
                .sorted(Pessoa.porAltura())
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Exemplo de uso de Sequenced Collection para inverter a ordem.
     */
    public List<Pessoa> obterListaInvertida() {
        return pessoaList.reversed().stream().toList();
    }

    public static void main(String[] args) {
        OrdenacaoPessoas ordenacaoPessoas = new OrdenacaoPessoas();

        ordenacaoPessoas.adicionarPessoa("Alice", 20, 1.56);
        ordenacaoPessoas.adicionarPessoa("Bob", 30, 1.80);
        ordenacaoPessoas.adicionarPessoa("Charlie", 25, 1.70);
        ordenacaoPessoas.adicionarPessoa("David", 17, 1.56);

        System.out.println("Lista Original: " + ordenacaoPessoas.pessoaList);
        System.out.println("Por Idade (Natural): " + ordenacaoPessoas.ordenarPorIdade());
        System.out.println("Por Altura (Custom): " + ordenacaoPessoas.ordenarPorAltura());
        System.out.println("Lista Invertida (Java 21+): " + ordenacaoPessoas.obterListaInvertida());
    }
}
