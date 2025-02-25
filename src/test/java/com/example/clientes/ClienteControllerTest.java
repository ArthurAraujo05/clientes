package com.example.clientes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.clientes.model.Cliente;
import com.example.clientes.service.ClienteService;
import com.example.clientes.controller.ClienteController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente(1L, "João Silva", "joao@email.com");
    }

    @Test
    void deveRetornarResponseEntityParaBuscarCliente() {
        when(clienteService.buscarClientePorId(1L)).thenReturn(cliente);
        ResponseEntity<Cliente> response = (ResponseEntity<Cliente>) clienteController.getClienteById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(cliente, response.getBody());
    }

    @Test
    void deveRetornarResponseEntityParaCriarCliente() {
        when(clienteService.criarCliente(any(Cliente.class))).thenReturn(cliente);
        ResponseEntity<Cliente> response = (ResponseEntity<Cliente>) clienteController.createCliente(cliente);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(cliente, response.getBody());
    }

    @Test
    void deveRemoverClienteViaController() {
        doNothing().when(clienteService).removerCliente(1L);
        ResponseEntity<Map<String, String>> response = clienteController.deleteCliente(1L);
        assertEquals(204, response.getStatusCodeValue());
    }
}
