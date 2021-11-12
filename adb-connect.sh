#!/bin/bash

adb kill-server && adb start-server && sleep 3

devices="$(adb -H host.docker.internal devices | cut -f1 | sed 1d)"

for device in $devices; do
 random_port="$(shuf -i 1000-10000 -n 1)"
 adb_tcpip="adb -H host.docker.internal -s $device tcpip $random_port"
 $adb_tcpip
 sleep 5
 IP="$(adb -H host.docker.internal -s $device shell ip route | awk '{print $9}')"
 adb_connect="adb -s $device connect $IP:$random_port"
 $adb_connect && sleep 3
done