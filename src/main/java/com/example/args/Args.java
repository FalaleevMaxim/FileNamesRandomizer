package com.example.args;

import java.util.HashSet;
import java.util.Set;

public class Args {
    private String path;
    private String pre;
    private Set<String> randFlags = new HashSet<>();
    private Set<String> fileTypes = new HashSet<>();
    private boolean recursive;
    private Integer length;

    public Args setPath(String path) {
        this.path = path;
        return this;
    }

    public Args setRandFlags(Set<String> randFlags) {
        this.randFlags = randFlags;
        return this;
    }

    public Args addRandFlag(String randFlag) {
        this.randFlags.add(randFlag);
        return this;
    }

    public Args setFileTypes(Set<String> fileTypes) {
        this.fileTypes = fileTypes;
        return this;
    }

    public Args addFileTypes(String fileType) {
        this.fileTypes.add(fileType);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Set<String> getRandFlags() {
        return randFlags;
    }

    public Set<String> getFileTypes() {
        return fileTypes;
    }

    public boolean isRecursive() {
        return recursive;
    }

    public Args setRecursive(boolean recursive) {
        this.recursive = recursive;
        return this;
    }

    public Integer getLength() {
        return length;
    }

    public Args setLength(Integer length) {
        this.length = length;
        return this;
    }

    public String getPre() {
        return pre;
    }

    public Args setPre(String pre) {
        this.pre = pre;
        return this;
    }
}
