package service;

import org.example.lifesafe.model.entities.*;
import org.example.lifesafe.model.enums.InsuranceType;
import org.example.lifesafe.repository.IContractRepository;
import org.example.lifesafe.repository.IInsuranceRepository;
import org.example.lifesafe.service.impl.InsuranceServiceImpl;
import org.example.lifesafe.util.CalculateDevis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InsuranceServiceImplTest {

    @Mock
    private IInsuranceRepository insuranceRepository;

    @Mock
    private CalculateDevis calculateDevis;

    @Mock
    private IContractRepository contractRepository;

    @InjectMocks
    private InsuranceServiceImpl insuranceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Tests for calculateAutomobileDevis
    @Test
    public void calculateAutomobileDevis_ShouldReturnCalculatedValue() {
        Automobile automobile = new Automobile();
        double expectedDevis = 800.0;

        when(calculateDevis.calculateAutomobileDevis(automobile)).thenReturn(expectedDevis);

        double result = insuranceService.calculateAutomobileDevis(automobile);

        assertEquals(expectedDevis, result, 0.01);
        verify(calculateDevis, times(1)).calculateAutomobileDevis(automobile);
    }

    // Tests for calculateHousingDevis
    @Test
    public void calculateHousingDevis_ShouldReturnCalculatedValue() {
        Housing housing = new Housing();
        double expectedDevis = 450.0;

        when(calculateDevis.calculateHousingDevis(housing)).thenReturn(expectedDevis);

        double result = insuranceService.calculateHousingDevis(housing);

        assertEquals(expectedDevis, result, 0.01);
        verify(calculateDevis, times(1)).calculateHousingDevis(housing);
    }

    // Tests for calculateHealthDevis
    @Test
    public void calculateHealthDevis_ShouldReturnCalculatedValue() {
        Health health = new Health();
        double expectedDevis = 300.0;

        when(calculateDevis.calculateHealthDevis(health)).thenReturn(expectedDevis);

        double result = insuranceService.calculateHealthDevis(health);

        assertEquals(expectedDevis, result, 0.01);
        verify(calculateDevis, times(1)).calculateHealthDevis(health);
    }

    // Tests for checkUserHasActiveInsurance
    @Test
    public void checkUserHasActiveInsurance_UserHasActiveInsurance_ShouldReturnTrue() {
        int userId = 1;
        InsuranceType insuranceType = InsuranceType.Automobile;

        when(contractRepository.userHasActiveInsurance(userId, insuranceType)).thenReturn(true);

        boolean result = insuranceService.checkUserHasActiveInsurance(userId, insuranceType);

        assertTrue(result);
        verify(contractRepository, times(1)).userHasActiveInsurance(userId, insuranceType);
    }

    @Test
    public void checkUserHasActiveInsurance_UserHasNoActiveInsurance_ShouldReturnFalse() {
        int userId = 1;
        InsuranceType insuranceType = InsuranceType.Health;

        when(contractRepository.userHasActiveInsurance(userId, insuranceType)).thenReturn(false);

        boolean result = insuranceService.checkUserHasActiveInsurance(userId, insuranceType);

        assertFalse(result);
        verify(contractRepository, times(1)).userHasActiveInsurance(userId, insuranceType);
    }

    // Tests for checkUserHasActiveAnyInsurance
    @Test
    public void checkUserHasActiveAnyInsurance_UserHasOneActiveInsurance_ShouldReturnTrue() {
        int userId = 1;

        when(contractRepository.userHasActiveInsurance(userId, InsuranceType.Automobile)).thenReturn(false);
        when(contractRepository.userHasActiveInsurance(userId, InsuranceType.Housing)).thenReturn(true);
        when(contractRepository.userHasActiveInsurance(userId, InsuranceType.Health)).thenReturn(false);

        boolean result = insuranceService.checkUserHasActiveAnyInsurance(userId);

        assertTrue(result);
        verify(contractRepository, times(1)).userHasActiveInsurance(userId, InsuranceType.Automobile);
        verify(contractRepository, times(1)).userHasActiveInsurance(userId, InsuranceType.Housing);
        verify(contractRepository, times(1)).userHasActiveInsurance(userId, InsuranceType.Health);
    }

    @Test
    public void checkUserHasActiveAnyInsurance_UserHasNoActiveInsurances_ShouldReturnFalse() {
        int userId = 1;

        when(contractRepository.userHasActiveInsurance(userId, InsuranceType.Automobile)).thenReturn(false);
        when(contractRepository.userHasActiveInsurance(userId, InsuranceType.Housing)).thenReturn(false);
        when(contractRepository.userHasActiveInsurance(userId, InsuranceType.Health)).thenReturn(false);

        boolean result = insuranceService.checkUserHasActiveAnyInsurance(userId);

        assertFalse(result);
        verify(contractRepository, times(1)).userHasActiveInsurance(userId, InsuranceType.Automobile);
        verify(contractRepository, times(1)).userHasActiveInsurance(userId, InsuranceType.Housing);
        verify(contractRepository, times(1)).userHasActiveInsurance(userId, InsuranceType.Health);
    }
}
