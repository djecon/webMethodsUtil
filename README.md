"# webMethodsUtil" 
getOsInfo is a Java code example of how to obtain some useful information from the OS etc. This utility can be used to extract the information like:
systemLoadAverage
JVMmaxMemory
JVMUsedMemory
JVMFreeMemory
JVMStartDate
JVMUptimeSeconds
currentThreadCount
peakThreadCount
totalDiskSpace
freeDiskSpace

ConvertStatsLogs.java will convert webMethods Integration Server stats.log hex to decimal values. To run, download the java code, compile (javac ConvertStatsLogs.java) and then run (java ConvertStatsLogs). Command line switches are as follows:
-i "input file name" (required, usually stats.log etc)
-o "output file name" (optional, if not provided, the input file name will be used and suffixed with .out)


