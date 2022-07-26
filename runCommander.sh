#!/bin/bash
echo "Started at: " date
echo "Args: $*"
env
echo "Running Commander..."
echo "jobId: $AWS_BATCH_JOB_ID"
echo "jobQueue: $AWS_BATCH_JQ_NAME"

if [ -n "$1" ]; then
  echo "gradle -DpathToRESOScript=$1/config.xml"
  gradle testDataDictionaryReferenceMetadata_1_7

  status=$?
  if [ $status -eq 1 ]; then
    echo "failed"
  else
    echo "passed"
  fi

  echo "cp -R commander.log build/certification $1"
  ls -alh "$1"
else
  echo "ERROR: Results path parameter missing from args!"
  return 1
fi