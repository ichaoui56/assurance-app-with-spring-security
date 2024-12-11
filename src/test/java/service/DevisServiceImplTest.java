package org.example.lifesafe.service.impl;

import org.example.lifesafe.model.entities.Devis;
import org.example.lifesafe.repository.IDevisRepository;
import org.example.lifesafe.service.IDevisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DevisServiceImplTest {

    @Mock
    private IDevisRepository devisRepository;

    @InjectMocks
    private DevisServiceImpl devisService;

    private Devis devis;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        devis = new Devis();
        devis.setId(1L);
    }

    @Test
    public void testAddDevis_Success() {
        devisService.addDevis(devis);

        verify(devisRepository, times(1)).create(devis);
    }

    @Test
    public void testAddDevis_NullDevis_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> devisService.addDevis(null),
                "Adding a null Devis should throw IllegalArgumentException");
    }

    @Test
    public void testAddDevis_RepositoryThrowsException() {
        doThrow(new RuntimeException("Repository error")).when(devisRepository).create(any(Devis.class));

        Exception exception = assertThrows(RuntimeException.class, () -> devisService.addDevis(devis),
                "Repository error should propagate as a RuntimeException");
        assertEquals("Repository error", exception.getMessage());
    }

    @Test
    public void testFindById_ReturnsDevis_WhenDevisExists() {
        when(devisRepository.findById(1)).thenReturn(Optional.of(devis));

        Optional<Devis> result = devisService.findById(1);

        assertTrue(result.isPresent(), "Devis should be present");
        assertEquals(devis, result.get(), "Returned Devis should match the mock Devis");
    }

    @Test
    public void testFindById_ReturnsEmpty_WhenDevisDoesNotExist() {
        when(devisRepository.findById(99)).thenReturn(Optional.empty());

        Optional<Devis> result = devisService.findById(99);

        assertFalse(result.isPresent(), "Devis should not be present for non-existing ID");
    }

    @Test
    public void testFindById_NegativeId_ReturnsEmpty() {
        Optional<Devis> result = devisService.findById(-1);

        assertFalse(result.isPresent(), "Devis should not be present for a negative ID");
    }

    @Test
    public void testFindById_RepositoryThrowsException() {
        when(devisRepository.findById(1)).thenThrow(new RuntimeException("Repository error"));

        Exception exception = assertThrows(RuntimeException.class, () -> devisService.findById(1),
                "Repository error should propagate as a RuntimeException");
        assertEquals("Repository error", exception.getMessage());
    }

    @Test
    public void testFindById_ZeroId_ReturnsEmpty() {
        Optional<Devis> result = devisService.findById(0);

        assertFalse(result.isPresent(), "Devis should not be present for an ID of 0");
    }

    @Test
    public void testUpdateDevis_Success() {
        devisService.updateDevis(devis);

        verify(devisRepository, times(1)).update(devis);
    }

    @Test
    public void testUpdateDevis_NullDevis_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> devisService.updateDevis(null),
                "Updating a null Devis should throw IllegalArgumentException");
    }

    @Test
    public void testUpdateDevis_RepositoryThrowsException() {
        doThrow(new RuntimeException("Repository error")).when(devisRepository).update(any(Devis.class));

        Exception exception = assertThrows(RuntimeException.class, () -> devisService.updateDevis(devis),
                "Repository error should propagate as a RuntimeException");
        assertEquals("Repository error", exception.getMessage());
    }

    @Test
    public void testAddDevis_DuplicateDevis_DoesNotCreateAgain() {
        when(devisRepository.findById(1)).thenReturn(Optional.of(devis));

        devisService.addDevis(devis);

        verify(devisRepository, never()).create(devis);
    }
}
