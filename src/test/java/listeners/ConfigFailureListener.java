package listeners;

import org.testng.IConfigurationListener;
import org.testng.ITestResult;

public class ConfigFailureListener implements IConfigurationListener {

    @Override
    public void onConfigurationFailure(ITestResult result) {
        System.err.println("❌ CONFIGURATION FAILED: " + result.getThrowable().getMessage());
        result.getThrowable().printStackTrace();
    }

    @Override
    public void onConfigurationSuccess(ITestResult result) {
        // Optional: can leave empty
    }

    @Override
    public void onConfigurationSkip(ITestResult result) {
        // Optional: can leave empty
    }
}