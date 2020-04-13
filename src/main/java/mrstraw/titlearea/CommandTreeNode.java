package mrstraw.titlearea;

import java.util.ArrayList;
import java.util.List;

public class CommandTreeNode<T> {

    private T data;
    private CommandTreeNode<T> parent;
    private List<CommandTreeNode<T>> children;

    public CommandTreeNode(T data, CommandTreeNode<T> parent, List<CommandTreeNode<T>> children) {
        this.data = data;
        this.parent = parent;
        this.children = children;
    }

    public CommandTreeNode(T data, CommandTreeNode<T> parent) {
        this.data = data;
        this.parent = parent;
        this.children = null;
    }

    public CommandTreeNode(T data) {
        this.data = data;
        this.parent = null;
        this.children = null;
    }

    public CommandTreeNode<T> addChild(T child) {
        CommandTreeNode<T> childNode = new CommandTreeNode<T>(child, this);
        this.children.add(childNode);
        return childNode;
    }

    public void addChildren(List<T> children) {
        List<CommandTreeNode<T>> childrenNodes = new ArrayList<CommandTreeNode<T>>();
        for (T child : children){
            CommandTreeNode<T> childNode = new CommandTreeNode<T>(child, this);
            this.children.add(childNode);
        }
    }

    public List<CommandTreeNode<T>> getChildren(){
        return this.children;
    }

    public List<T> getChildrenData(){
        ArrayList<T> result = new ArrayList<>();
        for (CommandTreeNode<T> child : this.getChildren()){
            result.add(child.getData());
        }
        return result;
    }

    public CommandTreeNode<T> getParent(){
        return this.parent;
    }

    public T getData(){
        return this.data;
    }

    public CommandTreeNode<T> getElement(T data) {
        CommandTreeNode<T> root = this;
        while (root.getParent() != null){
            root = root.getParent();
        }
        return searchInSubTree(data);
    }

    public CommandTreeNode<T> searchInSubTree(T data) {

        if (this.getData() == data) {
            return this;
        } else {
            if (this.getChildren() == null) {
                return null;
            } else {
                for (CommandTreeNode<T> child : this.getChildren()) {
                    if (child.searchInSubTree(data) != null) {
                        return child;
                    }
                }
            }
            return null;
        }
    }
}