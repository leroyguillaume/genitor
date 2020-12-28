package tech.genitor.core

import java.nio.file.Path

/**
 * Exception thrown when an error occurs during Genitor project file loading.
 */
sealed class GenitorFileException : RuntimeException() {
    /**
     * Exception thrown when an error occurs during Genitor project file parsing.
     *
     * @param path Path to Genitor project file.
     * @param cause Cause of error.
     */
    class Parsing(
        override val path: Path,
        override val cause: Throwable
    ) : GenitorFileException() {
        override val message = "Unable to parse $path as Genitor project file"
    }

    /**
     * Exception thrown when an error occurs during Genitor project file validation.
     *
     * @param path Path to Genitor project file.
     * @param message Error message.
     */
    class Validation(
        override val path: Path,
        message: String
    ) : GenitorFileException() {
        override val message = "Invalid Genitor project file ($path): $message"
    }

    abstract override val message: String

    /**
     * Path to Genitor project file.
     */
    abstract val path: Path
}
