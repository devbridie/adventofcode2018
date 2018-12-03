package com.devbridie.adventofcode2018

import java.lang.invoke.MethodHandles


private val clz = MethodHandles.lookup().lookupClass()
private val loader = clz.classLoader

fun loadInput(path: String) = loader.getResource(path).readText()