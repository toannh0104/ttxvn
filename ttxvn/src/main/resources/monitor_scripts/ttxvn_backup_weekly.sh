#!/bin/bash
#Purpose = Backup of Important Data
#START

# This Command will add date in Backup File Name.
TIME=`date +"%b-%d-%y"` 
# define Backup file name format.
FILENAME="ttxvn-nutch-backup-$TIME.tar.gz" 
# Location of Important Data Directory (Source of backup).
SRCDIR="/tmp/ttxvn_nutch_resources"
# Destination of backup file.
DESDIR="/tmp/ttxvn_nutch_resources_backup_weekly"   
mkdir -p $DESDIR
tar -cpzf $DESDIR/$FILENAME $SRCDIR
#remove 3 days old backup files
find $DESDIR -type f -mtime +6 -exec rm -rf {} \;
 
#END
