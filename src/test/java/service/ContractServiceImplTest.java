package org.example.lifesafe.service.impl;

import org.example.lifesafe.model.entities.Contract;
import org.example.lifesafe.repository.IContractRepository;
import org.example.lifesafe.service.IContractService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContractServiceImplTest {

    @Mock
    private IContractRepository contractRepository;

    @InjectMocks
    private ContractServiceImpl contractService;

    private Contract contract;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        contract = new Contract();
        contract.setId(1L);

    }

    @Test
    public void testAddContract_Success() {
        contractService.addContract(contract);

        verify(contractRepository, times(1)).create(contract);
    }

    @Test
    public void testAddContract_NullContract_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> contractService.addContract(null),
                "Adding a null contract should throw IllegalArgumentException");
    }

    @Test
    public void testAddContract_RepositoryThrowsException() {
        doThrow(new RuntimeException("Repository error")).when(contractRepository).create(any(Contract.class));

        Exception exception = assertThrows(RuntimeException.class, () -> contractService.addContract(contract),
                "Repository error should propagate as a RuntimeException");
        assertEquals("Repository error", exception.getMessage());
    }

    @Test
    public void testFindById_ReturnsContract_WhenContractExists() {
        when(contractRepository.findById(1)).thenReturn(Optional.of(contract));

        Optional<Contract> result = contractService.findById(1);

        assertTrue(result.isPresent(), "Contract should be present");
        assertEquals(contract, result.get(), "Returned contract should match the mock contract");
    }

    @Test
    public void testFindById_ReturnsEmpty_WhenContractDoesNotExist() {
        when(contractRepository.findById(99)).thenReturn(Optional.empty());

        Optional<Contract> result = contractService.findById(99);

        assertFalse(result.isPresent(), "Contract should not be present for non-existing ID");
    }

    @Test
    public void testFindById_NegativeId_ReturnsEmpty() {
        Optional<Contract> result = contractService.findById(-1);

        assertFalse(result.isPresent(), "Contract should not be present for a negative ID");
    }

    @Test
    public void testFindById_RepositoryThrowsException() {
        when(contractRepository.findById(1)).thenThrow(new RuntimeException("Repository error"));

        Exception exception = assertThrows(RuntimeException.class, () -> contractService.findById(1),
                "Repository error should propagate as a RuntimeException");
        assertEquals("Repository error", exception.getMessage());
    }

    @Test
    public void testFindById_ZeroId_ReturnsEmpty() {
        Optional<Contract> result = contractService.findById(0);

        assertFalse(result.isPresent(), "Contract should not be present for an ID of 0");
    }

    @Test
    public void testAddContract_DuplicateContract_DoesNotCreateAgain() {
        when(contractRepository.findById(1)).thenReturn(Optional.of(contract));

        contractService.addContract(contract);

        verify(contractRepository, never()).create(contract);
    }
}
