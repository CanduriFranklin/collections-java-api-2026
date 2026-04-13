package me.dio.collections.list.pesquisa;

/**
 * Representa um livro no catálogo.
 * Utiliza Record (Java 14+) para imutabilidade nativa e redução de boilerplate.
 */
public record Livro(String titulo, String autor, int anoPublicacao) {
    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", anoPublicacao=" + anoPublicacao +
                '}';
    }
}
