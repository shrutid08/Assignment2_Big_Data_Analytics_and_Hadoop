package com.example.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class DocumentSimilarityMapper
        extends Mapper<Object, Text, Text, Text> {

    private Text documentName = new Text();
    private Text wordText = new Text();

    @Override
    protected void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {

        FileSplit fileSplit = (FileSplit) context.getInputSplit();
        String fileName = fileSplit.getPath().getName();

        documentName.set(fileName);

        String line = value.toString().toLowerCase();
        String[] words = line.split("\\W+");

        for (String word : words) {
            if (!word.isEmpty()) {
                wordText.set(word);
                context.write(documentName, wordText);
            }
        }
    }
}
