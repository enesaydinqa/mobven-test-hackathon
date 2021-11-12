package com.hackathon.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager
{
    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports createExtentReports()
    {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./target/HackathonReport.html");
        reporter.config().setReportName("Mobven Test Hackathon");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Team Members:", "Amil, Burak, Enes, Nahide");
        extentReports.setSystemInfo("Author", "SahiMobi");
        return extentReports;
    }
}