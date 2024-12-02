package osiris.use_case.asset_report;

import osiris.data_access.PlaidDataAccessObject;
import osiris.utility.exceptions.PlaidUseCaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Interactor for the Dashboard Use Case.
 */
@Service
public class AssetReportInteractor implements AssetReportInputBoundary {

    private final PlaidDataAccessObject plaidDao;

    @Autowired
    public AssetReportInteractor(PlaidDataAccessObject plaidDao) {
        this.plaidDao = plaidDao;
    }

    @Override
    public String createAssetReport(String accessToken, int daysRequested) throws PlaidUseCaseException {
        try {
            return plaidDao.createAssetReport(accessToken, daysRequested);
        } catch (Exception e) {
            throw new PlaidUseCaseException("Error creating Asset Report", e);
        }
    }

    @Override
    public String getAssetReport(String assetReportToken) throws PlaidUseCaseException {
        try {
            return plaidDao.getAssetReport(assetReportToken);
        } catch (Exception e) {
            throw new PlaidUseCaseException("Error retrieving Asset Report JSON", e);
        }
    }

    @Override
    public byte[] getAssetReportPdf(String assetReportToken) throws PlaidUseCaseException {
        try {
            return plaidDao.getAssetReportPdf(assetReportToken);
        } catch (Exception e) {
            throw new PlaidUseCaseException("Error retrieving Asset Report PDF", e);
        }
    }
}
