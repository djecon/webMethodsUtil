import os

inFile = open('/pathToFile/stats.log','r')
outFile = open('/pathToFile/statsConverted.csv','w+')
counter = 0
lineOut = ""

if counter == 0:
    lineOut = 'Date,Time,Zone,JVMTotalMem,JVMFreeMem,CurrentThreads,MaxThreads,SSNs,SSNx,SSNAvg,REQs,REQx,REQAvg,StartReq,EndReq' \
              + '\n'
    outFile.write(lineOut)

for line in inFile:
    line = line.strip() + '\n'
    if len(line) > 1:
        inSplit = line.split()
        if inSplit[4] == "Start":  # get rid of start line
            continue
        # convert hex values to decimal
        inSplit[3] = (int(inSplit[3], 16))
        inSplit[4] = (int(inSplit[4], 16))
        inSplit[5] = (int(inSplit[5], 16))
        inSplit[6] = (int(inSplit[6], 16))
        inSplit[7] = (int(inSplit[7], 16))
        inSplit[8] = (int(inSplit[8], 16))
        inSplit[9] = (int(inSplit[9], 16))
        inSplit[10] = (int(inSplit[10], 16))
        inSplit[11] = (int(inSplit[11], 16))
        inSplit[12] = (int(inSplit[12], 16))
        inSplit[13] = (int(inSplit[13], 16))
        inSplit[14] = (int(inSplit[14], 16))

        lineOut =   str(inSplit[0]) + ',' + \
                    str(inSplit[1]) + ',' + \
                    str(inSplit[2]) + ',' + \
                    str(inSplit[3]) + ',' + \
                    str(inSplit[4]) + ',' + \
                    str(inSplit[5]) + ',' + \
                    str(inSplit[6]) + ',' + \
                    str(inSplit[7]) + ',' + \
                    str(inSplit[8]) + ',' + \
                    str(inSplit[9]) + ',' + \
                    str(inSplit[10]) + ',' + \
                    str(inSplit[11]) + ',' + \
                    str(inSplit[12]) + ',' + \
                    str(inSplit[13]) + ',' + \
                    str(inSplit[14])  + '\n'

        counter = counter + 1
        outFile.write(lineOut)

print("Logs Entries Converted: " + str(counter))

inFile.close()
outFile.close()
