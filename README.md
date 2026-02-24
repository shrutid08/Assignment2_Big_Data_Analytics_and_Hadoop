# Document Similarity using Hadoop MapReduce

This project implements a Hadoop MapReduce pipeline in Java to compute similarity between text documents.  
It demonstrates distributed data processing concepts using Hadoop, Docker, and Maven.

---

## Project Overview

The goal of this project is to:

- Process multiple text documents using Hadoop MapReduce  
- Extract words from each document  
- Compute similarity between document pairs  
- Generate similarity scores as output  

This simulates how large-scale document analytics can be performed in distributed systems.

---

## Tech Stack

- **Java**
- **Hadoop MapReduce**
- **Docker (Hadoop cluster setup)**
- **Maven (build tool)**

---

## Project Structure

```
Assignment2-Document-Similarity-usingg-MapReduce-main/
│
├── src/main/java/com/example/controller/
│   ├── DocumentSimilarityDriver.java
│   ├── DocumentSimilarityMapper.java
│   └── DocumentSimilarityReducer.java
│
├── docs/        # Input documents
├── output/      # MapReduce results
├── pom.xml
├── docker-compose.yml
└── hadoop.env
```

---

## How to Run the Project

### Build the Project

```bash
mvn clean package
```

This generates:

```
target/DocumentSimilarity-0.0.1-SNAPSHOT.jar
```

---

### Start Hadoop Cluster (Docker)

```bash
docker-compose up -d
```

Make sure Docker Desktop is running.

---

### Create Sample Documents

Example:

```bash
mkdir docs
echo "spark hadoop big data analytics" > docs/doc1.txt
echo "hadoop mapreduce big data processing" > docs/doc2.txt
echo "music streaming analytics spark data" > docs/doc3.txt
```

---

### Copy Files to Hadoop Container

```bash
docker cp docs namenode:/tmp/
docker cp target/DocumentSimilarity-0.0.1-SNAPSHOT.jar namenode:/tmp/
```

---

### Upload Documents to HDFS

```bash
docker exec -it namenode bash
hdfs dfs -mkdir -p /input
hdfs dfs -put /tmp/docs/* /input/
```

---

### Run MapReduce Job

```bash
hadoop jar /tmp/DocumentSimilarity-0.0.1-SNAPSHOT.jar \
com.example.controller.DocumentSimilarityDriver \
/input /output
```

---

### View Output

```bash
hdfs dfs -cat /output/part-r-00000
```

---

### Copy Output Back to Local Project (Optional)

```bash
docker exec namenode hdfs dfs -get /output /tmp/output
docker cp namenode:/tmp/output ./output
```

---

## Example Output

```
doc1.txt,doc2.txt    0.50
doc1.txt,doc3.txt    0.40
doc2.txt,doc3.txt    0.30
```

These values represent similarity scores between document pairs.

---

## Learning Outcomes

- Understanding Hadoop MapReduce workflow  
- Distributed document processing  
- Docker-based Hadoop setup  
- Building and executing MapReduce jobs with Maven  

