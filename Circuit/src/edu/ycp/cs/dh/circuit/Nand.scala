package edu.ycp.cs.dh.circuit

object Nand extends Function {
	def compute(a: Boolean, b: Boolean): Boolean = !(a && b)
}