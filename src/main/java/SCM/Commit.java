package scm;

import java.util.ArrayList;

public class Commit {

    private final int id;
    private final String message;
    private ArrayList<String> files;

    public Commit(int id, String message, ArrayList<String> files) {
        this.id = id;
        this.message = message;
        this.files = files;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<String> getFiles() {
        return files;
    }

}
