import account.Developer;
import backlog.BacklogItem;

public class demo {

    public static void main(String[] args) {
//        composite state pattern
        demoState();

    }

    public void demoComposite(){

    }
    public static void demoState(){
        BacklogItem backlogItem = new BacklogItem("test item",1,2,3);
        Developer developer = new Developer("testDev", 1,"test@mial.com","01111111","tester45" );
        System.out.println("[+] created  new backlogItem"+ backlogItem.getDescription());
        System.out.println("[+] status backlogitem"+ backlogItem.getState().toString());
        System.out.println("[+] trying to change status to ready for testing (should not work!)");
        try {
            backlogItem.getState().changeToReadyForTestingState(developer);
        } catch (Exception e){
            System.out.println("[+] this was not allowed in the current state");
        }
        try{
            backlogItem.getState().changeToDoingState();
        } catch (Exception e){
            System.out.println("[+] this should not get called!");
        }
        System.out.println("[+] changed to doing state");
        System.out.println(backlogItem.getState().toString());
        System.out.println("[+] lets try to change the state again!");
        try{
            backlogItem.getState().changeToReadyForTestingState(developer);

        }catch (Exception e){
            System.out.println("[+] This execption should not be thrown");
        }
        System.out.println("[+] The state of the backlogitem should be ready for testing state");
        System.out.println(backlogItem.getState().toString());
    }
}
