package com.mizhbio.aligner.alignment;

import com.mizhbio.aligner.chunk.Chunk;

public class AlignedChunkResult {
    public final Chunk chunk1;
    public final Chunk chunk2;
    public final AlignmentResult alignment;

    public AlignedChunkResult(Chunk chunk1, Chunk chunk2, AlignmentResult alignment) {
        this.chunk1 = chunk1;
        this.chunk2 = chunk2;
        this.alignment = alignment;
    }

    @Override
    public String toString() {
        return "AlignedChunkResult{" +
                "chunk1=" + chunk1 +
                ", chunk2=" + chunk2 +
                ", alignment=" + alignment +
                '}';
    }
}

