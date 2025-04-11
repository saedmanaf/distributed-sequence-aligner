package com.mizhbio.aligner.chunk.strategy;

import com.mizhbio.aligner.chunk.Chunk;

import java.util.List;

public interface DNAChunkingStrategy {
    String chunk(String sequence, int chunkIndex);
}
