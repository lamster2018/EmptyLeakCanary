package shark

import shark.LeakTrace.GcRootType.JAVA_FRAME
import shark.LeakTraceObject.LeakingStatus.LEAKING
import shark.LeakTraceObject.LeakingStatus.NOT_LEAKING
import shark.LeakTraceObject.LeakingStatus.UNKNOWN
import shark.LeakTraceReference.ReferenceType.STATIC_FIELD
import java.io.Serializable

/**
 * The best strong reference path from a GC root to the leaking object. "Best" here means the
 * shortest prioritized path. A large number of distinct paths can generally be found leading
 * to a leaking object. Shark prioritizes paths that don't go through known
 * [LibraryLeakReferenceMatcher] (because those are known to create leaks so it's more interesting
 * to find other paths causing leaks), then it prioritize paths that don't go through java local
 * gc roots (because those are harder to reason about). Taking those priorities into account,
 * finding the shortest path means there are less [LeakTraceReference] that can be suspected to
 * cause the leak.
 */
data class LeakTrace(
    /**
     * The Garbage Collection root that references the [LeakTraceReference.originObject] in
     * the first [LeakTraceReference] of [referencePath].
     */
    val gcRootType: GcRootType,
    val referencePath: List<LeakTraceReference>,
    val leakingObject: LeakTraceObject,
    /**
     * The minimum number of bytes which would be freed if all references to the leaking object were
     * released. Null if the retained heap size was not computed.
     */
    val retainedHeapByteSize: Int?
) : Serializable {

    /**
     * A part of [referencePath] that contains the references suspected to cause the leak.
     * Starts at the last non leaking object and ends before the first leaking object.
     */
    val suspectReferenceSubpath
        get() = referencePath.asSequence()
            .filterIndexed { index, _ ->
                referencePathElementIsSuspect(index)
            }

    /**
     * A SHA1 hash that represents this leak trace. This can be useful to group together similar
     * leak traces.
     *
     * The signature is a hash of [suspectReferenceSubpath].
     */
    val signature: String = ""

    /**
     * Returns true if the [referencePath] element at the provided [index] contains a reference
     * that is suspected to cause the leak, ie if [index] is greater than or equal to the index
     * of the [LeakTraceReference] of the last non leaking object and strictly lower than the index
     * of the [LeakTraceReference] of the first leaking object.
     */
    fun referencePathElementIsSuspect(index: Int): Boolean = false


    override fun toString(): String = leakTraceAsString(showLeakingStatus = true)

    fun toSimplePathString(): String = leakTraceAsString(showLeakingStatus = false)

    private fun leakTraceAsString(showLeakingStatus: Boolean): String = ""

    enum class GcRootType(val description: String) {
        JNI_GLOBAL("Global variable in native code"),
        JNI_LOCAL("Local variable in native code"),
        JAVA_FRAME("Java local variable"),
        NATIVE_STACK("Input or output parameters in native code"),
        STICKY_CLASS("System class"),
        THREAD_BLOCK("Thread block"),
        MONITOR_USED(
            "Monitor (anything that called the wait() or notify() methods, or that is synchronized.)"
        ),
        THREAD_OBJECT("Thread object"),
        JNI_MONITOR("Root JNI monitor"),
        ;

        companion object {
            fun fromGcRoot(gcRoot: GcRoot): GcRootType = when (gcRoot) {
                is GcRoot.JniGlobal -> JNI_GLOBAL
                is GcRoot.JniLocal -> JNI_LOCAL
                is GcRoot.JavaFrame -> JAVA_FRAME
                is GcRoot.NativeStack -> NATIVE_STACK
                is GcRoot.StickyClass -> STICKY_CLASS
                is GcRoot.ThreadBlock -> THREAD_BLOCK
                is GcRoot.MonitorUsed -> MONITOR_USED
                is GcRoot.ThreadObject -> THREAD_OBJECT
                is GcRoot.JniMonitor -> JNI_MONITOR
                else -> throw IllegalStateException("Unexpected gc root $gcRoot")
            }

        }
    }

    /** This field is kept to support backward compatible deserialization. */
    private val elements: List<LeakTraceElement>? = null

    internal fun fromV20(retainedHeapByteSize: Int?) = LeakTrace(
        gcRootType = elements!!.first().gcRootTypeFromV20(),
        referencePath = elements.subList(
            0, elements.lastIndex - 1
        ).map { it.referencePathElementFromV20() },
        leakingObject = elements.last().originObjectFromV20(),
        retainedHeapByteSize = retainedHeapByteSize
    )

    companion object {
        private fun getNextElementString(
            leakTrace: LeakTrace,
            reference: LeakTraceReference,
            index: Int,
            showLeakingStatus: Boolean
        ): String {
            return ""
        }

        private const val ZERO_WIDTH_SPACE = '\u200b'
        private const val serialVersionUID: Long = -6315725584154386429

    }
}