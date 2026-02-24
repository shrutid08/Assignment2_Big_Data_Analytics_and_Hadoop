package com.example.controller;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class DocumentSimilarityReducer
        extends Reducer<Text, Text, Text, Text> {

    private static Map<String, Set<String>> documentWordMap = new HashMap<>();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        Set<String> words = new HashSet<>();

        for (Text val : values) {
            words.add(val.toString());
        }

        documentWordMap.put(key.toString(), words);
    }

    @Override
    protected void cleanup(Context context)
            throws IOException, InterruptedException {

        List<String> documents = new ArrayList<>(documentWordMap.keySet());

        for (int i = 0; i < documents.size(); i++) {
            for (int j = i + 1; j < documents.size(); j++) {

                String doc1 = documents.get(i);
                String doc2 = documents.get(j);

                Set<String> set1 = documentWordMap.get(doc1);
                Set<String> set2 = documentWordMap.get(doc2);

                Set<String> intersection = new HashSet<>(set1);
                intersection.retainAll(set2);

                Set<String> union = new HashSet<>(set1);
                union.addAll(set2);

                double similarity = 0.0;
                if (!union.isEmpty()) {
                    similarity = (double) intersection.size() / union.size();
                }

                context.write(
                        new Text(doc1 + "," + doc2),
                        new Text(String.valueOf(similarity))
                );
            }
        }
    }
}