package com.mizhbio.aligner.alignment;

import java.util.List;

public class Aligner implements DNAAligner {

    private final ChunkedAlignmentService chunkedAlignmentService;

    public Aligner(ChunkedAlignmentService chunkedAlignmentService) {
        this.chunkedAlignmentService = chunkedAlignmentService;
    }

    @Override
    public AggregatedAlignmentResult align(String dna1, String dna2) {
        List<AlignedChunkResult> alignedChunks = chunkedAlignmentService.chunkAndAlign(dna1, dna2);
        return aggregate(alignedChunks);
    }

}

