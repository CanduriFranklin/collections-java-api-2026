package me.dio.collections.list.pesquisa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Catálogo de livros moderno usando Java 25.
 * Substitui loops imperativos por Streams API.
 */
public class CatalogoLivros {
    private final List<Livro> livroList;

    public CatalogoLivros() {
        this.livroList = new ArrayList<>();
    }

    public void adicionarLivro(String titulo, String autor, int anoPublicacao) {
        livroList.add(new Livro(titulo, autor, anoPublicacao));
    }

    /**
     * Pesquisa livros por autor usando Streams e filtragem declarativa.
     * Retorna uma lista inmutável.
     */
    public List<Livro> pesquisarPorAutor(String autor) {
        if (livroList.isEmpty()) throw new IllegalStateException("O catálogo está vazio!");

        return livroList.stream()
                .filter(l -> l.autor().equalsIgnoreCase(autor))
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Pesquisa livros por intervalo de anos com streams.
     */
    public List<Livro> pesquisarPorIntervaloAnos(int anoInicial, int anoFinal) {
        if (livroList.isEmpty()) throw new IllegalStateException("O catálogo está vazio!");

        return livroList.stream()
                .filter(l -> l.anoPublicacao() >= anoInicial && l.anoPublicacao() <= anoFinal)
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * Pesquisa livro por título e retorna o primeiro encontrado usando Optional.
     */
    public Optional<Livro> pesquisarPorTitulo(String titulo) {
        return livroList.stream()
                .filter(l -> l.titulo().equalsIgnoreCase(titulo))
                .findFirst();
    }

    public static void main(String[] args) {
        CatalogoLivros catalogoLivros = new CatalogoLivros();

        catalogoLivros.adicionarLivro("Microsserviços Prontos Para a Produção", "Susan J. Fowler", 2017);
        catalogoLivros.adicionarLivro("Java Guia do Programador", "Peter Jandl Junior", 2021);
        catalogoLivros.adicionarLivro("Código Limpo", "Robert C. Martin", 2009);
        catalogoLivros.adicionarLivro("O Codificador Limpo", "Robert C. Martin", 2012);

        System.out.println("Livros de Robert C. Martin: " + catalogoLivros.pesquisarPorAutor("Robert C. Martin"));
        System.out.println("Livros (2010 - 2022): " + catalogoLivros.pesquisarPorIntervaloAnos(2010, 2022));
        
        catalogoLivros.pesquisarPorTitulo("Java Guia do Programador")
                .ifPresentOrElse(
                    livro -> System.out.println("Livro encontrado: " + livro),
                    () -> System.out.println("Livro não encontrado.")
                );
    }
}
