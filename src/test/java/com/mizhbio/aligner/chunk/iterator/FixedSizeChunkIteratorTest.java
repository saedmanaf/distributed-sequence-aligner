package com.mizhbio.aligner.chunk.iterator;

import com.mizhbio.aligner.chunk.strategy.DNAChunkingStrategy;
import com.mizhbio.aligner.chunk.strategy.impl.LengthChunkingStrategy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

public class FixedSizeChunkIteratorTest {

    @Test
    void iterator_returnsAllChunksCorrectly() {
        DNAChunkingStrategy strategy = new LengthChunkingStrategy(3);
        FixedSizeChunkIterator iterator = new FixedSizeChunkIterator("AGTCAGTCA", 3, strategy);

        assertTrue(iterator.hasNext());
        assertEquals("AGT", iterator.next());
        assertEquals(0,iterator.getChunkIndex());

        assertTrue(iterator.hasNext());
        assertEquals("CAG", iterator.next());
        assertEquals(1,iterator.getChunkIndex());

        assertTrue(iterator.hasNext());
        assertEquals("TCA", iterator.next());
        assertEquals(2,iterator.getChunkIndex());

        assertFalse(iterator.hasNext());
    }

    @Test
    void iterator_throwsExceptionWhenNoNext() {
        DNAChunkingStrategy strategy = new LengthChunkingStrategy(3);
        FixedSizeChunkIterator iterator = new FixedSizeChunkIterator("AGT", 3, strategy);
        iterator.next(); // ok

        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void testIteratorReturnsAllChunks() {
        DNAChunkingStrategy strategy = new LengthChunkingStrategy(3);
        ChunkIterator iterator = new FixedSizeChunkIterator("ACGTGCAT", 3, strategy);

        assertTrue(iterator.hasNext());
        assertEquals("ACG", iterator.next());
        assertEquals("TGC", iterator.next());
        assertEquals("AT", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testGetChunkIndexIsAccurate() {
        DNAChunkingStrategy strategy = new LengthChunkingStrategy(2);
        ChunkIterator iterator = new FixedSizeChunkIterator("ACGT", 2, strategy);
        iterator.next();
        assertEquals(0, iterator.getChunkIndex());
    }

    @Test
    void testNoSuchElementException() {
        DNAChunkingStrategy strategy = new LengthChunkingStrategy(3);
        ChunkIterator iterator = new FixedSizeChunkIterator("AC", 3, strategy);
        iterator.next();
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}
