package com.mizhbio.aligner.chunk.strategy.impl;

import com.mizhbio.aligner.chunk.Chunk;
import com.mizhbio.aligner.chunk.factory.ChunkFactory;
import com.mizhbio.aligner.chunk.factory.DefaultChunkFactory;
import com.mizhbio.aligner.chunk.strategy.DNAChunkingStrategy;

import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class LengthChunkingStrategy implements DNAChunkingStrategy {
    private int chunkSize;

    public LengthChunkingStrategy(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    @Override
    public String chunk(String sequence, int chunkIndex) {
        int start = chunkIndex * chunkSize;
        int end = Math.min(start + chunkSize, sequence.length());
        return sequence.substring(start, end);
    }
}
