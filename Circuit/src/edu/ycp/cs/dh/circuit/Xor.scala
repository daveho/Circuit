package edu.ycp.cs.dh.circuit

object Xor {
  def compute(a: Boolean, b: Boolean): Boolean = (a || b) && !(a && b)
}