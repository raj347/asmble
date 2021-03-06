package asmble.run.jvm

import asmble.AsmErr

sealed class RunErr(message: String, cause: Throwable? = null) : RuntimeException(message, cause), AsmErr {

    class ImportMemoryLimitTooSmall(
        val expected: Int,
        val actual: Int
    ) : RunErr("Import memory limit $actual but expecting at least $expected") {
        override val asmErrString get() = "actual size smaller than declared"
    }

    class ImportMemoryCapacityTooLarge(
        val expected: Int,
        val actual: Int
    ) : RunErr("Import table capacity $actual but expecting no more than $expected") {
        override val asmErrString get() = "maximum size larger than declared"
    }

    class InvalidDataIndex(
        val index: Int,
        val dataSize: Int,
        val memSize: Int
    ) : RunErr("Trying to set $dataSize bytes at index $index but mem limit is only $memSize") {
        override val asmErrString get() = "data segment does not fit"
    }

    class ImportTableTooSmall(
        val expected: Int,
        val actual: Int
    ) : RunErr("Import table sized $actual but expecting at least $expected") {
        override val asmErrString get() = "actual size smaller than declared"
    }

    class ImportTableTooLarge(
        val expected: Int,
        val actual: Int
    ) : RunErr("Import table sized $actual but expecting no more than $expected") {
        override val asmErrString get() = "maximum size larger than declared"
    }

    class InvalidElemIndex(
        val index: Int,
        val tableSize: Int
    ) : RunErr("Trying to set elem at index $index but table size is only $tableSize") {
        override val asmErrString get() = "elements segment does not fit"
    }
}