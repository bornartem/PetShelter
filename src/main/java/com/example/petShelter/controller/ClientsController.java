package com.example.petShelter.controller;

import com.example.petShelter.model.Clients;
import com.example.petShelter.service.ClientsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The class consists of methods for REST API in order to make CRUD and
 * other commands for the entity "Clients"
 *
 * @author bornartem
 */
@RestController
@RequestMapping("/client")
public class ClientsController {
    private final ClientsService clientService;

    @Autowired
    public ClientsController(ClientsService clientService) {
        this.clientService = clientService;
    }

    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "create client",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Clients.class)
                    )
            )
    )
    @PostMapping("/create")
    public ResponseEntity<Clients> create(@RequestBody Clients clients) {
        Clients createdClient = clientService.create(clients);
        if (createdClient == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(createdClient);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "find client from db by id",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Clients.class)
                    )
            )
    })
    @GetMapping("/read{id}")
    public ResponseEntity<Clients> read(@Parameter(description = "all data of client", example = "Artem, 8911-111-1111")
                                        @PathVariable long id) {
        Clients clients = clientService.read(id);
        if (clients == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(clients);
    }


    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "update client",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Clients.class)
                    )
            )
    )
    @PutMapping("/update")
    public ResponseEntity<Clients> update(@RequestBody Clients clients) {
        Clients createdClient = clientService.update(clients);
        if (createdClient == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(createdClient);
    }

    @Operation(summary = "Register a foster animal with an adoptive parent",
            description = "Returns the client who adopted the animal"
            )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "register a foster animal with an adoptive parent",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Clients.class)
                    )
            )
    })
    @PutMapping("/registerAnimal")
    public ResponseEntity<Clients> registerAnimalWithAnAdoptiveParent(@RequestParam Long clientId,
                                                                      @RequestParam Long animalId) {
        Clients adoptiveParent = clientService.registerAFosterAnimalWithAnAdoptiveParent(clientId, animalId);
        return ResponseEntity.ok(adoptiveParent);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "delete client from db by id",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Clients.class)
                    )
            )
    })
    @DeleteMapping("/delete{id}")
    public void delete(@PathVariable long id) {
        clientService.delete(id);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "find all clients from db",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Clients[].class)
                    )
            )
    })
    @GetMapping("/all")
    public List<Clients> getAll() {
        return clientService.getAll();
    }

}
