package com.mizhbio.aligner.chunk.command;

import com.mizhbio.aligner.chunk.Chunk;

import java.util.List;

public interface ChunkingCommand {
    List<Chunk> chunk(String sequence);

}
