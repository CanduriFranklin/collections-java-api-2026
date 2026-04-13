package me.dio.collections.set.ordenacao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Suite de Pruebas de Ingeniería - CadastroProdutos (Java 25 Ready)")
class CadastroProdutosTest {

    private CadastroProdutos cadastro;

    @BeforeEach
    void setUp() {
        cadastro = new CadastroProdutos();
        cadastro.adicionarProduto(1L, "Smartphone", 1000.0, 10);
        cadastro.adicionarProduto(2L, "Notebook", 1500.0, 5);
        cadastro.adicionarProduto(3L, "Mouse", 30.0, 20);
        cadastro.adicionarProduto(4L, "Teclado", 50.0, 15);
    }

    @Test
    @DisplayName("Debe validar inmutabilidad de la colección devuelta")
    void deveValidarInmutabilidad() {
        Set<Produto> produtosPorPreco = cadastro.exibirProdutosPorPreco();
        
        assertThatThrownBy(() -> produtosPorPreco.add(new Produto(5L, "Monitor", 800.0, 2)))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("Debe soportar estrés de lectura concurrente con Virtual Threads (Java 25)")
    void deveSuportarLeituraConcorrenteComVirtualThreads() throws InterruptedException {
        int numeroDeHilos = 1000;
        
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, numeroDeHilos).forEach(i -> {
                executor.submit(() -> {
                    Set<Produto> ordenados = cadastro.exibirProdutosPorPreco();
                    assertThat(ordenados).hasSize(4);
                    assertThat(ordenados.iterator().next().preco()).isEqualTo(30.0);
                });
            });
        }
        // El try-with-resources cierra el executor y espera a los Virtual Threads automáticamente
    }

    @Test
    @DisplayName("Debe validar el orden determinista por nombre (Natural Order)")
    void deveValidarOrdenacaoNatural() {
        Set<Produto> porNome = cadastro.exibirProdutosPorNome();
        
        assertThat(porNome)
                .extracting(Produto::nome)
                .containsExactly("Mouse", "Notebook", "Smartphone", "Teclado");
    }

    @Test
    @DisplayName("Debe ignorar duplicados lógicos basados en el código del producto")
    void deveIgnorarDuplicadosLogicos() {
        cadastro.adicionarProduto(1L, "Otro Smartphone", 999.0, 1);
        
        Set<Produto> todos = cadastro.exibirProdutosPorNome();
        assertThat(todos).hasSize(4);
        assertThat(todos).extracting(Produto::nome).doesNotContain("Otro Smartphone");
    }
}
