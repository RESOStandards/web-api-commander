#!/bin/bash
echo "Started at: " date
echo "Args: $*"
env
echo "Running Commander..."
echo "certificationRequestId: $1"

certificationPath="/certification/$1"

if [ -n "$1" ]; then
#  gradle jar

  echo "Mounts"
  df -h
  echo

  echo "ls /certification"
  ls /certification
  echo

  echo "./gradle -DpathToRESOScript=$certificationPath/config.xml"
  ./gradlew testDataDictionaryReferenceMetadata_1_7
  echo

  status=$?
  if [ $status -eq 1 ]; then
    echo "Testing failed for certificationRequestId: $1"
  else
    echo "Testing succeeded for certificationRequestId: $1"
  fi
  echo

  echo "cp -R commander.log build/certification $certificationPath"
  ls -alh "$certificationPath"
  echo

else
  echo "ERROR: certificationRequestId parameter missing from args!"
  return 1
fi
