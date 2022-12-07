import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;

public class Node {
    private File folder;
    private ArrayList<Node> children;
    private long size;
    private int level;
    private long limit;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
//        String size = SizeCalculator.getHumanReadableSize(getSize());
        StringBuilder builder = new StringBuilder();
        String size = SizeCalculator.getHumanReadableSize(getSize());
        builder.append(folder.getName() + " - " + size + "\n");
        for (Node child : children){
            if (child.getSize() < limit){
                continue;
            }
            builder.append("  ".repeat(child.getLevel()) + child);
        }

        return builder.toString();
    }

    public Node(File folder, long limit) {
        this.folder = folder;
        this.limit = limit;
        children = new ArrayList<>();
    }

    public File getFolder() {
        return folder;
    }

    public long getLimit() {
        return limit;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    private void setLevel(int level) {
        this.level = level;
    }

    public void addChild(Node node){
        node.setLevel(level + 1);
        children.add(node);
    }


}
