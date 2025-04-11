package com.mizhbio.aligner.chunk.strategy.impl;

import com.mizhbio.aligner.chunk.strategy.DNAChunkingStrategy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LengthChunkingStrategyTest {

    @Test
    void chunk_returnsCorrectSubstring() {
        DNAChunkingStrategy strategy = new LengthChunkingStrategy(4);
        String sequence = "AGTCAGTCAGTC";
        String chunk = strategy.chunk(sequence, 1); // Should return "AGTC"
        assertEquals("AGTC", chunk);
    }

    @Test
    void chunk_handlesEndOfSequence() {
        DNAChunkingStrategy strategy = new LengthChunkingStrategy(5);
        String sequence = "AGTCA";
        String chunk = strategy.chunk(sequence, 0); // Full string
        assertEquals("AGTCA", chunk);
    }

    @Test
    void testExactChunking() {
        DNAChunkingStrategy strategy = new LengthChunkingStrategy(3);
        assertEquals("ACG", strategy.chunk("ACGTGCA", 0));
        assertEquals("TGC", strategy.chunk("ACGTGCA", 1));
        assertEquals("A", strategy.chunk("ACGTGCA", 2)); // leftover
    }

    @Test
    void testOutOfBoundsChunkIndex() {
        DNAChunkingStrategy strategy = new LengthChunkingStrategy(4);
        assertEquals("", strategy.chunk("ACGT", 2)); // should be empty
    }

    @Test
    void testEmptySequence() {
        DNAChunkingStrategy strategy = new LengthChunkingStrategy(5);
        assertEquals("", strategy.chunk("", 0));
    }

    @Test
    void testNegativeChunkIndex() {
        DNAChunkingStrategy strategy = new LengthChunkingStrategy(3);
        assertThrows(StringIndexOutOfBoundsException.class, () -> strategy.chunk("ACGT", -1));
    }
}
