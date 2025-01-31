package com.example.clientes.controller;

import com.example.clientes.model.Cliente;
import com.example.clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private static final Logger logger = Logger.getLogger(ClienteController.class.getName());

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        try {
            List<Cliente> clientes = service.findAll();
            logger.info("Listando todos os clientes.");
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            logger.severe("Erro ao listar clientes: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClienteById(@PathVariable Long id) {
        try {
            Optional<Cliente> cliente = service.findById(id);
            if (cliente.isPresent()) {
                logger.info("Cliente encontrado: " + cliente.get().getNome());
                return ResponseEntity.ok(cliente.get());
            } else {
                logger.warning("Cliente com ID " + id + " não encontrado.");
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Cliente não encontrado.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } catch (Exception e) {
            logger.severe("Erro ao buscar cliente: " + e.getMessage());
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erro interno do servidor.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCliente(@RequestBody Cliente cliente) {
        try {
            Cliente novoCliente = service.save(cliente);
            logger.info("Cliente criado com sucesso: " + novoCliente.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
        } catch (Exception e) {
            logger.severe("Erro ao criar cliente: " + e.getMessage());
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erro interno do servidor.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        try {
            Optional<Cliente> clienteExistente = service.findById(id);
            if (clienteExistente.isPresent()) {
                cliente.setId(id);
                Cliente atualizado = service.save(cliente);
                logger.info("Cliente atualizado com sucesso: " + atualizado.getId());
                return ResponseEntity.ok(atualizado);
            } else {
                logger.warning("Cliente com ID " + id + " não encontrado para atualização.");
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "Cliente não encontrado para atualização.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }
        } catch (Exception e) {
            logger.severe("Erro ao atualizar cliente: " + e.getMessage());
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erro interno do servidor.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteCliente(@PathVariable Long id) {
        try {
            Optional<Cliente> clienteExistente = service.findById(id);
            Map<String, String> response = new HashMap<>();
            if (clienteExistente.isPresent()) {
                service.deleteById(id);
                String mensagem = "Cliente com ID " + id + " deletado com sucesso.";
                logger.info(mensagem);
                response.put("message", mensagem);
                return ResponseEntity.ok(response);
            } else {
                logger.warning("Cliente com ID " + id + " não encontrado para deleção.");
                response.put("error", "Cliente não encontrado para deleção.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            logger.severe("Erro ao deletar cliente: " + e.getMessage());
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erro interno do servidor.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
