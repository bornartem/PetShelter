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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author bornartem
 */
@RestController
@RequestMapping("/client")
public class ClientsController {
    private final ClientsService clientService ;

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
    public Clients create(@RequestBody Clients clients) {
        return clientService.create(clients);
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
    public Clients read(@Parameter(description = "all data of client", example = "Artem, 8911-111-1111")
                        @PathVariable long id) {
        return clientService.read(id);
    }

    @PutMapping("/update")
    public Clients update(@RequestBody Clients clients) {
        return clientService.update(clients);
    }

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
