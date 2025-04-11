package com.mizhbio.aligner.alignment;

import static org.junit.jupiter.api.Assertions.*;

import com.mizhbio.aligner.chunk.command.StrategyChunkingCommand;
import com.mizhbio.aligner.chunk.factory.DefaultChunkFactory;
import com.mizhbio.aligner.chunk.strategy.impl.LengthChunkingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AlignerTest {

    private Aligner aligner;
    private AlignmentService service;

    @BeforeEach
    public void setUp(){
        int chunkSize = 6;
        StrategyChunkingCommand chunkingCommand = new StrategyChunkingCommand(
                chunkSize,
                new LengthChunkingStrategy(chunkSize),
                new DefaultChunkFactory()
        );
        ChunkedAlignmentService alignmentService = new ChunkedAlignmentService(
                chunkingCommand,
                new BioJavaAlignmentAdapter()
        );
        aligner = new Aligner(alignmentService);
        service = new BioJavaAlignmentAdapter();
    }

    @Test
    public void testSimpleMismatchAlignment() {

        String seq1 = "AGCTGAC";
        String seq2 = "AGCTGGC";

        AggregatedAlignmentResult result = aligner.align(seq1, seq2);
        AlignmentResult expected = service.align(seq1, seq2);
        assertEquals(expected.alignedSeq1, result.alignedSequence1);
        assertEquals(expected.alignedSeq2, result.alignedSequence2);
    }

    @Test
    public void testGapInMiddle() {

        String seq1 = "ACGTAC";
        String seq2 = "ACG--AC";

        AggregatedAlignmentResult result = aligner.align(seq1, seq2);
        AlignmentResult expected = service.align(seq1, seq2);
        assertEquals(expected.alignedSeq1, result.alignedSequence1);
        assertEquals(expected.alignedSeq2, result.alignedSequence2);
    }

    @Test
    public void testLeadingAndTrailingGaps() {

        String seq1 = "---AGTCG";
        String seq2 = "AGTCG---";

        AggregatedAlignmentResult result = aligner.align(seq1, seq2);
        AlignmentResult expected = service.align(seq1, seq2);
        assertEquals(expected.alignedSeq1, result.alignedSequence1);
        assertEquals(expected.alignedSeq2, result.alignedSequence2);
    }

    @Test
    public void testPerfectMatch() {

        String seq1 = "GATTACA";
        String seq2 = "GATTACA";

        AggregatedAlignmentResult result = aligner.align(seq1, seq2);
        AlignmentResult expected = service.align(seq1, seq2);
        assertEquals(expected.alignedSeq1, result.alignedSequence1);
        assertEquals(expected.alignedSeq2, result.alignedSequence2);
    }

    @Test
    public void testEmptySequence() {

        String seq1 = "";
        String seq2 = "AGTC";

        AggregatedAlignmentResult result = aligner.align(seq1, seq2);
        AlignmentResult expected = service.align(seq1, seq2);
        assertEquals(expected.alignedSeq1, result.alignedSequence1);
        assertEquals(expected.alignedSeq2, result.alignedSequence2);
    }
}
