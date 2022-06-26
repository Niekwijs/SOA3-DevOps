package project;

public class ProjectFactory {

    public IProject getProject(String type, String name){
        if(type == null){
            return null;
        }
        if (type.equalsIgnoreCase("scrum")) {
            return new ScrumProject(name);
        }
        else if (type.equalsIgnoreCase("kanban")) {
            return new KanbanProject(name);
        }
        return null;
    }

}
