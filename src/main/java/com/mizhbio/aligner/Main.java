package com.mizhbio.aligner;


import com.mizhbio.aligner.alignment.*;
import com.mizhbio.aligner.chunk.command.StrategyChunkingCommand;
import com.mizhbio.aligner.chunk.factory.DefaultChunkFactory;
import com.mizhbio.aligner.chunk.strategy.impl.LengthChunkingStrategy;
import com.mizhbio.aligner.parsers.ParsedResult;
import com.mizhbio.aligner.parsers.SequenceReaderAdaptor;
import com.mizhbio.aligner.parsers.fasta.FastaSequenceParser;

public class Main {
    public static void main(String[] args) throws Exception {
        SequenceReaderAdaptor sequenceReaderAdaptor = new FastaSequenceParser();
        ParsedResult sequences = sequenceReaderAdaptor.getSequences("src/main/resources/sequences.fasta");
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

        Aligner aligner = new Aligner(alignmentService);
        long timeNow = System.currentTimeMillis();
        AggregatedAlignmentResult alignedChunkResults = aligner.align(sequences.sequence1, sequences.sequence2);
        AlignmentVisualizer.visualize(alignedChunkResults.alignedSequence1, alignedChunkResults.alignedSequence2);
        System.out.println("time taken without parallel execution"+(System.currentTimeMillis()-timeNow));
        timeNow = System.currentTimeMillis();
        DNAAligner alignerP = new ParallelAligner(new BioJavaAlignmentAdapter()  ,10, chunkingCommand);
        AggregatedAlignmentResult alignedChunkResults3 = alignerP.align(sequences.sequence1, sequences.sequence2);
        AlignmentVisualizer.visualize(alignedChunkResults3.alignedSequence1, alignedChunkResults.alignedSequence2);
        System.out.println("time taken with parallel execution"+(System.currentTimeMillis()-timeNow));

    }
}
