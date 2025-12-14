#!/bin/sh
#
# This script is used to compile your program on CodeCrafters
#
# This runs before .codecrafters/run.sh
#
# Learn more: https://codecrafters.io/program-interface

set -e # commands.builtin.Exit on failure

mvn -q -B package -Ddir=/tmp/codecrafters-build-shell-java
