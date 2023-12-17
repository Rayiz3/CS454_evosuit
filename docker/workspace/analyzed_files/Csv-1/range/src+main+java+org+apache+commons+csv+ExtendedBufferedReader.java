{
  "filepath": "/tmp/Csv-1b/src/main/java/org/apache/commons/csv/ExtendedBufferedReader.java",
  "nodes": [
    {
      "type": "class_interface",
      "name": "ExtendedBufferedReader",
      "is_interface": false,
      "parent_types": [
        "java.io.BufferedReader"
      ],
      "begin_line": 34,
      "end_line": 161,
      "comment": "\n * ExtendedBufferedReader\n *\n * A special reader decorator which supports more\n * sophisticated access to the underlying reader object.\n *\n * In particular the reader supports a look-ahead option,\n * which allows you to see the next char returned by\n * next().\n "
    },
    {
      "type": "field",
      "varNames": [
        "END_OF_STREAM"
      ],
      "begin_line": 37,
      "end_line": 37,
      "comment": " The end of stream symbol "
    },
    {
      "type": "field",
      "varNames": [
        "UNDEFINED"
      ],
      "begin_line": 40,
      "end_line": 40,
      "comment": " Undefined state for the lookahead char "
    },
    {
      "type": "field",
      "varNames": [
        "lastChar"
      ],
      "begin_line": 43,
      "end_line": 43,
      "comment": " The last char returned "
    },
    {
      "type": "field",
      "varNames": [
        "lineCounter"
      ],
      "begin_line": 46,
      "end_line": 46,
      "comment": " The line counter "
    },
    {
      "type": "constructor",
      "signature": "org.apache.commons.csv.ExtendedBufferedReader.ExtendedBufferedReader(java.io.Reader)",
      "begin_line": 51,
      "end_line": 53,
      "comment": "\n     * Created extended buffered reader using default buffer-size\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.csv.ExtendedBufferedReader.read()",
      "begin_line": 55,
      "end_line": 63,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.apache.commons.csv.ExtendedBufferedReader.readAgain()",
      "begin_line": 75,
      "end_line": 77,
      "comment": "\n     * Returns the last character that was read as an integer (0 to 65535). This\n     * will be the last character returned by any of the read methods. This will\n     * not include a character read using the {@link #peek()} method. If no\n     * character has been read then this will return {@link #UNDEFINED}. If the\n     * end of the stream was reached on the last read then this will return\n     * {@link #END_OF_STREAM}.\n     * \n     * @return the last character that was read\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.csv.ExtendedBufferedReader.read(char[], int, int)",
      "begin_line": 79,
      "end_line": 107,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.apache.commons.csv.ExtendedBufferedReader.readLine()",
      "begin_line": 121,
      "end_line": 135,
      "comment": "\n     * Calls {@link BufferedReader#readLine()} which drops the line terminator(s).\n     * This method should only be called when processing a comment, otherwise\n     * information can be lost.\n     * \u003cp\u003e\n     * Increments  {@link #lineCounter}\n     * \u003cp\u003e\n     * Sets {@link #lastChar} to {@link #END_OF_STREAM} at EOF, \n     * otherwise to last character on the line (won\u0027t be CR or LF) \n     * \n     * @return the line that was read, or null if reached EOF.\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.csv.ExtendedBufferedReader.lookAhead()",
      "begin_line": 145,
      "end_line": 151,
      "comment": "\n     * Returns the next character in the current reader without consuming it. So\n     * the next call to {@link #read()} will still return this value.\n     * \n     * @return the next character\n     * \n     * @throws IOException if there is an error in reading\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.csv.ExtendedBufferedReader.getLineNumber()",
      "begin_line": 158,
      "end_line": 160,
      "comment": "\n     * Returns the nof line read\n     *\n     * @return the current-line-number (or -1)\n     "
    }
  ]
}