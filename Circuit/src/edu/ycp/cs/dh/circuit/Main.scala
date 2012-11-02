package edu.ycp.cs.dh.circuit

object Main {
	def main(args: Array[String]) {
	  val circuit = new Circuit()
	  	.addDevice(new Device(Nand, Circuit.OUTPUT_DEV_NAME))
	  	.addDevice(new Device(Nand, "g0"))
	  	.addConnection(new Connection("A", "g0", 0))
	  	.addConnection(new Connection("B", "g0", 1))
	  	.addConnection(new Connection("g0", Circuit.OUTPUT_DEV_NAME, 0))
	  	.addConnection(new Connection("C", Circuit.OUTPUT_DEV_NAME, 1))
	  	
	  val out = circuit.compute(Map("A" -> false, "B" -> true, "C" -> true))
	  
	  println(out)
	}
}