package com.junited.selenium.pages;

import com.junited.selenium.utils.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindAll;

import java.util.List;


public class HipoPage extends PageObject
{
    public HipoPage(Browser browser)
    {
        super(browser);
    }

    @FindBy(xpath = "//div[@class= 'b0KoTc']/span[@class = 'Q8LRLc']")
    public WebElement googleTurkey;

    @FindBy(xpath = "//input[@title = 'Ara']")
    public WebElement searchInput;

    @FindBy(xpath = "//div[@jsname='VlcLAe']/center/input[@name='btnK']")
    public WebElement jsSubmitButton;

    @FindBy(xpath = "//cite[text()='https://hipolabs.com/']")
    public WebElement hipoLabslink;

    @FindBy(xpath = "//cite[text()='https://hipolabs.com/']/preceding::h3")
    public WebElement hipoLabsSiteLink;

    @FindBy(xpath = "//li[@id = 'menuMaximizedButtonTeam']/a[@href = '/team/']")
    public WebElement teamButton;

    @FindBy(xpath = "//div[text() = 'APPLY FOR INTERNSHIP']")
    public WebElement applyInternshipButton;

    @FindBy(css = ".enter-to-send>textarea")
    public WebElement textArea;

    @FindBy(xpath = "//span[text()= 'Send Message']")
    public WebElement sendMessageButton;

    @FindBy(css = ".flex-center")
    public WebElement closeMessageBox;

    @FindBy(xpath = "//a[text()='WORK']")
    public WebElement works;

    @FindBy(xpath = "//div[@class='pageWorkContentLeftAction']")
    public List<WebElement> pageWorkContentLeftAction;

}
