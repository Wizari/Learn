package com.gmail.wizaripost.learning.claude.lesson004.q123

import org.springframework.stereotype.Service

@Service
class Utils {
    fun hasDuplicates(list: List<Int>): Boolean {
        val set = mutableSetOf<Int>()
        var result: Boolean
        for (element in list) {
            result = set.add(element)
            if (!result) {
                return true
            }
        }
        return false
    }

    fun wordFrequency(words: List<String>): Map<String, Int> {
        val wordsMap: MutableMap<String, Int> = mutableMapOf()
        for (word in words) {
            wordsMap[word] = wordsMap.getOrDefault(word, 0) + 1
        }
        return wordsMap
    }

    fun mergeCounts(
        a: Map<String, Int>,
        b: Map<String, Int>,
    ): Map<String, Int> {
        var result: MutableMap<String, Int> = mutableMapOf()
        result.putAll(a)

        for (elementB in b) {
            result[elementB.key] = result.getOrDefault(elementB.key, 0) + elementB.value
        }
        return result.toMap()
    }
}
