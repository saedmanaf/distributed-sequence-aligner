package com.mizhbio.aligner.alignment;

import org.biojava.nbio.alignment.Alignments;
import org.biojava.nbio.alignment.template.PairwiseSequenceAligner;
import org.biojava.nbio.core.alignment.template.SubstitutionMatrix;
import org.biojava.nbio.core.alignment.matrices.SubstitutionMatrixHelper;
import org.biojava.nbio.alignment.SimpleGapPenalty;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.compound.NucleotideCompound;

public class BioJavaAlignmentAdapter implements AlignmentService {
    @Override
    public AlignmentResult align(String seq1, String seq2) {
        try {
            DNASequence dna1 = new DNASequence(seq1);
            DNASequence dna2 = new DNASequence(seq2);

            SubstitutionMatrix<NucleotideCompound> matrix = SubstitutionMatrixHelper.getNuc4_4();
            SimpleGapPenalty gapPenalty = new SimpleGapPenalty();
            gapPenalty.setOpenPenalty((short)5);
            gapPenalty.setExtensionPenalty((short)2);

            PairwiseSequenceAligner<DNASequence, NucleotideCompound> aligner =
                    Alignments.getPairwiseAligner(dna1, dna2, Alignments.PairwiseSequenceAlignerType.GLOBAL, gapPenalty, matrix);

            String aligned1 = aligner.getPair().getQuery().getSequenceAsString();
            String aligned2 = aligner.getPair().getTarget().getSequenceAsString();

            return new AlignmentResult(aligned1, aligned2);
        } catch (Exception e) {
            throw new RuntimeException("Alignment failed", e);
        }
    }
}
