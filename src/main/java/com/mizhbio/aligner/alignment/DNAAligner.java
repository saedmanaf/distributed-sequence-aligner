package com.mizhbio.aligner.alignment;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface DNAAligner {
    AggregatedAlignmentResult align(String dna1, String dna2) throws ExecutionException, InterruptedException;

    default AggregatedAlignmentResult aggregate(List<AlignedChunkResult> results) {
        StringBuilder alignedSeq1 = new StringBuilder();
        StringBuilder alignedSeq2 = new StringBuilder();

        for (AlignedChunkResult result : results) {
            alignedSeq1.append(result.alignment.alignedSeq1);
            alignedSeq2.append(result.alignment.alignedSeq2);
        }

        return new AggregatedAlignmentResult(alignedSeq1.toString(), alignedSeq2.toString());
    }
}
