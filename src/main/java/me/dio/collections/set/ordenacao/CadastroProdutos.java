package me.dio.collections.set.ordenacao;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Modernização do Cadastro de Produtos com Java 25.
 * Demonstra como Records e Comparadores fluidos reduzem o código a linhas elegantes.
 */
public class CadastroProdutos {
    private final Set<Produto> produtoSet;

    public CadastroProdutos() {
        this.produtoSet = new HashSet<>();
    }

    public void adicionarProduto(long codigo, String nome, double preco, int quantidade) {
        produtoSet.add(new Produto(codigo, nome, preco, quantidade));
    }

    /**
     * Ordenação Natural por Nome (TreeSet usa Comparable do record).
     */
    public Set<Produto> exibirProdutosPorNome() {
        return new TreeSet<>(produtoSet);
    }

    /**
     * Ordenação por Preço em uma linha elegante usando Java 25.
     */
    public Set<Produto> exibirProdutosPorPreco() {
        return produtoSet.stream()
                .sorted(Produto.porPreco())
                .collect(Collectors.toUnmodifiableSet());
    }

    public static void main(String[] args) {
        CadastroProdutos cadastroProdutos = new CadastroProdutos();

        cadastroProdutos.adicionarProduto(1L, "Smartphone", 1000d, 10);
        cadastroProdutos.adicionarProduto(2L, "Notebook", 1500d, 5);
        cadastroProdutos.adicionarProduto(1L, "Mouse", 30d, 20); // Código duplicado: ignorado pelo Set
        cadastroProdutos.adicionarProduto(4L, "Teclado", 50d, 15);

        System.out.println("Produtos (HashSet): " + cadastroProdutos.produtoSet);
        System.out.println("Por Nome (Natural): " + cadastroProdutos.exibirProdutosPorNome());
        System.out.println("Por Preço (Custom): " + cadastroProdutos.exibirProdutosPorPreco());
    }
}
