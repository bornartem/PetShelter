package com.example.petShelter.service;

import com.example.petShelter.model.Volunteers;
import com.example.petShelter.repository.VolunteersRepository;
import com.example.petShelter.service.VolunteersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VolunteerServiceTest {

    @Mock
    private VolunteersRepository volunteersRepositoryMock;

    @InjectMocks
    private VolunteersService volunteersService;
    private Volunteers volunteers = new Volunteers(null, 1L, 1L, "Artem", "+7-911-081_18_10", true);
    private Volunteers volunteers1 = new Volunteers(null, 2L, 2L, "Art", "+7-911-911_18_10", true);

    @Test
    public void shouldReturnCreateVolunteer() {
        when(volunteersRepositoryMock.save(volunteers)).thenReturn(volunteers);
        Volunteers result = volunteersService.create(volunteers);
        assertEquals(volunteers, result);
    }

    @Test
    public void shouldReturnReadVolunteer() {
        when(volunteersRepositoryMock.findById(1L)).thenReturn(Optional.of(volunteers));
        Volunteers result = volunteersService.read(volunteers.getId());
        assertEquals(volunteers, result);
    }

    @Test
    public void shouldReturnUpdateVolunteer() {
        when(volunteersRepositoryMock.save(volunteers)).thenReturn(volunteers);
        Volunteers result = volunteersService.update(volunteers);
        assertEquals(volunteers, result);
    }

    @Test
    public void shouldReturnGetAll() {
        List<Volunteers> volunteersList = new ArrayList<>();
        volunteersList.add(volunteers);
        volunteersList.add(volunteers1);
        when(volunteersRepositoryMock.findAll()).thenReturn((List<Volunteers>) volunteersList);
        List<Volunteers> result = volunteersRepositoryMock.findAll();
        assertEquals(volunteersList, result);
    }
}
