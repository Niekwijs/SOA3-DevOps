package forum;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreeNode {

    private TreeNode parent = null;
    private List<TreeNode> children = null;
    private Comment comment;

    public TreeNode(Comment comment) {
        this.parent = null;
        this.children = new ArrayList<>();
        this.comment = comment;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder(50);
        print(buffer, "", "");
        return buffer.toString();
    }
    private void print(StringBuilder buffer, String prefix, String childrenPrefix){
        buffer.append(prefix);
        buffer.append(comment.getText());
        buffer.append('\n');
        for (Iterator<TreeNode> it = children.iterator(); it.hasNext();){
            TreeNode next = it.next();
            if(it.hasNext()){
                next.print(buffer, childrenPrefix + "├──", childrenPrefix +"│   ");
            }else{
                next.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
        }
    }

    public void addTreeNode(TreeNode child){
        child.setParent(this.parent);
        if(!children.contains(child)){
            this.children.add(child);
        }
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

}
