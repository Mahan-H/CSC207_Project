package osiris.interface_adapter.assets_report;

import osiris.use_case.asset_report.AssetReportInputBoundary;
import osiris.utility.exceptions.PlaidUseCaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/asset-report")
public class AssetReportController {

    private final AssetReportInputBoundary assetReportInteractor;

    @Autowired
    public AssetReportController(AssetReportInputBoundary assetReportInteractor) {
        this.assetReportInteractor = assetReportInteractor;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAssetReport(@RequestBody String accessToken) {
        try {
            String assetReportToken = assetReportInteractor.createAssetReport(accessToken, 90);
            return ResponseEntity.ok(assetReportToken);
        } catch (PlaidUseCaseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating asset report: " + e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<String> getAssetReport(@RequestParam String assetReportToken) {
        try {
            String assetReportJson = assetReportInteractor.getAssetReport(assetReportToken);
            return ResponseEntity.ok(assetReportJson);
        } catch (PlaidUseCaseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving asset report: " + e.getMessage());
        }
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> getAssetReportPdf(@RequestParam String assetReportToken) {
        try {
            byte[] pdfData = assetReportInteractor.getAssetReportPdf(assetReportToken);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "asset_report.pdf");

            return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
        } catch (PlaidUseCaseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
