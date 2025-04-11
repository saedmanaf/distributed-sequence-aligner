package com.mizhbio.aligner.alignment;

import com.mizhbio.aligner.chunk.Chunk;
import com.mizhbio.aligner.chunk.command.ChunkingCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelAligner implements DNAAligner{
    private final AlignmentService alignmentService;
    private final ExecutorService executorService;
    private final ChunkingCommand chunkingCommand;

    public ParallelAligner(AlignmentService alignmentService, int threadPoolSize, ChunkingCommand chunkingCommand) {
        this.alignmentService = alignmentService;
        this.executorService = Executors.newFixedThreadPool(threadPoolSize);
        this.chunkingCommand = chunkingCommand;
    }
    @Override
    public AggregatedAlignmentResult align(String dna1, String dna2) throws ExecutionException, InterruptedException {
        List<Chunk> chunks1 = chunkingCommand.chunk(dna1);
        List<Chunk> chunks2 = chunkingCommand.chunk(dna2);

        List<Future<AlignmentResult>> futures = new ArrayList<>();

        for (int i = 0; i < chunks1.size(); i++) {
            Chunk chunk1 = chunks1.get(i);
            Chunk chunk2 = chunks2.get(i);
            Callable<AlignmentResult> task = () -> alignmentService.align(chunk1.sequence, chunk2.sequence);
            futures.add(executorService.submit(task));
        }

        // Aggregating aligned chunks
        StringBuilder alignedSeq1 = new StringBuilder();
        StringBuilder alignedSeq2 = new StringBuilder();

        for (Future<AlignmentResult> future : futures) {
            AlignmentResult result = future.get();
            alignedSeq1.append(result.alignedSeq1);
            alignedSeq2.append(result.alignedSeq2);
        }
        executorService.shutdown();

        return new AggregatedAlignmentResult(alignedSeq1.toString(),alignedSeq2.toString());

    }
}
