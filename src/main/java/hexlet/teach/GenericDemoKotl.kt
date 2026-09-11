package hexlet.teach

/**
 * Returns the indices of the two numbers in [nums] that add up to [target].
 *
 * The first index is always the smaller one. Returns an empty array when no
 * such pair exists.
 */
fun twoSum(nums: IntArray, target: Int): IntArray {
    val seen = HashMap<Int, Int>()
    nums.forEachIndexed { index, value ->
        seen[target - value]?.let { return intArrayOf(it, index) }
        seen.putIfAbsent(value, index)
    }
    return IntArray(0)
}
