#!/bin/bash
echo "Started at: " date
echo "Args: $*"
env
echo "Running Commander..."
#echo "jobId: $AWS_BATCH_JOB_ID"
#echo "jobQueue: $AWS_BATCH_JQ_NAME"
echo "certificationRequestId: $1"

certificationPath="/certification/$1"

if [ -n "$1" ]; then
  echo "gradle -DpathToRESOScript=$certificationPath/config.xml"
  gradle testDataDictionaryReferenceMetadata_1_7

  status=$?
  if [ $status -eq 1 ]; then
    echo "failed"
  else
    echo "passed"
  fi

  echo "cp -R commander.log build/certification $certificationPath"
  ls -alh "$certificationPath"
else
  echo "ERROR: certificationRequestId parameter missing from args!"
  return 1
fi