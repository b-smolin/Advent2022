
import java.util.HashMap;

public class TreeNode {
    HashMap<String, TreeNode> children;
    int size;
    String name;

    public TreeNode(int size, String name){
        children = new HashMap<>();
        this.size = size;
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void addChild(TreeNode child){
        children.put(child.name, child);
    }

    public String toString(){
        String view = "name: " + name + " size: " + size + "\n";
        for(TreeNode child: children.values()){
            view += "  " + child.toString();
        }
        return view;
    }

}
