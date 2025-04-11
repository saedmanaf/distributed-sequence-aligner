package com.mizhbio.aligner.alignment;

public class AlignmentVisualizer {

    public static void visualize(String[] sequences) {
        visualize(sequences[0],sequences[1]);

    }

    public static void visualize(String seq1, String seq2) {
        if (seq1.length() != seq2.length()) {
            System.err.println("Sequences must be of equal length to visualize.");
            return;
        }

        StringBuilder matchLine = new StringBuilder();

        for (int i = 0; i < seq1.length(); i++) {
            char c1 = seq1.charAt(i);
            char c2 = seq2.charAt(i);

            if (c1 == c2) {
                matchLine.append("|");
            } else if (c1 == '-' || c2 == '-') {
                matchLine.append(" ");
            } else {
                matchLine.append(".");
            }
        }

        System.out.println("Alignment:");
        System.out.println("Seq1: " + seq1);
        System.out.println("      " + matchLine);
        System.out.println("Seq2: " + seq2);
    }

}

