package com.gmail.wizaripost.learning.list.linkedlist

import org.apache.kafka.common.errors.OffsetOutOfRangeException

class WLinkedList {

    @Transient
    var size: Int = 0

    @Transient
    private var first: WLinkedList.Node? = null

    @Transient
    private var last: WLinkedList.Node? = null

    fun WLinkedList() {
    }

    fun size(): Int {
        return this.size
    }

    fun isEmpty(): Boolean {
        return false
    }

    fun contains(o: Any?): Boolean {
        return false
    }

    fun toArray(): Array<Any?> {
        return arrayOfNulls(0)
    }

    fun add(o: Any?): Boolean {
        val node = Node(o as Int, null)
        if (last != null) {
            this.last!!.next = node
            size++
            last = node
        } else if (first != null) {
            this.first!!.next = node
            size++
            last = node
        } else {
            this.first = node
        }
        return true
    }

    fun remove(o: Any?): Boolean {
        return false
    }

    fun addAll(c: Collection<*>): Boolean {
        return false
    }

    fun addAll(index: Int, c: Collection<*>): Boolean {
        return false
    }

    fun clear() {
    }

//    override fun equals(o: Any?): Boolean {
//        return false
//    }
//
//    override fun hashCode(): Int {
//        return 0
//    }

    fun get(index: Int): Int? {
        if (index > size || index < 0 ) {
            return null
        }
        if (index == 0) {
            return this.first!!.item
        }
        if (index == size){
            return this.last!!.item
        }
        var node = this.first!!.next
        for (i in 0 until size) {
            if (index == i + 1) {
                return node!!.item
            }
            node = node!!.next!!
        }
        return null
    }

    fun set(index: Int, element: Any?): Any? {
        return null
    }

    fun add(index: Int, element: Any?) {
    }

    fun remove(index: Int): Any? {
        return null
    }

    fun indexOf(o: Any?): Int {
        return 0
    }

    fun lastIndexOf(o: Any?): Int {
        return 0
    }


    fun removeAll(c: Collection<*>): Boolean {
        return false
    }

    fun containsAll(c: Collection<*>): Boolean {
        return false
    }

    fun toArray(a: Array<Any?>): Array<Any?> {
        return arrayOfNulls(0)
    }


    private class Node(var item: Int, var next: Node?) {

    }


//    fun subList(fromIndex: Int, toIndex: Int): List<Int> {
//        return listOf<Any>()
//    }


//    fun iterator(): Iterator<Int> {
//        return null
//    }

//     fun listIterator(): ListIterator<Int> {
//         return null
//     }

//     fun listIterator(index: Int): ListIterator<Int> {
//         return null
//     }

//     fun retainAll(c: Collection<*>): Boolean {
//         return false
//     }
}