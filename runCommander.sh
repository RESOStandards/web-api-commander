#!/bin/bash
echo "Started at: " date
echo "Args: $*"
env
echo "Running Commander..."
echo "certificationRequestId: $1"

certificationPath="/certification/$1"

if [ -n "$1" ]; then
  gradle jar
  echo ""

  echo "Checking for config file: /certification/$1/config.xml"
  ls "/certification/$1/config.xml"
  echo ""

  echo "Changing to Commander Directory"
  cd "/web-api-commander" || exit

  echo "Running Metadata Tests. Command: gradle -DpathToRESOScript=$certificationPath/config.xml"
  gradle jar
  gradle testDataDictionary_1_7 "-DpathToRESOScript=$certificationPath/config.xml" > "$certificationPath/data-dictionary.log"

  status=$?
  if [ $status -eq 1 ]; then
    echo "Testing failed for certificationRequestId: $1"
  else
    echo "Testing succeeded for certificationRequestId: $1"
  fi
  echo ""

  echo "Copying files: cp -R commander.log build/certification $certificationPath"
  cp -R "commander.log" "build/certification" "$certificationPath"
  ls -alh "$certificationPath"
  echo "Copying complete!"

else
  echo "ERROR: certificationRequestId parameter missing from args!"
  return 1
fi
