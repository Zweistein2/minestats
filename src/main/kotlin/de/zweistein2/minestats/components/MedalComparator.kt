package de.zweistein2.minestats.components

class MedalComparator: Comparator<Pair<String, Triple<Int, Int, Int>>> {
    override fun compare(o1: Pair<String, Triple<Int, Int, Int>>, o2: Pair<String, Triple<Int, Int, Int>>): Int {
        val o1Medals = o1.second
        val o2Medals = o2.second

        return if(o1Medals.first.compareTo(o2Medals.first) == 0) {
            if(o1Medals.second.compareTo(o2Medals.second) == 0) {
                o1Medals.third.compareTo(o2Medals.third)
            } else {
                o1Medals.second.compareTo(o2Medals.second)
            }
        } else {
            o1Medals.first.compareTo(o2Medals.first)
        }
    }
}