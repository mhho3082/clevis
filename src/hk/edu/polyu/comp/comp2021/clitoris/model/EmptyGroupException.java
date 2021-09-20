package hk.edu.polyu.comp.comp2021.clitoris.model;

public class EmptyGroupException extends Exception {
    private final String groupName;

    public EmptyGroupException(String name) {
        super("Group cannot be empty!");
        this.groupName = name;
    }

    public String getGroupName() {
        return this.groupName;
    }
}
