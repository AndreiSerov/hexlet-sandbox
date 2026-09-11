---
name: leetcode-scaffold
description: Scaffold a new LeetCode practice file in this repo from a problem URL or name, following this repo's exact stub template — problem javadoc, an UNIMPLEMENTED method body (todo, no solution), and two @Test cases hardcoded from the problem's own examples. Use this whenever the user pastes a leetcode.com URL, names a LeetCode problem, or asks to "add a practice problem" / "scaffold a leetcode exercise" for this sandbox. Do NOT solve the problem — this skill is for generating the stub only, leaving the actual algorithm as an exercise.
---

# LeetCode scaffold

Generates one new practice file per problem in `src/test/java/heaxlet/teach/`, matching the
existing style of files like `BestTimeToBuyAndSellStock.java`, `ContainerWithMostWater.java`, and
`SubarraySumEqualsK.java` in that directory.

**The whole point of this skill is to leave the problem unsolved.** A prior attempt at this task
wrote a working solution instead of a stub, and the user reverted it — solving the problem defeats
the purpose, since these files exist for the user to practice on later. Never fill in the method
body, even if the algorithm seems obvious or trivial.

## Steps

1. **Get the problem statement.** If given a `leetcode.com/problems/...` URL, fetch it (WebFetch)
   to get the problem description, the example inputs/outputs, and the function signature LeetCode
   expects (visible in its code snippets, e.g. `public int maxProfit(int[] prices)`). If only given
   a problem name, search for its LeetCode URL first, then fetch it the same way.

2. **Derive the class name.** Convert the problem's title to PascalCase with no spaces or
   punctuation, matching the existing files' pattern (e.g. "Container With Most Water" →
   `ContainerWithMostWater`, "Best Time to Buy and Sell Stock" → `BestTimeToBuyAndSellStock`).

3. **Write the file** at `src/test/java/heaxlet/teach/<ClassName>.java` using the template below.
   Only this one file gets created — don't touch `build.gradle.kts`, `CLAUDE.md`, or any other file.

## Template

```java
package heaxlet.teach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class <ClassName> {


    /**
     * <One short paragraph paraphrasing the problem statement in your own words — not a
     * copy-paste of LeetCode's wording.>
     */
    public <returnType> <methodName>(<params>) {
        // todo


        return <defaultValue>;
    }


    @Test
    void test1() {
        assertEquals(
                <expectedOutput1>,
                <methodName>(<inputFromExample1>)
        );
    }

    @Test
    void test2() {
        assertEquals(
                <expectedOutput2>,
                <methodName>(<inputFromExample2>)
        );
    }
}
```

### Filling in the template

- **Package is `heaxlet.teach`, not `hexlet.teach`.** This repo has two parallel top-level
  packages (`hexlet.teach` and `heaxlet.teach` — note the swapped letters). It's intentional and
  pre-existing, not a typo — always use `heaxlet.teach` for these scaffolded files, matching the
  existing sibling files in that directory.
- **Method signature**: use LeetCode's own signature for the problem when it's visible in the
  fetched page (parameter names, types, and the return type). If it isn't available, pick a
  sensible Java signature and a camelCase method name that describes what it computes.
- **Method body**: always `// todo` followed by a blank line and a `return` of a plausible default
  for the return type — `0` for numeric, `false` for boolean, `null` for objects, `new int[0]` (or
  similarly typed empty array) for arrays that would otherwise be null-unsafe in the tests, etc.
  Never write the actual algorithm.
- **Javadoc**: paraphrase the core of the problem in one short paragraph — enough for the user to
  remember what they're solving without re-reading LeetCode, but not a verbatim copy.
- **Tests**: use the *first two* worked examples from the LeetCode problem statement (usually
  labeled "Example 1" and "Example 2"). Hardcode their inputs and expected outputs. If a problem
  only has one canonical example, look for a second case in the problem's constraints or edge
  cases (e.g. an empty/trivial input) rather than inventing an arbitrary one. Keep the
  `assertEquals(expected, actual)` argument order and the multi-line call style shown above — it's
  the convention every existing file in this directory follows.

## Example

For https://leetcode.com/problems/two-sum/, the result would be
`src/test/java/heaxlet/teach/TwoSum.java`:

```java
package heaxlet.teach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TwoSum {


    /**
     * Given an array of integers and a target, return the indices of the two numbers that add up
     * to the target. Each input has exactly one solution, and the same element can't be used twice.
     */
    public int[] twoSum(int[] nums, int target) {
        // todo


        return new int[0];
    }


    @Test
    void test1() {
        assertEquals(
                0,
                twoSum(new int[]{2, 7, 11, 15}, 9)[0]
        );
    }

    @Test
    void test2() {
        assertEquals(
                1,
                twoSum(new int[]{3, 2, 4}, 6)[0]
        );
    }
}
```

(Array-returning problems are the one case where comparing the whole array with `assertEquals`
gets awkward for a stub — indexing into the result like above, or using `assertArrayEquals` if the
existing file style ever adopts it, both stay faithful to the template's intent.)
