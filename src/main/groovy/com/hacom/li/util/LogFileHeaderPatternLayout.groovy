package com.hacom.li.util

import ch.qos.logback.classic.PatternLayout

/**
 * Created by Edgar Rios on 20/10/2017.
 * https://stackoverflow.com/questions/46278760/how-to-write-logfile-in-csv-format-using-logback
 */
class LogFileHeaderPatternLayout extends PatternLayout{

    String filePath
    String header

    @Override
    String getFileHeader() {
        if(getConstainsHeader()) {
            return ""
        } else {
            return header
        }
    }

    boolean getConstainsHeader() {
        List<String> lines = new File(filePath).readLines()
        def line = ""
        if (lines.size() > 0) {
            line = lines.first()
        }
        return line.contains(header)
    }
}
