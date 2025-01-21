package com.intimetec.automation.helpers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentReportUtils {
    private ExtentReports extentReports;
    private ExtentTest test;

    public ExtentReportUtils(String testName) {
        this.extentReports = ExtentReportManagerUtils.createExtentReports();
        this.test = extentReports.createTest(testName);
    }

    public void logInfo(String message) {
        test.info(message);
    }

    public void logPass(String message) {
        test.pass(message);
    }

    public void logFail(String message) {
        test.fail(message);
    }
}
