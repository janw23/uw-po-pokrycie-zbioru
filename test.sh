#!/bin/bash

progDir=$1
prog=$2
testsDir=$3

testsPassed=()
testsFailed=()

for filename in "${testsDir}"/*.in; do
  [ -e "$filename" ] || continue

  targetOut="${filename%in}out"
  progOut="${filename%in}pout"
  testName=$(basename "${targetOut%.out}")

  echo "Running test: $testName"

  java -ea -cp "$progDir" "$prog" <"$filename" >"$progOut"

  if [ "$(diff "$targetOut" "$progOut")" == "" ]; then
    testsPassed+=("$testName")
  else
    testsFailed+=("$testName")
  fi
done

echo ""

if [ ${#testsPassed[@]} == 0 ]; then
  echo "No tests passed :("
else
  echo "Tests passed:"
  for t in "${testsPassed[@]}"; do
    printf "\t%s\n" "$t"
  done
fi

echo ""

if [ ${#testsFailed[@]} == 0 ]; then
  echo "No tests failed :)"
else
  echo "Tests Failed:"
  for t in "${testsFailed[@]}"; do
    printf "\t%s\n" "$t"
  done
fi