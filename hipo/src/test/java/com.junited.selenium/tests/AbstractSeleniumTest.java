package com.junited.selenium.tests;

import com.junited.selenium.utils.Browser;
import com.junited.selenium.utils.Configuration;
import com.junited.selenium.utils.TestContext;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.internal.AssumptionViolatedException;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public abstract class AbstractSeleniumTest
{
    private static final Logger logger = LoggerFactory.getLogger(AbstractSeleniumTest.class);

    protected static TestContext context;
    protected static Browser browser;
    protected static Configuration configuration;

    private long lStartTime;
    private long lEndTime;
    private TestStatus testStatus;

    //-----


    public TestRule statusLogRule = new TestWatcher()
    {
        @Override
        protected void starting(Description description)
        {
            lStartTime = System.currentTimeMillis();
            logger.info("\t==================================================");
            logger.info("\t====> TEST STARTED {}", getDisplayName(description));
        }

        @Override
        public void succeeded(Description description)
        {
            testStatus = TestStatus.PASSED;
            logger.info("\t====> TEST PASSED {}", getDisplayName(description));
        }

        protected void skipped(AssumptionViolatedException e, Description description)
        {
            testStatus = TestStatus.SKIPPED;
            logger.info("\t====> TEST SKIPPED {}", getDisplayName(description));
        }

        @Override
        public void failed(Throwable e, Description description)
        {
            testStatus = TestStatus.FAILED;

            String displayName = getDisplayName(description);
            long fileId = System.currentTimeMillis();
            String fileName = fileId + "-" + displayName;

            savePageSource(fileName);

            logger.info("\t====> TEST FAILED {}", displayName);
        }

        @Override
        public void finished(Description description)
        {
            printStatus(description);
        }
    };

    //--

    public static void sleep(Long miliseconds)
    {
        sleep(miliseconds.intValue());
    }

    public static void sleep(int miliseconds)
    {
        try
        {
            Thread.sleep(miliseconds);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
        context = new TestContext();
        configuration = context.getConfiguration();

        try
        {
            browser = context.doCreateBrowser();
        }
        catch (Exception e)
        {
            logger.info("browser re-created for test");
            e.printStackTrace();
            browser = context.doCreateBrowser();
        }

        Assert.assertNotNull("browser could not be initialized!", browser);

        browser.manage().window().setSize(new Dimension(1200, 800));
    }

    @AfterClass
    public static void tearDownClass() throws IOException
    {
        if (context != null)
            context.close();
    }

    //--HTML-RESOURCE-CAPTURE

    private void savePageSource(String fileName)
    {
        if (configuration.getHtmlCaptureOption())
        {
            File dir = defineTargetDirectory(configuration.getTestHtmlSourcesDirectory());

            String capturedHtmlSource = browser.getPageSource();
            String htmlSourcePath = dir + System.getProperty("file.separator") + fileName + ".html";

            File newHtmlFile = new File(htmlSourcePath);
            try
            {
                FileUtils.writeStringToFile(newHtmlFile, capturedHtmlSource);
            }
            catch (IOException e)
            {
                logger.info("HTML source file path : [" + htmlSourcePath + "]");
                e.printStackTrace();
            }
        }
    }

    //--LOG

    protected void appendLogToFile(String data, String fileName)
    {
        data = data.replaceAll(",", "");
        File baseDir = new File(configuration.getLogDirectory());

        if (!baseDir.isDirectory())
        {
            if (!baseDir.mkdir())
            {
                throw new RuntimeException("Failed to create base directory for data export: " +
                        baseDir.getAbsolutePath());
            }
        }

        try
        {
            File file = new File(baseDir, fileName);

            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(data);
            fileWriter.flush();
            fileWriter.close();

            logger.info("\tAppend to : " + fileName + " Done");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void printStatus(Description description)
    {
        lEndTime = System.currentTimeMillis();
        long difference = lEndTime - lStartTime;
        long seconds = (difference / 1000) % 60;
        long minutes = (difference / (1000 * 60)) % 60;
        long hours = (difference / (1000 * 60 * 60)) % 24;

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_hhmm");

        String testResult = String.format("\t====> TEST FINISHED %s in %d hours, %d minutes, %d seconds",
                getDisplayName(description), hours, minutes, seconds);

        String testResultLogFormat = String.format("%s\t%s\t%s\t%s\t%d\t%d\t%d\n",
                dateFormat.format(Calendar.getInstance().getTime()),
                description.getTestClass().getSimpleName(),
                description.getMethodName(),
                testStatus,
                hours,
                minutes,
                seconds);

        appendLogToFile(testResultLogFormat, "testMethodFinishedTime.txt");
        logger.info(testResult);
        logger.info("\t==================================================");
    }

    private static File defineTargetDirectory(String directoryName)
    {
        File dir;
        if (!StringUtils.isBlank(System.getenv("WORKSPACE")))
        {
            dir = new File(System.getenv("WORKSPACE") + System.getProperty("file.separator") + directoryName);
        }
        else
        {
            dir = new File(System.getProperty("user.home") + System.getProperty("file.separator") + directoryName);
        }

        //Create directory if does not exist
        if (dir.exists())
        {
            logger.info("A folder with name '" + directoryName + "' is already exist in the path ");
        }
        else
        {
            dir.mkdirs();
        }
        return dir;
    }

    private String getDisplayName(Description description)
    {
        return description.getMethodName() + " (" + description.getTestClass().getSimpleName() + ")";
    }
    //--

    public boolean isTextDisplayedOnPage(String assertText)
    {
        boolean isTextOnPage = false;
        try
        {
            isTextOnPage = browser.findElements(By.xpath("//*[@name='" + assertText + "']")).size() > 0;
        }
        catch (Exception e)
        {
            logger.info(assertText + " is not displayed on page!!!");
        }
        return isTextOnPage;
    }

    public void saveScreenShot(String fileName)
    {
        if (configuration.getScreenshotCaptureOption())
        {
            File dir = defineTargetDirectory(configuration.getTestScreenshotsDirectory());
            browser.manage().window().setSize(new Dimension(1200, 800));

            try
            {
                Thread.sleep(1000);
                String imagePath = dir + System.getProperty("file.separator") + fileName + ".png";

                File screenshotFile = ((TakesScreenshot) browser.getWebDriver()).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshotFile, new File(imagePath));
                logger.info("Screenshot Image Path = [" + imagePath + "]");
            }
            catch (Exception e)
            {
                logger.info("Cannot take screenshot!!!");
                e.printStackTrace();
            }
        }
    }

    //-----

    enum TestStatus
    {
        PASSED,
        FAILED,
        SKIPPED
    }
}
