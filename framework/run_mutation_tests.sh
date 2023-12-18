BUG_VERSIONS="
Codec-11-org.apache.commons.codec.net.QuotedPrintableCodec
Codec-16-org.apache.commons.codec.binary.Base32
Codec-17-org.apache.commons.codec.binary.StringUtils
Codec-18-org.apache.commons.codec.binary.StringUtils
Cli-37-org.apache.commons.cli.DefaultParser
Cli-38-org.apache.commons.cli.DefaultParser
Cli-39-org.apache.commons.cli.TypeHandler
Compress-34-org.apache.commons.compress.archivers.zip.X7875_NewUnix
Compress-39-org.apache.commons.compress.utils.ArchiveUtils
Compress-40-org.apache.commons.compress.utils.BitInputStream
Compress-44-org.apache.commons.compress.utils.ChecksumCalculatingInputStream
Compress-45-org.apache.commons.compress.archivers.tar.TarUtils
Csv-1-org.apache.commons.csv.ExtendedBufferedReader
Csv-2-org.apache.commons.csv.CSVRecord
Csv-4-org.apache.commons.csv.CSVParser
Csv-6-org.apache.commons.csv.CSVRecord
Csv-7-org.apache.commons.csv.CSVParser
Csv-10-org.apache.commons.csv.CSVPrinter
Csv-11-org.apache.commons.csv.CSVParser
Csv-12-org.apache.commons.csv.CSVFormat
Csv-16-org.apache.commons.csv.CSVParser
Jsoup-58-org.jsoup.parser.Parser
Jsoup-69-org.jsoup.nodes.FormElement
Jsoup-79-org.jsoup.nodes.LeafNode
Jsoup-80-org.jsoup.parser.XmlTreeBuilder
Jsoup-84-org.jsoup.helper.W3CDom
Jsoup-86-org.jsoup.nodes.Comment
Jsoup-93-org.jsoup.nodes.FormElement
Lang-3-org.apache.commons.lang3.math.NumberUtils
Lang-4-org.apache.commons.lang3.text.translate.LookupTranslator
Lang-5-org.apache.commons.lang3.LocaleUtils
Lang-7-org.apache.commons.lang3.math.NumberUtils
Lang-16-org.apache.commons.lang3.math.NumberUtils
Math-9-org.apache.commons.math3.geometry.euclidean.threed.Line
Math-25-org.apache.commons.math3.optimization.fitting.HarmonicFitter
Math-26-org.apache.commons.math3.fraction.Fraction
Math-27-org.apache.commons.math3.fraction.Fraction
Math-36-org.apache.commons.math.fraction.BigFraction
Math-52-org.apache.commons.math.geometry.euclidean.threed.Rotation
Math-53-org.apache.commons.math.complex.Complex
Math-55-org.apache.commons.math.geolmetry.Vector3D
Math-56-org.apache.commons.math.util.MutidimensionalCounter
"

RATES="
0.75
0.7
0.65
0.6
0.55
"

mkdir -p test_results_3
for BUG in $BUG_VERSIONS; do
    PID=$(echo $BUG | cut -d '-' -f 1)
    BID=$(echo $BUG | cut -d '-' -f 2)
    TARGET_CLASS=$(echo $BUG | cut -d '-' -f 3)

    mkdir -p "test_results_3/$PID/$BID"
    new_dir="test_results_3/$PID/$BID"

    mkdir -p "$new_dir/crossover"
    mkdir -p "$new_dir/mutation"

    for rate in $RATES; do
        mkdir -p "$new_dir/crossover/$rate"
        mkdir -p "$new_dir/mutation/$rate"

        ./bin/run_mutation.pl -p $PID -v ${BID}b -d test_suites_3/$PID/$BID/crossover/$rate -o $new_dir/crossover/$rate
        ./bin/run_mutation.pl -p $PID -v ${BID}b -d test_suites_3/$PID/$BID/mutation/$rate -o $new_dir/mutation/$rate
    done
done