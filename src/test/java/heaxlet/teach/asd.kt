package heaxlet.teach

/**
 * @author andreiserov
 */

private class Pair<T1, T2>

//fun scrabble(symbols: String, word: String): Boolean {
//    val coll: MutableList<String> = ArrayList(Arrays.stream(symbols.split("".toRegex()).dropLastWhile { it.isEmpty() }
//        .toTypedArray()).toList())
//    return !Arrays.stream(word.lowercase(Locale.getDefault()).split("".toRegex()).dropLastWhile { it.isEmpty() }
//        .toTypedArray())
//        .map { cur: String ->
//            if (!coll.contains(cur)) return@map AppTest.Pair<String, String?>("left", null)
//            coll.remove(cur)
//            AppTest.Pair<String?, String>(null, "right")
//        }
////        .map<String>(Function<heaxlet.teach.Pair<String?, String?>, String> { it: heaxlet.teach.Pair<String?, String?> -> it.t1 })
//        .anyMatch { obj: String? -> Objects.nonNull(obj) }
//}
//class asd {
//}