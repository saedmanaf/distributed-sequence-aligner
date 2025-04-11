# distributed-sequence-aligner

⚡️ *A modular, parallel DNA alignment system built in Java using design patterns and biological-aware chunking.*

---

## 🚀 Overview

**distributed-sequence-aligner** is a DNA sequence alignment tool designed to be modular, scalable, and biologically aware. It supports:

- Customizable **chunking strategies** (e.g., fixed-size, codon-aware, GC-content-aware)
- Use of the **Strategy**, **Factory**, **Command**, **Iterator**, and **Adapter** design patterns
- **Parallel alignment** using multithreading
- Plug-and-play support for external tools like **BioJava**
- Aggregated alignment output

---

## 💐 Architecture

| Component        | Description |
|------------------|-------------|
| `Chunk`          | Represents a piece of the DNA sequence with metadata (id, index, etc.) |
| `DNAChunkingStrategy` | Interface for different chunking strategies |
| `ChunkFactory`   | Factory for creating `Chunk` instances |
| `ChunkIterator`  | Iterates through chunks (supports sequential or custom iteration logic) |
| `ChunkingCommand`| Command to execute a chunking strategy and collect results |
| `Aligner`        | Coordinates chunking and alignment (can run in parallel) |
| `AlignmentService` | Adapter over BioJava or other alignment engine |
| `FastaSequenceParser` | Adapter to read sequences from FASTA files |

---

## 🧚‍♂️ Biological-Aware Chunking

You can plug in domain-aware logic like:
- **Codon-size chunking** (3 nucleotides)
- **GC-content chunking**
- Future: exon-intron awareness, promoter regions, gene boundaries, etc.

---

## 🥵 Parallel Alignment

Alignments are executed in parallel using a thread pool. Chunk pairs are aligned independently, and results are aggregated.

---

## 📦 Example Usage

```java
DNAChunkingStrategy strategy = new LengthChunkingStrategy(30);
ChunkFactory factory = new DefaultChunkFactory();
ChunkingCommand command = new StrategyChunkingCommand(30, strategy, factory);

Aligner aligner = new Aligner(command, new BioJavaAlignmentService());

AlignmentResult result = aligner.chunkAndAlign(sequence1, sequence2);
System.out.println(result);
```

---

## 📊 Testing

Unit and integration tests cover:
- Chunking logic
- Strategy behavior
- Iterator correctness
- Factory generation
- Alignment accuracy
- Thread safety and aggregation

---

## 📁 Project Structure

```
com.mizhbio.aligner
├── alignment/
│   ├── Aligner.java
│   ├── AlignmentService.java
├── chunk/
│   ├── Chunk.java
│   ├── ChunkFactory.java
│   ├── ChunkingCommand.java
│   ├── ChunkIterator.java
├── strategy/
│   ├── DNAChunkingStrategy.java
│   ├── LengthChunkingStrategy.java
├── adapter/
│   ├── BioJavaAlignmentService.java
│   ├── FastaSequenceParser.java
├── parallel/
    └── ParallelAligner.java
```

---

## 🚣 Roadmap

- [ ] GC-content-based chunking strategy
- [ ] Codon-aware alignment scoring
- [ ] Full genome support with streaming
- [ ] Microservice orchestration for chunk/align/score
- [ ] Result visualization (Web UI or CLI)

---

## 🤝 Contributing

This is a learning-driven, open-source project. Feel free to fork and build features like:

- New chunking strategies
- Different alignment algorithms
- Distributed versions (e.g., Kafka or Akka support)

---

## 📄 License

MIT License. You're free to use, modify, and share.

---

## 🌱 Author

Crafted with ❤️ and code by @saedmanaf.

