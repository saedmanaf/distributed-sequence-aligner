package com.mizhbio.aligner.parsers;

public interface SequenceReaderAdaptor {

    ParsedResult getSequences(String filePath) throws Exception;

}
