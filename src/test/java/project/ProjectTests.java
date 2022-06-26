package project;

import account.*;
import backlog.Backlog;
import sprint.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.testng.AssertJUnit.assertEquals;

public class ProjectTests {

    ProjectFactory projectFactory = new ProjectFactory();
    Backlog sprintBacklog = new Backlog();
    Account productOwner = new ProductOwner("Name", 1, "email", "06", "bestPO");
    Account scrumMaster = new ScrumMaster("Name", 1, "email", "06", "bestSM");

    @Test
    public void T_1_1_scrum_project_is_created_with_valid_data(){
        // Arrange
        ScrumProject project = (ScrumProject) projectFactory.getProject("scrum", "FirstProject");
        project.setProductOwner(productOwner);

        //Act
        Account productOwner = project.getProductOwner();
        String name = project.getName();
        int items = project.getProjectBacklog().getBacklogItems().size();

        //Assert
        assertEquals(name, "FirstProject");
        assertEquals(productOwner.getName(), "Name");
        assertEquals(items, 0);
    }

    @Test
    public void T_1_2_scrum_project_is_created_with_empty_list_of_sprints(){
        // Arrange
        ScrumProject project = (ScrumProject) projectFactory.getProject("scrum", "SecondProject");
        project.setProductOwner(productOwner);

        //Act
        int length = project.getSprints().size();

        //Assert
        assertEquals(length, 0);
    }

    @Test
    public void T_1_3_sprint_can_be_added_to_scrum_project(){
        // Arrange
        ScrumProject project = (ScrumProject) projectFactory.getProject("scrum", "ThirdProject");
        project.setProductOwner(productOwner);

        Sprint newSprint = new Sprint(SprintType.RELEASE,"Sprint 1", scrumMaster,
                productOwner, project, new Date(), new Date());

        //Act
        int initialLength = project.getSprints().size();
        project.addSprint(newSprint);
        int newLength = project.getSprints().size();

        //Assert
        assertEquals(initialLength, 0);
        assertEquals(newLength, 1);
    }

    @Test
    public void T_1_4_kanban_project_is_created_with_valid_data(){
        // Arrange
        IProject project = projectFactory.getProject("kanban", "KanbanProject");
        project.setProductOwner(productOwner);

        //Act
        Account productOwner = project.getProductOwner();
        String name = project.getName();

        //Assert
        assertEquals(name, "KanbanProject");
        assertEquals(productOwner.getName(), "Name");
    }

    @Test
    public void T_1_4_kanban_project_is_created_with_empty_backlog(){
        // Arrange
        KanbanProject project = (KanbanProject) projectFactory.getProject("kanban", "KanbanProject");
        project.setProductOwner(productOwner);

        //Act
        int length = project.getProjectBacklog().getBacklogItems().size();

        //Assert
        assertEquals(0, length);
    }

}
