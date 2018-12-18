package com.junited.selenium.tests;

import com.junited.selenium.pages.HipoPage;
import com.junited.selenium.utils.UrlFactory;
import com.sun.org.glassfish.gmbal.Description;
import org.junit.Assert;
import org.junit.Test;

public class HipoTest extends AbstractSeleniumTest
{
    private HipoPage hipoPage;

    @Description("Question 1")
    @Test
    public void testGoHipoLabs()
    {
        hipoPage = new HipoPage(browser);
        //Verifying it’s Google TR
        Assert.assertEquals(hipoPage.googleTurkey.getText(),"Türkiye");
        //Search for “Hipo Labs”
        browser.waitAndSendKeys(hipoPage.searchInput, "Hipo Labs");
        browser.waitAndClick(hipoPage.jsSubmitButton);
        //Verify hipolabs.com is listed.
        Assert.assertTrue(hipoPage.hipoLabslink.isDisplayed());
        //Open the website
        browser.waitAndClick(hipoPage.hipoLabsSiteLink);
        Assert.assertEquals(browser.getCurrentUrl(), UrlFactory.HIPO_URL.pageUrl);
        //Open Team menu
        browser.waitAndClick(hipoPage.teamButton);
        browser.scrollToElement(hipoPage.applyInternshipButton);
        sleep(1000);
        Assert.assertTrue(hipoPage.applyInternshipButton.isDisplayed());
        //Verify that page has “APPLY for INTERNSHIP” text.
        Assert.assertTrue(isTextDisplayedOnPage("APPLY FOR INTERNSHIP"));
        //Take a Screenshot
        saveScreenShot("hipo");
    }
    @Description("Question 2-checking ask a question works")
    @Test
    public void testHipoLabsTextArea()
    {
        hipoPage = new HipoPage(browser);
        browser.get(UrlFactory.HIPO_URL);

        Assert.assertTrue(hipoPage.textArea.isDisplayed());
        browser.waitAndSendKeys(hipoPage.textArea, "Test");
        browser.waitAndClick(hipoPage.sendMessageButton);
    }

    @Description("Question 2-close ask a question part")
    @Test
    public void testHipoLabsCloseTextArea()
    {
        hipoPage = new HipoPage(browser);
        browser.get(UrlFactory.HIPO_URL);

        Assert.assertTrue(hipoPage.textArea.isDisplayed());
        browser.waitAndClick(hipoPage.closeMessageBox);
    }

    @Description("Question 2- Checking works")
    @Test
    public void testHipoLabsWorks()
    {
        hipoPage = new HipoPage(browser);
        browser.get(UrlFactory.HIPO_URL);

        browser.waitAndClick(hipoPage.works);
        Assert.assertTrue(browser.getCurrentUrl().contains("work"));

        sleep(2000);
        //Assert.assertTrue(hipoPage.pageWorkContentLeftAction.get(0).isEnabled());
        hipoPage.pageWorkContentLeftAction.forEach(element -> Assert.assertTrue(element.isEnabled()));
    }

}
