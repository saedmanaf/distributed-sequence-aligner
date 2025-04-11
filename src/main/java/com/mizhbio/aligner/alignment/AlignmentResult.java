package com.mizhbio.aligner.alignment;

public class AlignmentResult {
    public final String alignedSeq1;
    public final String alignedSeq2;

    public AlignmentResult(String alignedSeq1, String alignedSeq2) {
        this.alignedSeq1 = alignedSeq1;
        this.alignedSeq2 = alignedSeq2;
    }

    @Override
    public String toString() {
        return "AlignmentResult{" +
                "alignedSeq1='" + alignedSeq1 + '\'' +
                ", alignedSeq2='" + alignedSeq2 + '\'' +
                '}';
    }
}
