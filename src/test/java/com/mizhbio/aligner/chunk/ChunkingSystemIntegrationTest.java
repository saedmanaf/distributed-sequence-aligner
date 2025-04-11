package com.mizhbio.aligner.chunk;

import com.mizhbio.aligner.chunk.command.ChunkingCommand;
import com.mizhbio.aligner.chunk.command.StrategyChunkingCommand;
import com.mizhbio.aligner.chunk.factory.ChunkFactory;
import com.mizhbio.aligner.chunk.factory.DefaultChunkFactory;
import com.mizhbio.aligner.chunk.strategy.DNAChunkingStrategy;
import com.mizhbio.aligner.chunk.strategy.impl.LengthChunkingStrategy;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ChunkingSystemIntegrationTest {

    @Test
    void testCompleteChunkingFlow() {
        String inputDNA = "ACGTGCAATGCCGTTAGC";

        // Step 1: Set up all collaborators
        int chunkSize = 5;
        DNAChunkingStrategy strategy = new LengthChunkingStrategy(chunkSize);
        ChunkFactory factory = new DefaultChunkFactory();
        ChunkingCommand command = new StrategyChunkingCommand(chunkSize, strategy, factory);

        // Step 2: Execute chunking pipeline
        List<Chunk> chunks = command.chunk(inputDNA);

        // Step 3: Assert results
        assertEquals(4, chunks.size());

        assertEquals("ACGTG", chunks.get(0).sequence);
        assertEquals("CAATG", chunks.get(1).sequence);
        assertEquals("CCGTT", chunks.get(2).sequence);
        assertEquals("AGC", chunks.get(3).sequence); // Last chunk, leftover

        // Check all chunks belong to same DNA ID
        chunks.forEach(c -> assertEquals(inputDNA, c.dnaId));

        // Ensure all chunk indices are correct
        for (int i = 0; i < chunks.size(); i++) {
            assertEquals(i, chunks.get(i).chunkIndex);
        }

        // Ensure all chunk IDs are unique
        long distinctIds = chunks.stream().map(c -> c.id).distinct().count();
        assertEquals(chunks.size(), distinctIds);
    }

    @Test
    void testEmptyInputDNAProducesNoChunks() {
        DNAChunkingStrategy strategy = new LengthChunkingStrategy(4);
        ChunkFactory factory = new DefaultChunkFactory();
        ChunkingCommand command = new StrategyChunkingCommand(4, strategy, factory);

        List<Chunk> result = command.chunk("");
        assertTrue(result.isEmpty(), "Chunking empty DNA should return no chunks");
    }

    @Test
    void testSingleCharacterInput() {
        DNAChunkingStrategy strategy = new LengthChunkingStrategy(3);
        ChunkFactory factory = new DefaultChunkFactory();
        ChunkingCommand command = new StrategyChunkingCommand(3, strategy, factory);

        List<Chunk> result = command.chunk("A");

        assertEquals(1, result.size());
        assertEquals("A", result.get(0).sequence);
        assertEquals(0, result.get(0).chunkIndex);
    }
}

