package com.mycompany.sampleproject;


import java.util.ArrayList;
import java.io.*;

public class LengthFilter extends Processing_elements {

    private ArrayList<File> filteredFiles = null;
    private long length;
    private String op;

    private ArrayList<File> subFiles = null;

    public ArrayList<File> getSubFiles() {
        return subFiles;
    }

    public void setSubFiles(ArrayList<File> subFiles) {
        this.subFiles = subFiles;
    }

    public ArrayList<File> getFilteredFiles() {
        return filteredFiles;
    }

    public void setFilteredFiles(ArrayList<File> filteredFiles) {
        this.filteredFiles = filteredFiles;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    //constructor
    public LengthFilter(ArrayList<File> entries, long length, String op) {

        setLength(length);
        setOp(op);

        for (File userInput : entries) {

            if (userInput.isFile() == true) {
                filteredFiles.add(userInput);
            }

        }

    }

    //define these functions
    public void opertaions() {
        switch (op) {

            case "EQ":

                for (File subFile : filteredFiles) {

                    if (subFile.length() == length){
                        subFiles.add(subFile);
                    }

                }

                break;

            case "NEQ":
 
                for (File subFile : filteredFiles) {

                    if (subFile.length() != length){
                        subFiles.add(subFile);
                    }

                }
                break;
            case "GT":
 
                for (File subFile : filteredFiles) {

                    if (subFile.length() > length){
                        subFiles.add(subFile);
                    }

                }
                break;

            case "GTE":
 
                for (File subFile : filteredFiles) {

                    if (subFile.length() >= length){
                        subFiles.add(subFile);
                    }

                }
                break;

            case "LT":
 
                for (File subFile : filteredFiles) {

                    if (subFile.length() < length){
                        subFiles.add(subFile);
                    }

                }
                break;

            case "LTE":
 
                for (File subFile : filteredFiles) {

                    if (subFile.length() <= length){
                        subFiles.add(subFile);
                    }

                }
                break;

            default:
                System.out.println("Operator does not exist, all files outputted.");
                 for (File subFile: filteredFiles){
                        subFiles.add(subFile);
                    }
                break;

        }

    }

    public void outputs() {

        for(File printFile: subFiles){
            System.out.println(printFile.getName());
        }
    }

}