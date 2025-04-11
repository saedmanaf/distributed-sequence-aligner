package com.mizhbio.aligner.chunk;

import java.util.UUID;

public class Chunk {
    public final String dnaId;
    public final String id;
    public final String sequence;
    public final int chunkIndex;

    public Chunk(String dnaId, String sequence, int chunkIndex) {
        this.dnaId = dnaId;
        this.id = UUID.randomUUID().toString();
        this.sequence = sequence;
        this.chunkIndex = chunkIndex;
    }

    @Override
    public String toString() {
        return "Chunk{" +
                "dnaId='" + dnaId + '\'' +
                ", id='" + id + '\'' +
                ", sequence='" + sequence + '\'' +
                ", chunkIndex=" + chunkIndex +
                '}';
    }
}
