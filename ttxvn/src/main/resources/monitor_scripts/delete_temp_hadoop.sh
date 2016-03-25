#!/bin/bash
#Purpose = Backup of Important Data
#START

# Destination of backup file.
DESDIR="/tmp/hadoop-dev/mapred/local/taskTracker/dev/jobcache"   

#remove 3 days old backup files
find $DESDIR -type f -mtime +3 -exec rm -rf {} \;
 
#END
