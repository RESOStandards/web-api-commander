#!/bin/bash
echo "Job: RESO Data Dictionary Testing"
echo "Started at: " date
echo "Args: $*"
env

if [ -z "$1" ]; then
  echo "ERROR: 'certificationRequestId' parameter missing from args!"
  exit 1
fi

if [ -z "$2" ]; then
  echo "ERROR: 'certificationCommand' parameter missing from args!"
  exit 1
fi

certificationCommand="$1"
certificationRequestId="$2"
certificationPath="/certification/$certificationRequestId"

echo "Running Commander..."
echo "certificationCommand: $certificationCommand"
echo "certificationRequestId: $certificationRequestId"
echo "certificationPath: $certificationPath"

echo "Checking for config file: $certificationPath/config.xml"
ls "$certificationPath/config.xml"

status=$?
if [ $status -eq 1 ]; then
  echo "ERROR: Could not find config file!"
  exit 1
fi

echo "Changing to Commander Directory"
cd "/web-api-commander" || exit 1

echo "Running Tests! Command: gradle $certificationCommand -DpathToRESOScript=$certificationPath/config.xml"
gradle "$certificationCommand" "-DpathToRESOScript=$certificationPath/config.xml" #> "$certificationPath/$certificationCommand.log"

status=$?
if [ $status -eq 1 ]; then
  echo "ERROR: Command '$certificationCommand' failed for certificationRequestId: $certificationRequestId"
  exit 1
else
  echo "SUCCESS: Command '$certificationCommand' succeeded for certificationRequestId: $certificationRequestId"
fi

echo "Copying files: cp -R commander.log build/certification $certificationPath"
cp -R "commander.log" "build/certification" "$certificationPath"

status=$?
if [ $status -eq 1 ]; then
  echo "ERROR: Could not copy files to '$certificationPath'!"
  exit 1
else
  echo "Files copied!"
fi

ls -alh "$certificationPath"
status=$?
if [ $status -eq 1 ]; then
  echo "ERROR: Could not list files in '$certificationPath'!"
  exit 1
else
  echo "Files copied!"
fi

echo "Testing complete!"


