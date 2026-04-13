package me.dio.collections.list.ordenacao;

import java.util.Comparator;

/**
 * Representa uma pessoa para ordenação.
 * Utiliza Record (Java 14+) e implementa Comparable para ordenação natural por idade.
 */
public record Pessoa(String nome, int idade, double altura) implements Comparable<Pessoa> {

    @Override
    public int compareTo(Pessoa p) {
        return Integer.compare(this.idade, p.idade());
    }

    /**
     * Factory method para um Comparator fluído por altura.
     */
    public static Comparator<Pessoa> porAltura() {
        return Comparator.comparingDouble(Pessoa::altura);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", altura=" + altura +
                '}';
    }
}
