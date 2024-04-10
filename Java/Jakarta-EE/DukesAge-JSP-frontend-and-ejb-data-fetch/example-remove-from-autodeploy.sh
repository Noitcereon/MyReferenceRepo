#!/usr/bin/env bash

# Should reflect where your Application Server's autodeploy directory is.
AUTODEPLOY_DIRECTORY="C:\Oracle\Middleware\Oracle_Home\user_projects\domains\practice_domain\autodeploy"

ECHO "Removing my-dukes-age.war from autodeploy directory"
rm  "$AUTODEPLOY_DIRECTORY"/my-dukes-age*.war && ECHO "Removed my-dukes-age.war successfully"

ECHO "Removing my-firstcup.war from autodeploy directory"
rm  "$AUTODEPLOY_DIRECTORY"/my-firstcup*.war && ECHO "Removed my-firstcup.war successfully"

ECHO ""
ECHO "Remove from autodeploy script finished executing."
ECHO "Closing in 10 seconds or when ENTER is pressed."
read -t 10
exit 0