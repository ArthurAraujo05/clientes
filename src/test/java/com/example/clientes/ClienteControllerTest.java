package com.example.clientes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.clientes.dto.ClienteDto;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    private Cliente cliente;
    private ClienteDto clienteDto;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João Silva");
        cliente.setCorrentista(999999999L);
        cliente.s(true);
        cliente.setRendaMensal(750.5f);
        cliente.setLimiteCredito(1000.0f);
        clienteDto = clienteDto.fromEntity(cliente);
    }

    @Test
    void deveRetornarClientePorId() {
        when(clienteService.getClienteById(1L)).thenReturn(cliente);
        ResponseEntity<ClienteDto> response = clienteController.getClienteById(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(clienteDto, response.getBody());
    }

    @Test
    void deveRetornarTodosOsClientes() {
        List<Cliente> clientes = List.of(cliente);
        when(clienteService.getAllClientes()).thenReturn(clientes);
        ResponseEntity<List<ClienteDTO>> response = clienteController.getAllClientes();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(clientes.stream().map(ClienteDTO::fromEntity).collect(Collectors.toList()), response.getBody());
    }

    @Test
    void deveCriarCliente() {
        when(clienteService.saveCliente(any(Cliente.class))).thenReturn(cliente);
        ResponseEntity<ClienteDTO> response = clienteController.createCliente(clienteDTO);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(clienteDTO, response.getBody());
    }

    @Test
    void deveAtualizarCliente() {
        when(clienteService.updateCliente(eq(1L), any(Cliente.class))).thenReturn(cliente);
        ResponseEntity<ClienteDTO> response = clienteController.updateCliente(1L, clienteDTO);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(clienteDTO, response.getBody());
    }

    @Test
    void deveRemoverCliente() {
        doNothing().when(clienteService).deleteCliente(1L);
        ResponseEntity<Void> response = clienteController.deleteCliente(1L);
        assertEquals(204, response.getStatusCodeValue());
    }
}
