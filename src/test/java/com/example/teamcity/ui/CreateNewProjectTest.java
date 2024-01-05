package com.example.teamcity.ui;

import com.codeborne.selenide.Condition;
import com.example.teamcity.api.models.Project;
import com.example.teamcity.api.requests.unchecked.UncheckedProject;
import com.example.teamcity.api.spec.Specifications;
import com.example.teamcity.ui.pages.favorites.ProjectsPage;
import com.example.teamcity.ui.pages.admin.CreateNewProject;
import org.testng.annotations.Test;

import static com.example.teamcity.ui.pages.admin.CreateNewProject.projectNameError;


public class CreateNewProjectTest extends BaseUiTest {
    private static final String URL = "https://github.com/AlexPshe/spring-core-for-qa";

    @Test
    public void authorizedUserShouldBeAbleCreateNewProject() {
        var testData = testDataStorage.addTestData();

        loginAsUser(testData.getUser());
        new CreateNewProject()
                .open(testData.getProject().getParentProject().getId())
                .createProjectByUrl(URL)
                .setupProject(testData.getProject().getName(), testData.getBuildType().getName());

        new ProjectsPage().open()
                .getSubproject()
                .stream().reduce((first, second) -> second).get()
                .getHeader().shouldHave(Condition.text(testData.getProject().getName()));

        var name = new UncheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .getByName(testData.getProject().getName())
                .then().extract().as(Project.class);
        softy.assertThat(name.getName()).isEqualTo(testData.getProject().getName());

    }

    @Test
    public void authorizedUserShouldNotBeAbleCreateNewProjectWithoutName() {
        var testData = testDataStorage.addTestData();

        loginAsUser(testData.getUser());
        new CreateNewProject()
                .open(testData.getProject().getParentProject().getId())
                .createProjectByUrl(URL)
                .setupProject("", testData.getBuildType().getName());

        projectNameError.shouldBe(Condition.visible);

    }
}
