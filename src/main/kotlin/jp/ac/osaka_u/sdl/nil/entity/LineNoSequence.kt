package jp.ac.osaka_u.sdl.nil.entity

/**
 * LineNoSequence is a list of each token's related line number in the source file.
 * The index is the same as TokenSequence's index, while the value is the line number.
 */
typealias LineNoSequence = List<Int>