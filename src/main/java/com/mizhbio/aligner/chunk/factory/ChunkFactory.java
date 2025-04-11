package com.mizhbio.aligner.chunk.factory;

import com.mizhbio.aligner.chunk.Chunk;

public interface ChunkFactory {

    public Chunk createChunk( String sequence,String chunkedStr, int chunkIndex);
}
