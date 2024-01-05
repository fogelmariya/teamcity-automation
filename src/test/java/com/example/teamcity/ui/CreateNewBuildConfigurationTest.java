package com.example.teamcity.ui;

import com.example.teamcity.api.requests.checked.CheckedProject;
import com.example.teamcity.api.spec.Specifications;
import com.example.teamcity.ui.pages.EditPtojectPage;
import com.example.teamcity.ui.pages.favorites.ProjectsPage;
import org.testng.annotations.Test;

public class CreateNewBuildConfigurationTest extends BaseUiTest {
    @Test
    public void authorizedUserShouldBeAbleCreateNewBuildConfiguration() {
        var testData = testDataStorage.addTestData();

        loginAsUser(testData.getUser());
        new CheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(testData.getProject());

        new EditPtojectPage().open(testData.getProject().getId())
                .createBuildConfig.click();
    }
}