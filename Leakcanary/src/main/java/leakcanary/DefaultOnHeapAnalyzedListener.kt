package leakcanary

import shark.HeapAnalysis

/**
 * Default [OnHeapAnalyzedListener] implementation, which will store the analysis to disk and
 * show a notification summarizing the result.
 */
class DefaultOnHeapAnalyzedListener : OnHeapAnalyzedListener {

    override fun onHeapAnalyzed(heapAnalysis: HeapAnalysis) {
    }

    companion object {
        fun create(): OnHeapAnalyzedListener =
            DefaultOnHeapAnalyzedListener()
    }
}