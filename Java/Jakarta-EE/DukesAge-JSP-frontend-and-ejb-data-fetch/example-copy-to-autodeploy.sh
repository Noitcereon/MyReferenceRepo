#!/usr/bin/env bash

# Should reflect where your Application Server's autodeploy directory is.
AUTODEPLOY_DIRECTORY="C:\Oracle\Middleware\Oracle_Home\user_projects\domains\practice_domain\autodeploy"

ECHO "Copying my-dukes-age.war to autodeploy directory"
cp -p "my-dukes-age/target/my-dukes-age"*.war $AUTODEPLOY_DIRECTORY && ECHO "Copied my-dukes-age.war successfully"

ECHO "Copying my-firstcup.war to autodeploy directory"
cp -p "my-firstcup/target/my-firstcup"*.war $AUTODEPLOY_DIRECTORY && ECHO "Copied my-firstcup.war successfully"

ECHO ""
ECHO "Autodeploy script finished executing"
ECHO "Closing in 10 seconds or when ENTER is pressed."
read -t 10
exit 0