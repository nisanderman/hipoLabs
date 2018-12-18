package com.junited.selenium.utils;

public enum UrlFactory
{
    BASE_URL("https://www.google.com.tr/"),
    HIPO_URL("https://hipolabs.com/");

    //--

    public final String pageUrl;

    UrlFactory(String pageUrl)
    {
        this.pageUrl = pageUrl;
    }

    UrlFactory(UrlFactory baseUrl, String pageUrl)
    {
        this.pageUrl = baseUrl.pageUrl + pageUrl;
    }
}
