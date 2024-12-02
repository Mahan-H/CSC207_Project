package osiris.use_case.asset_report;

import java.util.List;

public class AssetReportPresenter implements AssetsReportOutputBoundary {

    @Override
    public void presentAssetsReport(List<Asset> assets) {
        // Example: Print to console or format into CSV
        System.out.println("User Assets Report:");
        for (Asset asset : assets) {
            System.out.println("Asset Name: " + asset.getName() + ", Value: " + asset.getValue());
        }
    }
}
