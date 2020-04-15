package mrstraw.titlearea.commands;

import java.util.ArrayList;
import java.util.List;

public class TaTreeNode {

    private String data;
    private TaTreeNode parent;
    private List<TaTreeNode> children;

    public TaTreeNode(String data, TaTreeNode parent, List<TaTreeNode> children) {
        this.data = data;
        this.parent = parent;
        this.children = children;
    }

    public TaTreeNode(String data, TaTreeNode parent) {
        this.data = data;
        this.parent = parent;
        this.children = null;
    }

    public TaTreeNode(String data) {
        this.data = data;
        this.parent = null;
        this.children = null;
    }

    public TaTreeNode addChild(String child) {
        TaTreeNode childNode = new TaTreeNode(child, this);
        if (this.getChildren()==null){
            this.children = new ArrayList<TaTreeNode>();
        }
        this.children.add(childNode);
        return childNode;
    }

    public void addChildren(List<String> children) {
        if (children == null){
            return;
        }
        List<TaTreeNode> childrenNodes = new ArrayList<TaTreeNode>();
        if (this.getChildren()==null){
            this.children = new ArrayList<TaTreeNode>();
        }
        for (String child : children){
            TaTreeNode childNode = new TaTreeNode(child, this);
            this.children.add(childNode);
        }
    }

    public List<TaTreeNode> getChildren(){
        return this.children;
    }

    public List<String> getChildrenData(){
        ArrayList<String> result = new ArrayList<>();
        if (this.getChildren() == null){
            return null;
        }
        for (TaTreeNode child : this.getChildren()){
            result.add(child.getData());
        }
        return result;
    }

    public void resetChildren(){
        this.children = null;
    }

    public TaTreeNode getParent(){
        return this.parent;
    }

    public String getData(){
        return this.data;
    }

    public TaTreeNode getElement(String data) {
        TaTreeNode root = this;
        while (root.getParent() != null){
            root = root.getParent();
        }
        return root.searchInSubTree(data);
    }

    public TaTreeNode searchInSubTree(String data) {

        if (this.getData().equalsIgnoreCase(data)) {
            return this;
        } else {
            if (this.getChildren() != null) {
                for (TaTreeNode child : this.getChildren()) {
                    if (child.searchInSubTree(data) != null) {
                        return child.searchInSubTree(data);
                    }
                }
            }
            return null;
        }
    }
}