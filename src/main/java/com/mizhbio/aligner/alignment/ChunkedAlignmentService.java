package com.mizhbio.aligner.alignment;

import com.mizhbio.aligner.chunk.Chunk;
import com.mizhbio.aligner.chunk.command.ChunkingCommand;

import java.util.ArrayList;
import java.util.List;

public class ChunkedAlignmentService {
    private final ChunkingCommand chunkingCommand;
    private final AlignmentService alignmentService;

    public ChunkedAlignmentService(ChunkingCommand chunkingCommand, AlignmentService alignmentService) {
        this.chunkingCommand = chunkingCommand;
        this.alignmentService = alignmentService;
    }

    public List<AlignedChunkResult> chunkAndAlign(String dna1, String dna2) {
        List<Chunk> chunks1 = chunkingCommand.chunk(dna1);
        List<Chunk> chunks2 = chunkingCommand.chunk(dna2);

        List<AlignedChunkResult> results = new ArrayList<>();
        int chunkCount = Math.min(chunks1.size(), chunks2.size());

        for (int i = 0; i < chunkCount; i++) {
            Chunk c1 = chunks1.get(i);
            Chunk c2 = chunks2.get(i);
            AlignmentResult alignment = alignmentService.align(c1.sequence, c2.sequence);

            results.add(new AlignedChunkResult(c1, c2, alignment));
        }

        return results;
    }
}

