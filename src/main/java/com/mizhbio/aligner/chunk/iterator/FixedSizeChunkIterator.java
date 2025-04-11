package com.mizhbio.aligner.chunk.iterator;

import com.mizhbio.aligner.chunk.strategy.DNAChunkingStrategy;

import java.util.NoSuchElementException;

public class FixedSizeChunkIterator implements ChunkIterator {
    private final String sequence;
    private final int chunkSize;
    private int currentIndex;
    private final DNAChunkingStrategy dnaChunkingStrategy;
    private int chunkIndex=-1;

    public FixedSizeChunkIterator(String sequence, int chunkSize, DNAChunkingStrategy dnaChunkingStrategy) {
        this.sequence = sequence;
        this.chunkSize = chunkSize;
        this.currentIndex = 0;
        this.dnaChunkingStrategy = dnaChunkingStrategy;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < sequence.length();
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        chunkIndex++;


        // Get chunk starting from currentIndex
        String chunk = dnaChunkingStrategy.chunk(sequence, chunkIndex);

        // Update the currentIndex for the next chunk
        currentIndex += chunkSize;
        return chunk;
    }

    @Override
    public int getChunkIndex() {
        return chunkIndex;
    }
}

