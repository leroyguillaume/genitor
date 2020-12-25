package tech.genitor.core

/**
 * SHA1 calculator.
 */
interface Sha1Calculator {
    /**
     * Compute SHA1 and return it to hexadecimal format.
     *
     * @param bytes Bytes.
     * @return SHA1 in hexadecimal format.
     */
    fun compute(bytes: ByteArray): String
}
