package edu.ycp.cs.dh.circuit

object Generate {
	val ALL_GATES = List(And, Or, Nand, Nor, Xor)
  
	def withDevices(numDevices : Int) : List[Circuit] = {
	  allDeviceCombos(numDevices).map(deviceList => new Circuit(deviceList, List()))
	}
	
	def allDeviceCombos(numDevices : Int) : List[List[Device]] = {
	  // TODO
	  List()
	}
	
 
}