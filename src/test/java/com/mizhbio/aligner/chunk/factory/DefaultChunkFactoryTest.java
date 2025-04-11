package com.mizhbio.aligner.chunk.factory;

import com.mizhbio.aligner.chunk.Chunk;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DefaultChunkFactoryTest {

    @Test
    void createChunk_createsCorrectChunk() {
        DefaultChunkFactory factory = new DefaultChunkFactory();
        Chunk chunk = factory.createChunk("DNA1", "AGTC", 1);

        assertEquals("DNA1", chunk.dnaId);
        assertEquals("AGTC", chunk.sequence);
        assertEquals(1, chunk.chunkIndex);
        assertNotNull(chunk.id);
    }

    @Test
    void testCreateChunkGeneratesUniqueId() {
        ChunkFactory factory = new DefaultChunkFactory();
        Chunk c1 = factory.createChunk("SEQ1", "ACG", 0);
        Chunk c2 = factory.createChunk("SEQ1", "TGC", 1);

        assertNotNull(c1.id);
        assertNotEquals(c1.id, c2.id);
    }

    @Test
    void testCreateChunkHoldsCorrectData() {
        ChunkFactory factory = new DefaultChunkFactory();
        Chunk chunk = factory.createChunk("DNA001", "GTA", 2);

        assertEquals("DNA001", chunk.dnaId);
        assertEquals("GTA", chunk.sequence);
        assertEquals(2, chunk.chunkIndex);
    }
}
