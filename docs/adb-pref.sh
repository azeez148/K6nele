#!/bin/bash

# Example of getting/putting Kõnele preferences
# Note that the activity must be exported for this to work.

if [ $# -lt 1 ]
then
    echo "Usage examples:"
    echo "adb-pref.sh keyHelpText"
    echo "adb-pref.sh keyHelpText --ez val true"
    echo "adb-pref.sh keyMaxResults -e val 10 # For some prefs, ints are passed as strings"
    echo "adb-pref.sh defaultRewriteTables --esa val Base,ActivityCommands,ImeCommands"
    echo "adb-pref.sh defaultRewriteTables --esn val # Setting to null"
    exit
fi

echo "adb shell am start -n ee.ioc.phon.android.speak/.activity.GetPutPreferenceActivity -e key $1 ${@:2}"
adb shell am start -n ee.ioc.phon.android.speak/.activity.GetPutPreferenceActivity -e key $1 ${@:2}
