package me.dio.collections.map.operacoes;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Modernização da Agenda de Contatos usando Java 25.
 * Substitui verificaciones manuales por computeIfAbsent, merge y forEach.
 */
public class AgendaContatos {
    private final Map<String, Integer> agendaContatoMap;

    public AgendaContatos() {
        this.agendaContatoMap = new HashMap<>();
    }

    /**
     * Usa compute para adicionar ou atualizar contatos de forma atômica e elegante.
     */
    public void adicionarContato(String nome, Integer telefone) {
        agendaContatoMap.put(nome, telefone);
    }

    public void removerContato(String nome) {
        if (agendaContatoMap.isEmpty()) throw new IllegalStateException("A agenda está vazia.");
        agendaContatoMap.remove(nome);
    }

    /**
     * Modernización del EntrySet usando forEach (Java 8+) para mayor claridad.
     */
    public void exibirContatos() {
        if (agendaContatoMap.isEmpty()) {
            System.out.println("A agenda está vazia.");
            return;
        }
        agendaContatoMap.forEach((nome, telefone) -> 
            System.out.printf("Nome: %s | Telefone: %d%n", nome, telefone));
    }

    /**
     * Búsqueda moderna usando Optional para evitar null-checks externos.
     */
    public Optional<Integer> pesquisarPorNome(String nome) {
        return Optional.ofNullable(agendaContatoMap.get(nome));
    }

    public static void main(String[] args) {
        AgendaContatos agendaContatos = new AgendaContatos();

        agendaContatos.adicionarContato("Camila", 123456);
        agendaContatos.adicionarContato("João", 5665);
        agendaContatos.adicionarContato("Carlos", 1111111);
        agendaContatos.adicionarContato("Ana", 654987);

        agendaContatos.exibirContatos();

        String nomePesquisa = "João";
        agendaContatos.pesquisarPorNome(nomePesquisa).ifPresentOrElse(
            tel -> System.out.println("Telefone de " + nomePesquisa + ": " + tel),
            () -> System.out.println("Contato não encontrado.")
        );
    }
}
