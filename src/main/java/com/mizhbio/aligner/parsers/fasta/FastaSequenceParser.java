package com.mizhbio.aligner.parsers.fasta;

import com.mizhbio.aligner.parsers.ParsedResult;
import com.mizhbio.aligner.parsers.SequenceReaderAdaptor;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.io.FastaReaderHelper;

import java.io.File;
import java.util.Map;

public class FastaSequenceParser implements SequenceReaderAdaptor {

    @Override
    public ParsedResult getSequences(String filePath) throws Exception {
        Map<String, DNASequence> sequences =
                FastaReaderHelper.readFastaDNASequence(new File(filePath));

        String[] result = new String[2];
        int i = 0;
        for (DNASequence sequence : sequences.values()) {
            result[i++] = sequence.getSequenceAsString();
            if (i >= 2) break;
        }
        return new ParsedResult(result[0],result[1]);
    }
}
