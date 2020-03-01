package leakcanary.lahm.emptyleakcanary

import shark.HeapAnalysisSuccess
import shark.LeakTrace
import leakcanary.DefaultOnHeapAnalyzedListener
import leakcanary.OnHeapAnalyzedListener
import shark.HeapAnalysis

/**
 * Project Name:EmptyLeakCanary
 * Package Name:leakcanary.lahm.emptyleakcanary
 * Created by lahm on 2020-03-01 17:39 .
 *
 * More code see my github https://github.com/lamster2018
 */

class TestLeakMemUploader : OnHeapAnalyzedListener {
    val defaultListener = DefaultOnHeapAnalyzedListener.create()

    override fun onHeapAnalyzed(heapAnalysis: HeapAnalysis) {
        when (heapAnalysis) {
            //如果是成功解析
            is HeapAnalysisSuccess -> {
                val logPath = "leak/"
                //总是拿最新的一个leak
                val selectedLeak = heapAnalysis.allLeaks.first()
                val leakTrace = selectedLeak.leakTraces.first()
            }
        }
        defaultListener.onHeapAnalyzed(heapAnalysis)

    }

    private fun leakToString(
        leakTrace: LeakTrace,
        analysis: HeapAnalysisSuccess
    ) = """$leakTrace

METADATA

${if (analysis.metadata.isNotEmpty()) analysis.metadata.map { "${it.key}: ${it.value}" }.joinToString(
        "\n"
    ) else ""}
Analysis duration: ${analysis.analysisDurationMillis} ms"""

}