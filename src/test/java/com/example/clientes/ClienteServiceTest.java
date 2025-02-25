package com.example.clientes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.clientes.model.Cliente;
import com.example.clientes.service.ClienteService;
import com.example.clientes.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente(1L, "João Silva", "joao@email.com");
    }

    @Test
    void deveCriarClienteComSucesso() {
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        Cliente novoCliente = clienteService.criarCliente(cliente);
        assertNotNull(novoCliente);
        assertEquals("João Silva", novoCliente.getNome());
    }

    @Test
    void deveBuscarClientePorId() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        Cliente encontrado = clienteService.buscarClientePorId(1L);
        assertNotNull(encontrado);
        assertEquals(1L, encontrado.getId());
    }

    @Test
    void deveAtualizarCliente() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        Cliente atualizado = clienteService.atualizarCliente(1L, cliente);
        assertEquals("João Silva", atualizado.getNome());
    }

    @Test
    void deveRemoverCliente() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        doNothing().when(clienteRepository).deleteById(1L);
        clienteService.removerCliente(1L);
        verify(clienteRepository, times(1)).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoEncontrado() {
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> clienteService.buscarClientePorId(2L));
    }

    @Test
    void deveRetornarListaDeClientes() {
        List<Cliente> clientes = Arrays.asList(cliente);
        when(clienteRepository.findAll()).thenReturn(clientes);
        List<Cliente> resultado = clienteService.listarClientes();
        assertFalse(resultado.isEmpty());
    }
}
