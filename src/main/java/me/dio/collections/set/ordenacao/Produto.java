package me.dio.collections.set.ordenacao;

import java.util.Comparator;
import java.util.Objects;

/**
 * Record Produto: Inmutabilidad nativa.
 * Implementa Comparable para ordenação natural por nome (ignora case).
 */
public record Produto(long codigo, String nome, double preco, int quantidade) 
        implements Comparable<Produto> {

    @Override
    public int compareTo(Produto p) {
        return nome.compareToIgnoreCase(p.nome());
    }

    /**
     * Identidad del Set basada únicamente en el código del producto.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto produto)) return false;
        return codigo == produto.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    /**
     * Comparadores fluidos (Java 25 Ready).
     */
    public static Comparator<Produto> porPreco() {
        return Comparator.comparingDouble(Produto::preco);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                '}';
    }
}
