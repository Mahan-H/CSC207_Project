package use_case.asset_report;

import osiris.data_access.PlaidDataAccessObject;
import osiris.use_case.asset_report.AssetReportInteractor;
import osiris.use_case.asset_report.AssetReportOutputBoundary;
import osiris.use_case.asset_report.AssetReportResponseModel;
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

    @Mock
    private AssetReportOutputBoundary presenter; // Mock the presenter

    @InjectMocks
    private AssetReportInteractor interactor; // Interactor to test

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testCreateAssetReport_Success() throws Exception {
        // Mock input data
        String mockAccessToken = "access-sandbox-123";
        int daysRequested = 90;
        String mockAssetReportToken = "asset-report-token-123";

        // Mock the DAO behavior
        when(plaidDao.createAssetReport(mockAccessToken, daysRequested)).thenReturn(mockAssetReportToken);

        // Call the method
        AssetReportResponseModel response = interactor.createAssetReport(mockAccessToken, daysRequested);

        // Assertions
        assertNotNull(response);
        assertEquals(mockAssetReportToken, response.getReportData());

        // Verify DAO and presenter behavior
        verify(plaidDao, times(1)).createAssetReport(mockAccessToken, daysRequested);
        verify(presenter, times(1)).presentAssetReport(any(AssetReportResponseModel.class));
    }

    @Test
    void testCreateAssetReport_Failure() throws Exception {
        // Mock input data
        String mockAccessToken = "access-sandbox-123";
        int daysRequested = 90;

        // Mock the DAO to throw an IOException
        when(plaidDao.createAssetReport(mockAccessToken, daysRequested)).thenThrow(new IOException("API Error"));

        // Call the method and expect an exception
        PlaidUseCaseException exception = assertThrows(PlaidUseCaseException.class, () ->
                interactor.createAssetReport(mockAccessToken, daysRequested)
        );

        // Assertions
        assertTrue(exception.getMessage().contains("Error creating asset report"));

        // Verify DAO call
        verify(plaidDao, times(1)).createAssetReport(mockAccessToken, daysRequested);
        verifyNoInteractions(presenter); // Presenter should not be called
    }

    @Test
    void testGetAssetReport_Success() throws Exception {
        // Mock input data
        String mockAssetReportToken = "asset-report-token-123";
        String mockAssetReportJson = "{\"report\": {\"accounts\": []}}";

        // Mock the DAO behavior
        when(plaidDao.getAssetReport(mockAssetReportToken)).thenReturn(mockAssetReportJson);

        // Call the method
        AssetReportResponseModel response = interactor.getAssetReport(mockAssetReportToken);

        // Assertions
        assertNotNull(response);
        assertEquals(mockAssetReportJson, response.getReportData());

        // Verify DAO and presenter behavior
        verify(plaidDao, times(1)).getAssetReport(mockAssetReportToken);
        verify(presenter, times(1)).presentAssetReport(any(AssetReportResponseModel.class));
    }

    @Test
    void testGetAssetReport_Failure() throws Exception {
        // Mock input data
        String mockAssetReportToken = "asset-report-token-123";

        // Mock the DAO to throw an IOException
        when(plaidDao.getAssetReport(mockAssetReportToken)).thenThrow(new IOException("API Error"));

        // Call the method and expect an exception
        PlaidUseCaseException exception = assertThrows(PlaidUseCaseException.class, () ->
                interactor.getAssetReport(mockAssetReportToken)
        );

        // Assertions
        assertTrue(exception.getMessage().contains("Error retrieving asset report"));

        // Verify DAO call
        verify(plaidDao, times(1)).getAssetReport(mockAssetReportToken);
        verifyNoInteractions(presenter); // Presenter should not be called
    }

    @Test
    void testGetAssetReportPdf_Success() throws Exception {
        // Mock input data
        String mockAssetReportToken = "asset-report-token-123";
        byte[] mockPdfData = new byte[]{1, 2, 3};

        // Mock the DAO behavior
        when(plaidDao.getAssetReportPdf(mockAssetReportToken)).thenReturn(mockPdfData);

        // Call the method
        byte[] result = interactor.getAssetReportPdf(mockAssetReportToken);

        // Assertions
        assertNotNull(result);
        assertArrayEquals(mockPdfData, result);

        // Verify DAO and presenter behavior
        verify(plaidDao, times(1)).getAssetReportPdf(mockAssetReportToken);
        verify(presenter, times(1)).presentAssetReportPdf(any(byte[].class));
    }

    @Test
    void testGetAssetReportPdf_Failure() throws Exception {
        // Mock input data
        String mockAssetReportToken = "asset-report-token-123";

        // Mock the DAO to throw an IOException
        when(plaidDao.getAssetReportPdf(mockAssetReportToken)).thenThrow(new IOException("API Error"));

        // Call the method and expect an exception
        PlaidUseCaseException exception = assertThrows(PlaidUseCaseException.class, () ->
                interactor.getAssetReportPdf(mockAssetReportToken)
        );

        // Assertions
        assertTrue(exception.getMessage().contains("Error retrieving asset report PDF"));

        // Verify DAO call
        verify(plaidDao, times(1)).getAssetReportPdf(mockAssetReportToken);
        verifyNoInteractions(presenter); // Presenter should not be called
    }
}
