import by.a1qa.Models.TestModel;
import by.a1qa.Utilities.ApiUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class Listener implements ITestListener {

    static String logs = null;
    @Override
    public void onTestStart(ITestResult result) {
        logs = result.getInstance().toString();
        TestModel.testName = result.getInstanceName();
        TestModel.methodName = result.getName();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logs += " Status " + result.getStatus();
    }

    @Override
    public void onFinish(ITestContext context) {
        ApiUtils.attachLogs(TestModel.testId, logs);
    }

}
