package com.example.clientes.controller;

import com.example.clientes.dto.ClienteDto;
import com.example.clientes.model.Cliente;
import com.example.clientes.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> getAllClientes() {
        List<ClienteDto> clientes = clienteService.findAll()
                .stream()
                .map(ClienteDto::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteService.getClienteById(id);
        return ResponseEntity.ok(ClienteDto.fromEntity(cliente));
    }

    @PostMapping
    public ResponseEntity<ClienteDto> criarCliente(@RequestBody ClienteDto clienteDTO) {
        Cliente cliente = clienteDTO.toEntity();
        Cliente savedCliente = clienteService.save(cliente);
        return ResponseEntity.ok(ClienteDto.fromEntity(savedCliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDTO) {
        Cliente cliente = clienteDTO.toEntity();
        Cliente updatedCliente = clienteService.atualizarCliente(id, cliente);
        return ResponseEntity.ok(ClienteDto.fromEntity(updatedCliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
