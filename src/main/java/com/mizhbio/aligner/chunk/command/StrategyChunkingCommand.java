package com.mizhbio.aligner.chunk.command;

import com.mizhbio.aligner.chunk.Chunk;
import com.mizhbio.aligner.chunk.factory.ChunkFactory;
import com.mizhbio.aligner.chunk.iterator.ChunkIterator;
import com.mizhbio.aligner.chunk.iterator.FixedSizeChunkIterator;
import com.mizhbio.aligner.chunk.strategy.DNAChunkingStrategy;

import java.util.ArrayList;
import java.util.List;

public class StrategyChunkingCommand implements ChunkingCommand{
    private final int chunkSize;
    private final DNAChunkingStrategy strategy;
    private final ChunkFactory factory;

    public StrategyChunkingCommand(int chunkSize, DNAChunkingStrategy strategy, ChunkFactory factory) {
        this.chunkSize = chunkSize;
        this.strategy = strategy;
        this.factory = factory;
    }

    @Override
    public List<Chunk> chunk(String sequence) {
        List<Chunk> chunks = new ArrayList<>();
        ChunkIterator iterator = new FixedSizeChunkIterator(sequence,chunkSize, strategy);
        while (iterator.hasNext()){
            String chunkedSeq = iterator.next();
            Chunk chunk = factory.createChunk(sequence,chunkedSeq, iterator.getChunkIndex());
            chunks.add(chunk);
        }
        return chunks;
    }
}
