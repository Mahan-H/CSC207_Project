package osiris.use_case.plaid;

import osiris.data_access.PlaidDataAccessObject;
import osiris.data_access.PlaidDataAccessObject.ExchangeTokenResponse;
import osiris.data_access.PlaidDataAccessObject.LinkTokenResponse;
import osiris.utility.exceptions.PlaidException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osiris.utility.exceptions.PlaidUseCaseException;

import java.io.IOException;

/**
 * The Plaid Interactor implementing the PlaidInputBoundary.
 */
@Service
public class PlaidInteractor implements PlaidInputBoundary {

    private final PlaidDataAccessObject plaidDao;

    @Autowired
    public PlaidInteractor(PlaidDataAccessObject plaidDao) {
        this.plaidDao = plaidDao;
    }

    @Override
    public CreateLinkTokenOutputData createLinkToken(CreateLinkTokenInputData inputData) throws PlaidUseCaseException {

        try {
            LinkTokenResponse response = plaidDao.createLinkToken(
                    inputData.getClientName(),
                    inputData.getCountryCodes(),
                    inputData.getLanguage(),
                    inputData.getUserClientId(),
                    inputData.getProducts()
            );
            return new CreateLinkTokenOutputData(response.link_token, response.request_id);
        } catch (PlaidException e) {
            throw new PlaidUseCaseException("Failed to create Link Token: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new PlaidUseCaseException("IO Error while creating Link Token", e);
        }
    }

    @Override
    public ExchangePublicTokenOutputData exchangePublicToken(ExchangePublicTokenInputData inputData) throws PlaidUseCaseException {
        try {
            ExchangeTokenResponse response = plaidDao.exchangePublicToken(inputData.getPublicToken());

            return new ExchangePublicTokenOutputData(response.access_token, response.item_id, response.request_id);
        } catch (PlaidException e) {
            throw new PlaidUseCaseException("Failed to exchange Public Token: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new PlaidUseCaseException("IO Error while exchanging Public Token", e);
        }
    }

    @Override
    public String createAssetReport(String accessToken, int daysRequested) throws PlaidUseCaseException {
        try {
            return plaidDao.createAssetReport(accessToken, daysRequested);
        } catch (PlaidException e) {
            throw new PlaidUseCaseException("Failed to create Asset Report: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new PlaidUseCaseException("IO Error while creating Asset Report", e);
        }
    }

    @Override
    public String retrieveAssetReport(String assetReportToken) throws PlaidUseCaseException {
        try {
            return plaidDao.retrieveAssetReport(assetReportToken);
        } catch (PlaidException e) {
            throw new PlaidUseCaseException("Failed to retrieve Asset Report: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new PlaidUseCaseException("IO Error while retrieving Asset Report", e);
        }
    }
}


