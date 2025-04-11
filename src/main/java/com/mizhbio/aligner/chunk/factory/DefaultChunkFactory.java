package com.mizhbio.aligner.chunk.factory;

import com.mizhbio.aligner.chunk.Chunk;

public class DefaultChunkFactory implements ChunkFactory{
    @Override
    public Chunk createChunk( String sequence,String chunkedStr, int chunkIndex) {
        return new Chunk(
                sequence,
                chunkedStr,
                chunkIndex);
    }

}
