package shark


/**
 * Enables [ObjectInspector] implementations to provide insights on [heapObject], which is
 * an object (class, instance or array) found in the heap.
 *
 * A given [ObjectReporter] only maps to one object in the heap, but is shared to many
 * [ObjectInspector] implementations and accumulates insights.
 */
class ObjectReporter constructor() {


}