package edu.ycp.cs.dh.circuit

object Main {
	def main(args: Array[String]) {
	  val circuit = new Circuit()
	  	.addDevice(new Device(Nand, Circuit.OUTPUT_DEV_NAME))
	  	.addConnection(new Connection("A", Circuit.OUTPUT_DEV_NAME, 0))
	  	.addConnection(new Connection("B", Circuit.OUTPUT_DEV_NAME, 1))
	  	
	  val out = circuit.compute(Map("A" -> true, "B" -> true))
	  
	  println(out)
	}
}