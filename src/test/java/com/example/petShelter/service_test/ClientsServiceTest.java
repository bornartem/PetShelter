package com.example.petShelter.service_test;

import com.example.petShelter.model.Clients;
import com.example.petShelter.repository.ClientsRepository;
import com.example.petShelter.service.ClientsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientsServiceTest {

    @Mock
    private ClientsRepository clientsRepositoryMock;

    @InjectMocks
    private ClientsService clientsService;
    private Clients clients = new Clients(null, 1L, 1L, "Artem", "+7-911-081_18_10");
    private Clients clients1 = new Clients(null, 2L, 2L, "Art", "+7-911-081_10_10");

    @Test
    public void shouldReturnCreateClient(){
        when(clientsRepositoryMock.save(clients)).thenReturn(clients);
        Clients result = clientsService.create(clients);
        assertEquals(clients, result);
    }

    @Test
    public void shouldReturnReadClient(){
        when(clientsRepositoryMock.findById(1L)).thenReturn(Optional.of(clients));
        Clients result = clientsService.read(1L);
        assertEquals(clients, result);
    }

    @Test
    public void shouldReturnUpdateClient(){
        when(clientsRepositoryMock.save(clients)).thenReturn(clients);
        Clients result = clientsService.update(clients);
        assertEquals(clients, result);
    }

    @Test
    public void shouldReturnGetAll(){
        List<Clients> clientsList = new ArrayList<>();
        clientsList.add(clients);
        clientsList.add(clients1);
        when(clientsRepositoryMock.findAll()).thenReturn((List<Clients>)clientsList);
        List<Clients> result = clientsRepositoryMock.findAll();
        assertEquals(clientsList, result);
    }
}
