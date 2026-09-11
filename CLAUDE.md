# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this repo is

A personal learning/practice sandbox for Java and Kotlin (Hexlet course exercises). Files are grouped
by topic (generics, streams, sorting algorithms, LeetCode-style problems, debugging tricks, etc.), not
by application layers — there is no production app or single entry point. Many `README.md` files inside
`src/main/java/hexlet/teach/**` are informal Russian-language study notes for a given topic, not
project documentation.

## Build system

The project has both a Gradle build (`build.gradle.kts`) and a stray `pom.xml`. **Gradle is the one
actually used** — the `pom.xml` is a bare, unused stub (no dependencies/plugins configured). Don't try
to build with Maven.

- Build: `./gradlew build`
- Run all tests: `./gradlew test`
- Run a single test class: `./gradlew test --tests "hexlet.teach.sort.BubleSortTest"`
- Run a single test method: `./gradlew test --tests "hexlet.teach.sort.BubleSortTest.methodName"`

Toolchain: Java 21, Kotlin 2.4.20 (JVM target 21), JUnit 5 (Jupiter) + AssertJ for assertions.

## Package layout quirk

Code lives under **two different top-level packages that are easy to mistake for a typo**:

- `hexlet.teach` — the primary package.
- `heaxlet.teach` — a second, separate package (note the swapped letters), used in parallel in both
  `src/main/java/heaxlet/teach` conceptually and, more commonly, `src/test/java/heaxlet/teach`.

This split is intentional/pre-existing in the repo, not a mistake to "fix" — do not rename
`heaxlet` to `hexlet` (or vice versa) as a cleanup unless explicitly asked, since it would break
existing file/package alignment across main and test sources.

## Code organization

- `src/main/java/hexlet/teach/archive/` — older demo classes (repository/DTO/mapper patterns, equals demos).
- `src/main/java/hexlet/teach/stream/`, `debug_tricks/`, `arrays/`, `max_array_problem/` — topic-scoped demos, each mirrored by a topic README where present.
- `src/test/java/hexlet/teach/**` and `src/test/java/heaxlet/teach/**` — JUnit tests, often paired 1:1 with a solution class of the same name (e.g. `SubarraySumEqualsK.java` exists under both `main` and `test` trees as separate exercises/attempts).
- Kotlin and Java sources coexist directly alongside each other in the same package directories (e.g. `kalia.kt`, `GenericDemoKotl.kt` next to `.java` files).

Dependencies include Ktor client (`kalia.kt` demonstrates an HTTP client with coroutines) and Jackson,
used for isolated topic demos rather than a cohesive app.
