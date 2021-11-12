#!/bin/bash

FILE="hackathon-core/src/main/resources/db/devices.sql"

if [ -f $FILE ]; then
   rm $FILE
   echo "$FILE is removed"
fi

multilin=$(adb devices)

splitStr=$(echo $multilin | tr " " "\n")

array=()
sqlArray=()

for str in $splitStr
do

	if [[ $str != "List" ]] && [[ $str != "of" ]] && [[ $str != "devices" ]] && [[ $str != "attached" ]] && [[ $str != "device" ]] && [[ $str != "offline" ]];
		then
			array+=("'"$str"'")
	fi
done

echo "Total ${#array[*]} device/s found.."
echo "Devices: ${array[*]}"
echo "Generating SQL for INSERT..."

#/bin/cat <<EOM >$FILE

for (( i=0 ; i<${#array[*]}; i++ ))
do
  echo "INSERT INTO devices(deviceName, version, appium_url, uid, port, status) VALUES ("${array[i]}", '11.0.0', '0.0.0.0',"${array[i]},$((10000 + $RANDOM % 99999)),"'IDLE')"
	sqlArray+=("INSERT INTO devices(deviceName, version, appium_url, uid, port, status) VALUES ("${array[i]}", '11.0.0', '0.0.0.0',"${array[i]},$((10000 + $RANDOM % 99999)),"'IDLE')")
done



for (( i=0 ; i<${#sqlArray[*]}; i++ ))
do
	printf "${sqlArray[i]}\n" >> $FILE
done

chmod 777 $FILE