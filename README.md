# Assignment2_Big_Data_Analytics_and_Hadoop

Document similarity MapReduce job using Hadoop (Jaccard similarity on token sets).

## Input Format
Each input line must be:

```text
document_id<TAB>document_content
```

Example:

```text
doc1	hadoop mapreduce is scalable
doc2	mapreduce scales for big data
doc3	java spring boot service
```

## Build
```bash
mvn clean package
```

## Run (Hadoop)
```bash
hadoop jar target/DocumentSimilarity-0.0.1-SNAPSHOT.jar \
  com.example.controller.DocumentSimilarityDriver \
  /input/documents.txt /output/similarity
```

## Output Format
```text
docA,docB    similarity_score
```

Example:
```text
doc1,doc2    0.2857142857142857
doc1,doc3    0.0
doc2,doc3    0.0
```
