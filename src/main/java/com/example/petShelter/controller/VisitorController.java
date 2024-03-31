package com.example.petShelter.controller;

import com.example.petShelter.model.Visitor;
import com.example.petShelter.model.Volunteer;
import com.example.petShelter.service.VisitorService;
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
@RequestMapping("/visitor")
public class VisitorController {
    private final VisitorService visitorService;

    @Autowired
    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "create visitor",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteer.class)
                    )
            )
    )
    @PostMapping("/create")
    public Visitor create(@RequestBody Visitor visitor) {
        return visitorService.create(visitor);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "find visitor from db by id",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Visitor.class)
                    )
            )
    })
    @GetMapping("/read{id}")
    public Visitor read(@Parameter(description = "all data of visitor", example = "Artem, 8911-111-1111")
                        @PathVariable long id) {
        return visitorService.read(id);
    }

    @PutMapping("/update")
    public Visitor update(@RequestBody Visitor visitor) {
        return visitorService.update(visitor);
    }

    @DeleteMapping("/delete{id}")
    public void delete(@PathVariable long id) {
        visitorService.delete(id);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "find all visitors from db",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Visitor[].class)
                    )
            )
    })
    @GetMapping("/all")
    public List<Visitor> getAll() {
        return visitorService.getAll();
    }
}
