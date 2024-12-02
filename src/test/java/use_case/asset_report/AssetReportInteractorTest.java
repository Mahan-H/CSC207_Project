package use_case.asset_report;

import osiris.data_access.PlaidDataAccessObject;
import osiris.utility.exceptions.PlaidUseCaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssetReportInteractorTest {

    @Mock
    private PlaidDataAccessObject plaidDao; // Mock the DAO

    @InjectMocks
    private AssetReportInteractor interactor; // Interactor to test

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testCreateAssetReport_Success() throws Exception {
        // Mock data
        String mockAccessToken = "access-sandbox-123";
        String mockAssetReportToken = "asset-report-token-123";
        int daysRequested = 90;

        // Mock the DAO call
        when(plaidDao.createAssetReport(mockAccessToken, daysRequested)).thenReturn(mockAssetReportToken);

        // Call the interactor method
        String result = interactor.createAssetReport(mockAccessToken, daysRequested);

        // Assertions
        assertNotNull(result);
        assertEquals(mockAssetReportToken, result);

        // Verify DAO call
        verify(plaidDao, times(1)).createAssetReport(mockAccessToken, daysRequested);
    }

    @Test
    void testCreateAssetReport_Failure() throws Exception {
        // Mock data
        String mockAccessToken = "access-sandbox-123";
        int daysRequested = 90;

        // Mock the DAO call to throw an exception
        when(plaidDao.createAssetReport(mockAccessToken, daysRequested)).thenThrow(new IOException("API Error"));

        // Call the interactor method and expect an exception
        PlaidUseCaseException exception = assertThrows(PlaidUseCaseException.class, () ->
                interactor.createAssetReport(mockAccessToken, daysRequested)
        );

        // Assertions
        assertTrue(exception.getMessage().contains("Error creating Asset Report"));

        // Verify DAO call
        verify(plaidDao, times(1)).createAssetReport(mockAccessToken, daysRequested);
    }

    @Test
    void testGetAssetReport_Success() throws Exception {
        // Mock data
        String mockAssetReportToken = "asset-report-token-123";
        String mockAssetReportJson = "{\"report\": {\"accounts\": []}}";

        // Mock the DAO call
        when(plaidDao.getAssetReport(mockAssetReportToken)).thenReturn(mockAssetReportJson);

        // Call the interactor method
        String result = interactor.getAssetReport(mockAssetReportToken);

        // Assertions
        assertNotNull(result);
        assertEquals(mockAssetReportJson, result);

        // Verify DAO call
        verify(plaidDao, times(1)).getAssetReport(mockAssetReportToken);
    }

    @Test
    void testGetAssetReport_Failure() throws Exception {
        // Mock data
        String mockAssetReportToken = "asset-report-token-123";

        // Mock the DAO call to throw an exception
        when(plaidDao.getAssetReport(mockAssetReportToken)).thenThrow(new IOException("API Error"));

        // Call the interactor method and expect an exception
        PlaidUseCaseException exception = assertThrows(PlaidUseCaseException.class, () ->
                interactor.getAssetReport(mockAssetReportToken)
        );

        // Assertions
        assertTrue(exception.getMessage().contains("Error retrieving Asset Report JSON"));

        // Verify DAO call
        verify(plaidDao, times(1)).getAssetReport(mockAssetReportToken);
    }

    @Test
    void testGetAssetReportPdf_Success() throws Exception {
        // Mock data
        String mockAssetReportToken = "asset-report-token-123";
        byte[] mockPdfData = new byte[]{1, 2, 3};

        // Mock the DAO call
        when(plaidDao.getAssetReportPdf(mockAssetReportToken)).thenReturn(mockPdfData);

        // Call the interactor method
        byte[] result = interactor.getAssetReportPdf(mockAssetReportToken);

        // Assertions
        assertNotNull(result);
        assertArrayEquals(mockPdfData, result);

        // Verify DAO call
        verify(plaidDao, times(1)).getAssetReportPdf(mockAssetReportToken);
    }

    @Test
    void testGetAssetReportPdf_Failure() throws Exception {
        // Mock data
        String mockAssetReportToken = "asset-report-token-123";

        // Mock the DAO call to throw an exception
        when(plaidDao.getAssetReportPdf(mockAssetReportToken)).thenThrow(new IOException("API Error"));

        // Call the interactor method and expect an exception
        PlaidUseCaseException exception = assertThrows(PlaidUseCaseException.class, () ->
                interactor.getAssetReportPdf(mockAssetReportToken)
        );

        // Assertions
        assertTrue(exception.getMessage().contains("Error retrieving Asset Report PDF"));

        // Verify DAO call
        verify(plaidDao, times(1)).getAssetReportPdf(mockAssetReportToken);
    }
}