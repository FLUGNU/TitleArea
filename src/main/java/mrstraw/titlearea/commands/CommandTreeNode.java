package mrstraw.titlearea.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandTreeNode {

    private String data;
    private CommandTreeNode parent;
    private List<CommandTreeNode> children;

    public CommandTreeNode(String data, CommandTreeNode parent, List<CommandTreeNode> children) {
        this.data = data;
        this.parent = parent;
        this.children = children;
    }

    public CommandTreeNode(String data, CommandTreeNode parent) {
        this.data = data;
        this.parent = parent;
        this.children = null;
    }

    public CommandTreeNode(String data) {
        this.data = data;
        this.parent = null;
        this.children = null;
    }

    public CommandTreeNode addChild(String child) {
        CommandTreeNode childNode = new CommandTreeNode(child, this);
        if (this.getChildren()==null){
            this.children = new ArrayList<CommandTreeNode>();
        }
        this.children.add(childNode);
        return childNode;
    }

    public void addChildren(List<String> children) {
        if (children == null){
            return;
        }
        List<CommandTreeNode> childrenNodes = new ArrayList<CommandTreeNode>();
        if (this.getChildren()==null){
            this.children = new ArrayList<CommandTreeNode>();
        }
        for (String child : children){
            CommandTreeNode childNode = new CommandTreeNode(child, this);
            this.children.add(childNode);
        }
    }

    public List<CommandTreeNode> getChildren(){
        return this.children;
    }

    public List<String> getChildrenData(){
        ArrayList<String> result = new ArrayList<>();
        if (this.getChildren() == null){
            return null;
        }
        for (CommandTreeNode child : this.getChildren()){
            result.add(child.getData());
        }
        return result;
    }

    public void resetChildren(){
        this.children = null;
    }

    public CommandTreeNode getParent(){
        return this.parent;
    }

    public String getData(){
        return this.data;
    }

    public CommandTreeNode getElement(String data) {
        CommandTreeNode root = this;
        while (root.getParent() != null){
            root = root.getParent();
        }
        return root.searchInSubTree(data);
    }

    public CommandTreeNode searchInSubTree(String data) {

        if (this.getData().equalsIgnoreCase(data)) {
            return this;
        } else {
            if (this.getChildren() != null) {
                for (CommandTreeNode child : this.getChildren()) {
                    if (child.searchInSubTree(data) != null) {
                        return child.searchInSubTree(data);
                    }
                }
            }
            return null;
        }
    }
}