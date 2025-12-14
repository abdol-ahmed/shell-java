#!/bin/sh
#
# This script is used to run your program on CodeCrafters
#
# This runs after .codecrafters/compile.sh
#
# Learn more: https://codecrafters.io/program-interface

set -e # commands.builtin.Exit on failure

exec java --enable-preview -jar /tmp/codecrafters-build-shell-java/codecrafters-shell.jar "$@"
