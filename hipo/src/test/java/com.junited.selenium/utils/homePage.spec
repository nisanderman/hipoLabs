@objects
	header				                css     .header
	sahibinden-logo                     css     .logo

@set
    categoryItemsHeight  ~ 18px
    categoryItemsWidth   ~ 224px

= Home Page =
    @on desktop
        homepage-content:
            width 838px
            right-of aside 16px
        adv-on-left:
            width 522px
            height 250px
            inside adv-banner
            below header 16px
            right-of nav 16px
        adv-on-right:
            width 300px
            height 250px
            inside adv-banner
            below header 16px
            right-of adv-on-left 16px
        adv-banner:
            width 838px
            height 250px
            inside homepage-content
            below header 16px
            right-of nav 16px
        adv-center:
            width 838px
            height 90px
            inside homepage-content
            below showcases-1 16px
            right-of categories-left-menu 16px
        header:
            width 1115px
            height 58px
            inside screen 0px top
            color-scheme    0% white, 0% black,  65%   #3f475f
        sahibinden-logo:
            width 160px
            height 34px
            inside header 12px top bottom
            left-of header  -160px
            text is "sahibinden.com anasayfasına dön"
            color-scheme    0% white, 11% black,  53% #ffe803
    	search-container:
            width 395px
            height 47px
            inside header 443px right, 277px left, 0px top, 11px bottom
            right-of sahibinden-logo 117px
            left-of login-text 163px
    	search-text:
            width 290px
            height 36px
    	    inside search-container 105px right, 0px left, 11px top, 0px bottom
    	    left-of search-button -38px
        search-button:
            width 38px
            height 36px
            inside search-container 105px right, 252px left, 11px top, 0px bottom
            right-of search-text -38px
        search-clear:
            width 81px
            height 15px
            inside search-container 0px right, 314px left, 22px top, 10px bottom
            right-of search-button 24px
            text is "Detaylı Arama"
        login-text:
            width 68px
            height 23px
            left-of register-text -1 to 0px
            inside header 18px top, 17px bottom
            text is "Giriş Yap"
        register-text:
            width 55px
            height 23px
            right-of login-text -1 to 0px
            inside header 18px top, 17px bottom
            text is "Üye Ol"
        free-classified:
            width 146px
            height 34px
            inside header 0px right, 12px top bottom
            text is "Ücretsiz* İlan Ver"
            color-scheme    2% white, 0% black,  77 to 78% #489af1
        homepage:
            width 1115px
        content:
            width 1115px
            inside homepage 0px top, 0px bottom,
        aside:
            width 261px
        nav:
            width 261px
            inside aside 0px top, 269px bottom, 0px left, 0px right
            css font-size is "13px"
            css font-family contains "Lucida Grande"
            css font-family ends "sans-serif"
            css font-family contains "Arial"
            css font-family matches ".*Arial.*"

        special-categories:
            width 261px
            height 234px
            inside nav 0px top, 2313px bottom, 0px left, 0px right

        #special category items
        @forEach [special-categories-items-*] as itemName, next as nextItem
            ${itemName}:
                above ${nextItem} 0px
                width 226px
                height 28 to 29px
                inside nav 35px left, 0px right

        bottom-categories:
            width 261px
            height 66px
            inside nav 244px top, 2237px bottom, 0px left, 0px right
            below special-categories 10px
            aligned vertically left categories-left-menu

        #bottom category items
        @forEach [bottom-categories-items-*] as itemName, next as nextItem
            ${itemName}:
                above ${nextItem} 0px
                width 226px
                height 28 to 29px
                inside bottom-categories 0px top, 38px bottom, 35px left, 0px right

        categories-left-menu:
            width 261px
            inside nav 317px top, 0px bottom, 0px left, 0px right
            below bottom-categories 7px
            aligned vertically left special-categories

        #category left menu items
        @for [1 - 9] as index
            categories-left-menu-items-${index}:
                above categories-left-menu-items-${index + 1} 7px
                width 261px

        real-estate-category:
            width 261px
            height 203px
            inside categories-left-menu 0px top, 2027px bottom, 0px left, 0px right
            below bottom-categories 7px
            aligned vertically left special-categories

        #real estate category items
        @forEach [real-estate-category-items-*] as itemName, next as nextItem
            ${itemName}:
                above ${nextItem} 5px
                width ${categoryItemsWidth}
                height ${categoryItemsHeight}

        vehicle-category:
             width 261px
             height 250px
             inside categories-left-menu 210px top, 1770px bottom, 0px left, 0px right
             below real-estate-category 7px
             aligned vertically left special-categories

        #vehicle category items
        @forEach [vehicle-category-items-*] as itemName, next as nextItem
            ${itemName}:
                above ${nextItem} 5px
                width ${categoryItemsWidth}
                height ${categoryItemsHeight}

        spare-parts-category:
             width 261px
             height 129px
             inside categories-left-menu 467px top, 1634px bottom, 0px left, 0px right
             below vehicle-category 7px
             aligned vertically left special-categories

        #spare parts category items
        @forEach [spare-parts-category-items-*] as itemName, next as nextItem
            ${itemName}:
                above ${nextItem} 5px
                width ${categoryItemsWidth}
                height ${categoryItemsHeight}

        shopping-category:
             width 261px
             height 615px
             inside categories-left-menu 603px top, 1012px bottom, 0px left, 0px right
             below spare-parts-category 7px
             aligned vertically left special-categories

        #shopping category items
        @forEach [shopping-category-items-*] as itemName, next as nextItem
            ${itemName}:
                above ${nextItem} 5px
                width ${categoryItemsWidth}
                height ${categoryItemsHeight}

        industry-category:
             width 261px
             height 135px
             inside categories-left-menu 1225px top, 870px bottom, 0px left, 0px right
             below shopping-category 7px
             aligned vertically left special-categories

        #industry category items
        @forEach [industry-category-items-*] as itemName, next as nextItem
            ${itemName}:
                above ${nextItem} 5px
                width ${categoryItemsWidth}
                height ${categoryItemsHeight}

        services-category:
             width 261px
             height 132px
             inside categories-left-menu 1367px top, 731px bottom, 0px left, 0px right
             below industry-category 7px
             aligned vertically left special-categories

        #services category items
        @forEach [services-category-items-*] as itemName, next as nextItem
            ${itemName}:
                above ${nextItem} 5px
                width ${categoryItemsWidth}
                height ${categoryItemsHeight}

        teaching-staff-category:
             width 261px
             height 135px
             inside categories-left-menu 1506px top, 589px bottom, 0px left, 0px right
             below services-category 7px
             aligned vertically left special-categories

        #teaching staff category items
        @forEach [teaching-staff-category-items-*] as itemName, next as nextItem
            ${itemName}:
                above ${nextItem} 5px
                width ${categoryItemsWidth}
                height ${categoryItemsHeight}

        career-category:
             width 261px
             height 204px
             inside categories-left-menu 1648px top, 378px bottom, 0px left, 0px right
             below teaching-staff-category 7px
             aligned vertically left special-categories

        #career category items
        @forEach [career-category-items-*] as itemName, next as nextItem
            ${itemName}:
                above ${nextItem} 5px
                width ${categoryItemsWidth}
                height 18 to 35 px

        deputy-seekers-category:
             width 261px
             height 129px
             inside categories-left-menu 1859px top, 242px bottom, 0px left, 0px right
             below career-category 7px
             aligned vertically left special-categories

        #deputy seekers category items
        @forEach [deputy-seekers-category-items-*] as itemName, next as nextItem
            ${itemName}:
                above ${nextItem} 5px
                width ${categoryItemsWidth}
                height ${categoryItemsHeight}

        pets-category:
             width 261px
             height 235px
             inside categories-left-menu 1995px top, 0px bottom, 0px left, 0px right
             below deputy-seekers-category 7px
             aligned vertically left special-categories

        #pets category items
        @forEach [pets-category-items-*] as itemName, next as nextItem
            ${itemName}:
                above ${nextItem} 5px
                width ${categoryItemsWidth}
                height ${categoryItemsHeight}

        picks-category-banner:
            width 261px
            height 24px
            below pets-category 19px
            right-of screen -1150px
            inside picks-category 0px top, 93px bottom, 0px left, 0px right
            aligned vertically left categories-left-menu
        picks-category:
             width 261px
             height 117px
             inside aside 2566px top, 149px bottom, 0px left, 0px right
             below pets-category 19px
             aligned vertically left categories-left-menu

        #picks category items
        @forEach [picks-category-items-*] as itemName, next as nextItem
            ${itemName}:
                above ${nextItem} 0px
                width 226px
                height 28px

        news-category-banner:
            width 261px
            height 24px
            below picks-category 18px
            right-of screen -1150px
            inside news-category 0px top, 91px bottom, 0px left, 0px right
        news-category:
            width 261px
            height 115 to 131px
            inside aside 2701px top, 0px bottom, 0px left, 0px right
            below picks-category 18px
            aligned vertically left categories-left-menu

        #news category items
        @forEach [news-category-items-*] as itemName, next as nextItem
            ${itemName}:
                above ${nextItem} 9px
                width 241px
                height 16 to 48 px

        #homepage vitrin
        homepage-vitrin-banner:
            width 838px
            height 24px
            below adv-banner 13px
            right-of special-categories 16px
        showcases-1:
            width 838px
            height 881px
            aligned vertically left estate-projects
            below homepage-vitrin-banner
            inside homepage-content 287px top, 1430px bottom, 0px left, 0px right
        estate-projects:
            width 838px
            height 251px
            inside homepage-content 1320px top, 1027px bottom, 0px left, 0px right
            right-of categories-left-menu 16px
        daily-opportunity-slide:
            width 838px
            height 281px
            inside homepage-content 1591px top, 726px bottom, 0px left, 0px right
            above interestingAds
            right-of categories-left-menu 16px
        interestingAds:
            width 838px
            height 137 to 138px
            inside homepage-content 1894px top, 567px bottom, 0px left, 0px right
            below daily-opportunity-slide
            right-of categories-left-menu 16px
        popular-products:
            width 838px
            height 185px
            inside homepage-content 2050px top, 363px bottom, 0px left, 0px right
            below interestingAds
            right-of categories-left-menu 16px

        #uiBox list
        @forEach [uiBox-*] as itemName, prev as previousItem
            ${itemName}:
                below ${previousItem}
                width 838px

        #showcase-banners
        @forEach [showcase-banners-*] as itemName, next as nextItem
            ${itemName}:
                width 838px
                height 24px
                inside homepage-content
                aligned vertically left estate-projects
                right-of categories-left-menu 16px

        #showcases
        @forEach [showcases-*] as itemName, next as nextItem
            ${itemName}:
                width 838px
                inside homepage-content
                aligned vertically left estate-projects
                right-of screen -873px
                css font-size is "12px"
                css font-family contains "Lucida Grande"
                css font-family ends "sans-serif"
                css font-family contains "Arial"
                css font-family matches ".*Arial.*"

        #showcase classifieds
        @for [1 - 6] as index
            showcase-classifieds-${index}:
                left-of showcase-classifieds-${index + 1} 16px
                width 106px
                height 80px
                inside homepage-content

        @forEach [estate-projects-items-*] as itemName, next as nextItem
            ${itemName}:
                width 411 to 427px
                height 251px
                inside homepage-content

        daily-opportunity-slide-banner:
            width 838px
            height 24px
            inside daily-opportunity-slide 0px top, 257px bottom, 0px left, 0px right
            below estate-projects 20px
            right-of categories-left-menu 16px

        interestingAds-banner:
            width 838px
            height 24px
            inside interestingAds 0px top, 113 to 114px bottom, 0px left, 0px right
            below daily-opportunity-slide
            right-of categories-left-menu 16px

        #interestingAds items
        @forEach [interestingAds-items-*] as itemName, prev as previousItem
            ${itemName}:
                width 106px
                height 97px
                below interestingAds-banner 16px
                inside interestingAds
                aligned horizontally all ${nextItem}  614px

        #popular-links container list
        @forEach [popular-links-*] as itemName, prev as previousItem
            ${itemName}:
                width 838px
                height 70 to 80px
                below ${previousItem}
                inside homepage-content
                right-of categories-left-menu 16px

        popular-products-banner:
            width 838px
            height 24px
            inside popular-products 0px top, 161px bottom, 0px left, 0px right
            below interestingAds 18 to 19px
            right-of categories-left-menu 16px

        #popularproducts items
        @forEach [popular-products-items-*] as itemName, prev as previousItem
            ${itemName}:
                width 167px
                height 150px
                below popular-products-banner
                inside popular-products
                right-of categories-left-menu 16px

        popular-search:
            width 838px
            height 104px
            inside homepage-content 2235px top, 259px bottom, 0px left, 0px right
            below adv-banner    1985px
            right-of categories-left-menu   16px
        most-search-services:
            width 838px
            height 104px
            inside homepage-content 2356px top, 138px bottom, 0px left, 0px right
            below popular-search          17px
            right-of categories-left-menu 16px
        most-search-career:
            width 838px
            height 104px
            inside homepage-content 2477px top, 17px bottom, 0px left, 0px right
            below most-search-services      17px
            right-of categories-left-menu   16px

        footerContainer:
            width 100 % of screen/width
            height 415px
            below aside 38px
        footer:
            width 1115px
            height 413px
        footer-menu:
            width 1115px
            height 191px
            inside footer 28px top, 194px bottom, 0px left, 0px right

        #footer menu list
        @forEach [footer-menu-*] as itemName, prev as previousItem
            ${itemName}:
                width 130 to 235px
                height 133 to 185px
                inside footer-menu 0px top, 6px bottom
                css font-size is "12px"
                css font-family contains "Lucida Grande"
                css font-family ends "sans-serif"
                css font-family contains "Arial"
                css font-family matches ".*Arial.*"

        contact-menu:
            width 1115px
            inside footer 28px top, 385px bottom, 0px left, 0px right

        #contact menu list
        @forEach [contact-menu-*] as itemName, prev as previousItem
            ${itemName}:
                width 192 to 202px
                height 30 to 32px
                inside footer
                below footer-menu

        warning-text:
             width 1115px
             height 65px
             inside footer 299px top, 49px bottom, 0px left, 0px right
             below footer-menu