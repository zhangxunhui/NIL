package jp.ac.osaka_u.sdl.nil.entity

interface CS {

    /**
     * Calculate the common sequences between given token sequence a and b.
     */
    fun calcSequences(a: TokenSequence, b: TokenSequence): List<Int>

}