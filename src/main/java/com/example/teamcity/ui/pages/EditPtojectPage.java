package com.example.teamcity.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByAttribute;
import com.example.teamcity.ui.Selectors;
import com.example.teamcity.ui.pages.admin.CreateNewProject;

import static com.codeborne.selenide.Selenide.element;

public class EditPtojectPage extends Page {

    public SelenideElement createBuildConfig = element(new ByAttribute("partialLinkText", "createBuild"));
    public EditPtojectPage open(String projectId) {
        Selenide.open("/admin/editProject.html?projectId=" + projectId);
        waitUntilPageIsLoaded();
        return this;
    }

    public void CreateBuildConfig() {
        createBuildConfig.click();
    }
}
