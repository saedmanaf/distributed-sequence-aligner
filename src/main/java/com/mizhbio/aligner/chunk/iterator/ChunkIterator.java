package com.mizhbio.aligner.chunk.iterator;

import java.util.Iterator;

public interface ChunkIterator extends Iterator<String> {

    int getChunkIndex();
}
