package edu.ycp.cs.dh.circuit

object Circuit {
  val OUTPUT_DEV_NAME = "out"
}

// Represents a boolean circuit consisting of some number of Devices.
// The list of Connections specifies how the outputs are connected to
// the device inputs.  One device should be specified as the output
// device.
class Circuit(val devices : List[Device], val connections : List[Connection]) {
  // Create an empty Circuit.
  // Useful as a starting point.
  def this() = {
    this(List(), List())
  }
  
  // Produce a new Circuit by adding a device to this Circuit.
  def addDevice(device : Device) : Circuit = {
    return new Circuit(device :: devices, connections)
  }
  
  // Produce a new Circuit by adding a Connection to this circuit.
  def addConnection(connection : Connection) : Circuit = {
    return new Circuit(devices, connection :: connections)
  }
  
  // Given map of inputs to their values, compute the Boolean
  // result produced by the output device.
  def compute(inputs : Map[String, Boolean]) : Boolean = {
    // Set each device output to false
    val initDeviceOutputs = devices.map(device => (device.name, false)).toMap
    
    // Build a map of device names to devices
    val deviceNameToDeviceMap = devices.map(device => (device.name, device) ).toMap
    
    // Build a map of (device name, input) pairs to Connections
    val deviceNameAndInputToConnectionMap = connections.map(
        connection => ((connection.inputDeviceName, connection.input), connection)
    ).toMap
    
    // Iteratively compute outputs until none of the outputs change,
    // then return the output of the circuit output device
    computeWork(deviceNameToDeviceMap, deviceNameAndInputToConnectionMap, inputs, initDeviceOutputs)
  }
  
  def computeWork(
      deviceNameToDeviceMap : Map[String, Device],
      deviceNameAndInputToConnectionMap : Map[(String, Int), Connection],
      inputs : Map[String, Boolean],
      deviceOutputs : Map[String, Boolean]) : Boolean = {
    
    // Compute device outputs
    val nextOutputs = devices.map(device => {
      val a = output(deviceNameAndInputToConnectionMap((device.name, 0)).outputDeviceName, inputs, deviceOutputs)
      val b = output(deviceNameAndInputToConnectionMap((device.name, 1)).outputDeviceName, inputs, deviceOutputs)
      val out = device.function.compute(a, b)
      
      (device.name, out)
    }).toMap
    
    // Did device outputs change?
    // If so, return the value of the circuit output device.
    // Otherwise, keep computing on the next outputs.
    if (deviceOutputs == nextOutputs)
      output(Circuit.OUTPUT_DEV_NAME, inputs, deviceOutputs)
    else
      computeWork(deviceNameToDeviceMap, deviceNameAndInputToConnectionMap, inputs, nextOutputs)
  }
  
  def output(deviceName : String, inputs : Map[String, Boolean], deviceOutputs : Map[String, Boolean]) : Boolean = {
    if (inputs.contains(deviceName))
      inputs(deviceName)
    else
      deviceOutputs(deviceName)
  }
}