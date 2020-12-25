package tech.genitor.commons.beans

import org.apache.commons.codec.digest.DigestUtils
import tech.genitor.core.Sha1Calculator

/**
 * Default implementation of SHA1 calculator.
 */
class DefaultSha1Calculator : Sha1Calculator {
    override fun compute(bytes: ByteArray): String = DigestUtils.sha1Hex(bytes)
}
