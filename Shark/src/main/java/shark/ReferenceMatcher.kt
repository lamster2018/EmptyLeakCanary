package shark

/**
 * Used to pattern match known patterns of references in the heap, either to ignore them
 * ([IgnoredReferenceMatcher]) or to mark them as library leaks ([LibraryLeakReferenceMatcher]).
 */
sealed class ReferenceMatcher {

  /** The pattern that references will be matched against. */
  abstract val pattern: ReferencePattern

}

