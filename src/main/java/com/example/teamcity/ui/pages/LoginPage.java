package com.example.teamcity.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByAttribute;
import com.example.teamcity.api.models.User;
import com.example.teamcity.ui.Selectors;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.element;

@Getter
public class LoginPage extends Page{
    private static final String LOGIN_PAGE_URL = "/login.html";
    private SelenideElement loginFormButton = element(Selectors.byId("loginPasswordSwitch"));
    private SelenideElement usernameInput = element(Selectors.byId("username"));
    private SelenideElement passwordInput = element(Selectors.byId("password"));
    public LoginPage open() {
        Selenide.open("/login.html");
        return this;
    }

    public void login (User user) {
        loginFormButton.click();
        usernameInput.sendKeys(user.getUsername());
        passwordInput.sendKeys(user.getPassword());
        submit();
        submit();
        waitUntilDataIsSaved();
    }

}
