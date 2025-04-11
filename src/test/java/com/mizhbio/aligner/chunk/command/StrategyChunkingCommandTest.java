package com.mizhbio.aligner.chunk.command;

import com.mizhbio.aligner.chunk.Chunk;
import com.mizhbio.aligner.chunk.factory.ChunkFactory;
import com.mizhbio.aligner.chunk.factory.DefaultChunkFactory;
import com.mizhbio.aligner.chunk.strategy.DNAChunkingStrategy;
import com.mizhbio.aligner.chunk.strategy.impl.LengthChunkingStrategy;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class StrategyChunkingCommandTest {

    @Test
    void chunk_returnsExpectedChunks() {
        LengthChunkingStrategy strategy = new LengthChunkingStrategy(3);
        DefaultChunkFactory factory = new DefaultChunkFactory();
        StrategyChunkingCommand command = new StrategyChunkingCommand(3, strategy, factory);

        String sequence = "AGTCAGTCA";
        List<Chunk> chunks = command.chunk(sequence);

        assertEquals(3, chunks.size());
        assertEquals("AGT", chunks.get(0).sequence);
        assertEquals("CAG", chunks.get(1).sequence);
        assertEquals("TCA", chunks.get(2).sequence);
    }

    @Test
    void chunk_handlesShortSequence() {
        DNAChunkingStrategy strategy = new LengthChunkingStrategy(5);
        ChunkFactory factory = new DefaultChunkFactory();
        ChunkingCommand command = new StrategyChunkingCommand(5, strategy, factory);

        String sequence = "AGTCA";
        List<Chunk> chunks = command.chunk(sequence);

        assertEquals(1, chunks.size());
        assertEquals("AGTCA", chunks.get(0).sequence);
    }

    @Test
    void testFullPipeline() {
        DNAChunkingStrategy strategy = new LengthChunkingStrategy(4);
        ChunkFactory factory = new DefaultChunkFactory();
        ChunkingCommand command = new StrategyChunkingCommand(4, strategy, factory);

        List<Chunk> chunks = command.chunk("ACGTGTCAGT");

        assertEquals(3, chunks.size());
        assertEquals("ACGT", chunks.get(0).sequence);
        assertEquals("GTCA", chunks.get(1).sequence);
        assertEquals("GT", chunks.get(2).sequence);
    }

    @Test
    void testHandlesEmptySequence() {
        DNAChunkingStrategy strategy = new LengthChunkingStrategy(4);
        ChunkFactory factory = new DefaultChunkFactory();
        ChunkingCommand command = new StrategyChunkingCommand(4, strategy, factory);

        List<Chunk> chunks = command.chunk("");

        assertTrue(chunks.isEmpty());
    }

    @Test
    void testChunkIndicesAreSequential() {
        DNAChunkingStrategy strategy = new LengthChunkingStrategy(3);
        ChunkFactory factory = new DefaultChunkFactory();
        ChunkingCommand command = new StrategyChunkingCommand(3, strategy, factory);

        List<Chunk> chunks = command.chunk("AAACCCGGGTTT");

        for (int i = 0; i < chunks.size(); i++) {
            assertEquals(i, chunks.get(i).chunkIndex);
        }
    }
}
