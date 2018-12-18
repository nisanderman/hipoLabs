package com.junited.selenium.utils;

import com.google.common.collect.ImmutableList;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.net.PortProber;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.*;
import java.util.logging.Level;

public class TestContext
{
    private static final Logger logger = LoggerFactory.getLogger(TestContext.class);

    //--
    protected static final int DEFAULT_SLEEP = 2000;
    private Browser browser;
    private WebDriver webDriver;
    private DesiredCapabilities capabilities;
    private Configuration configuration;

    //-----

    public TestContext() throws IOException
    {
        configuration = new Configuration();
    }

    public void close()
    {
        if (null != browser)
        {
            browser.close();
            browser.quit();
            browser = null;
        }
    }

    public Browser doCreateBrowser()
    {
        switch (configuration.getBrowserType())
        {

            case FIREFOX:

                FirefoxProfile profile = new FirefoxProfile();
                profile.setAcceptUntrustedCertificates(true);

                final int marionettePort = PortProber.findFreePort();
                final int webDriverPort = PortProber.findFreePort();
                GeckoDriverService.Builder builder = new GeckoDriverService.Builder()
                {
                    @Override
                    protected ImmutableList<String> createArgs()
                    {
                        ImmutableList.Builder<String> argsBuilder = ImmutableList.builder();
                        argsBuilder.addAll(super.createArgs());
                        argsBuilder.add(String.format("--marionette-port=%d", marionettePort));
                        return argsBuilder.build();
                    }
                };
                builder.usingPort(webDriverPort);
                GeckoDriverService driverService = builder.build();
                try
                {
                    driverService.start();
                }
                catch (IOException e)
                {
                    throw new IllegalStateException("Could not start the GeckoDriverService", e);
                }
                webDriver = new MarionetteDriver(driverService);

                webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                webDriver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
                webDriver.manage().timeouts().setScriptTimeout(90, TimeUnit.SECONDS);

                webDriver.manage().window().maximize();

                break;


            default:
            case CHROME:
                capabilities = DesiredCapabilities.chrome();

                capabilities.setCapability("enable-restore-session-state", true);
                capabilities.setJavascriptEnabled(true);
                capabilities.setBrowserName("chrome");
                capabilities.setPlatform(Platform.ANY);

                ChromeOptions options = new ChromeOptions();
                options.addArguments(Arrays.asList("--start-maximized", "allow-running-insecure-content", "ignore-certificate-errors", "--disable-gpu"));


                capabilities.setCapability(ChromeOptions.CAPABILITY, options);

                LoggingPreferences logPrefs = new LoggingPreferences();
                logPrefs.enable(LogType.BROWSER, Level.ALL);
                capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

                ExecutorService executor = Executors.newSingleThreadExecutor();
                Future<String> future = executor.submit(new Callable<String>()
                {
                    @Override
                    public String call() throws Exception
                    {
                        webDriver = new EventFiringWebDriver(new ChromeDriver(capabilities));
                        return "Driver created";
                    }
                });

                try
                {
                    logger.info(future.get(1, TimeUnit.MINUTES)); //timeout is in a minutes
                }
                catch (TimeoutException e)
                {
                    logger.error("WebDriver does not created within 1 Minutes");
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                catch (ExecutionException e)
                {
                    e.printStackTrace();
                }
                executor.shutdownNow();


                webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                webDriver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
                webDriver.manage().timeouts().setScriptTimeout(90, TimeUnit.SECONDS);

                break;
        }

        try
        {
            int retryCounter = 0;
            do
            {
                webDriver.get(UrlFactory.BASE_URL.pageUrl);
                Thread.sleep(DEFAULT_SLEEP);

                logger.info("Driver Starts : " + webDriver.getCurrentUrl());
                retryCounter++;
            }
            while (!webDriver.getCurrentUrl().contains(UrlFactory.BASE_URL.pageUrl) && retryCounter < 5);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        browser = new Browser(this, webDriver);

        return browser;
    }


    //--

    public Browser getBrowser()
    {
        return browser;
    }

    public void setBrowser(Browser browser)
    {
        this.browser = browser;
    }

    public WebDriver getWebDriver()
    {
        return webDriver;
    }

    public void setWebDriver(WebDriver webDriver)
    {
        this.webDriver = webDriver;
    }

    public DesiredCapabilities getCapabilities()
    {
        return capabilities;
    }

    public void setCapabilities(DesiredCapabilities capabilities)
    {
        this.capabilities = capabilities;
    }

    public Configuration getConfiguration()
    {
        return configuration;
    }

    public void setConfiguration(Configuration configuration)
    {
        this.configuration = configuration;
    }
}
