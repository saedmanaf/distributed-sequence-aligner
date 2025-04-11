package com.mizhbio.aligner.alignment;

public class AggregatedAlignmentResult {
    public final String alignedSequence1;
    public final String alignedSequence2;

    public AggregatedAlignmentResult(String alignedSequence1, String alignedSequence2) {
        this.alignedSequence1 = alignedSequence1;
        this.alignedSequence2 = alignedSequence2;
    }

    @Override
    public String toString() {
        return "AggregatedAlignmentResult{" +
                "alignedSequence1='" + alignedSequence1 + '\'' +
                ", alignedSequence2='" + alignedSequence2 + '\'' +
                '}';
    }
}
