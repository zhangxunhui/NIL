package jp.ac.osaka_u.sdl.nil

import java.io.File

data class NILSegConfig(
    val src: File,
    val minLine: Int = 3, // this is different from NILMain, no white line is counted
    val minToken: Int = 10, // this is different from NILMain
    val gramSize: Int = 5,
    val partitionNum: Int = 10,
    val outputFileName: String = "result_segment.csv",
    val threads: Int = 0,
    val lang: Language = Language.JAVA,
    val isForBigCloneEval: Boolean = false,
    val isForMutationInjectionFramework: Boolean = false,
)

fun parseSegArgs(args: Array<String>): NILSegConfig {
    var src: File? = null
    var minLine = 3
    var minToken = 10
    var gramSize = 5
    var partitionNum = 10
    var outputFileName: String? = null
    var threads = 0
    var lang = Language.JAVA
    var isForBigCloneEval = false
    var isForMutationInjectionFramework = false

    val iterator = args.iterator()
    while (iterator.hasNext()) {
        when (val optionName = iterator.next().toLowerCase()) {
            "-s", "--src" -> src = File(iterator.next())
            "-mil", "--min-line" -> minLine = iterator.next().toIntOrException(optionName)
            "-mit", "--min-token" -> minToken = iterator.next().toIntOrException(optionName)
            "-n", "--n-gram" -> gramSize = iterator.next().toIntOrException(optionName)
            "-p", "--partition-number" -> partitionNum = iterator.next().toIntOrException(optionName)
            "-o", "--output" -> outputFileName = iterator.next()
            "-t", "--threads" -> threads = iterator.next().toInt()
            "-l", "--language" -> lang = iterator.next().toLangOrException()
            "-bce", "--bigcloneeval" -> isForBigCloneEval = true
            "-mif", "--mutationinjectionframework" -> isForMutationInjectionFramework = true
            else -> throw InvalidOptionException("$optionName is invalid option.")
        }
    }

    if (isForBigCloneEval && isForMutationInjectionFramework) {
        throw InvalidOptionException("Cannot specify both -bce and -mif.")
    }

    return NILSegConfig(
            src ?: throw InvalidOptionException("-s must be specified."),
            minLine,
            minToken,
            gramSize,
            partitionNum,
            outputFileName ?: "result_segment_${gramSize}.csv",
            threads,
            lang,
            isForBigCloneEval,
            isForMutationInjectionFramework,
    )
}