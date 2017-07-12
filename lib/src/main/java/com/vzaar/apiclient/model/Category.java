package com.vzaar.apiclient.model;

@SuppressWarnings("unused")
public class Category implements VzaarModel {

    private int id;
    private int accountId;
    private int userId;
    private String name;
    private int description;
    private int parentId;
    private int depth;
    private int nodeChildrenCount;
    private int treeChildrenCount;
    private int nodeVideoCount;
    private int treeVideoCount;
    private String createdAt;
    private String updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getNodeChildrenCount() {
        return nodeChildrenCount;
    }

    public void setNodeChildrenCount(int nodeChildrenCount) {
        this.nodeChildrenCount = nodeChildrenCount;
    }

    public int getTreeChildrenCount() {
        return treeChildrenCount;
    }

    public void setTreeChildrenCount(int treeChildrenCount) {
        this.treeChildrenCount = treeChildrenCount;
    }

    public int getNodeVideoCount() {
        return nodeVideoCount;
    }

    public void setNodeVideoCount(int nodeVideoCount) {
        this.nodeVideoCount = nodeVideoCount;
    }

    public int getTreeVideoCount() {
        return treeVideoCount;
    }

    public void setTreeVideoCount(int treeVideoCount) {
        this.treeVideoCount = treeVideoCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
