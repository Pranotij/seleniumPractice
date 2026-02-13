package listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import utils.FlakyReportGenerator;
import utils.FlakyTracker;
import utils.LogUtil;

public class TestExecutionFlakyListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        LogUtil.info("Test Suite Started: " + suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        LogUtil.info("Test Suite Finished: " + suite.getName());
        FlakyReportGenerator.generateReport();
        FlakyTracker.clear();
    }


}
