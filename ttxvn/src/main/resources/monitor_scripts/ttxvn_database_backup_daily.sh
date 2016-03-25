#!/bin/sh
now="$(date +'%d_%m_%Y_%H_%M_%S')"
filename="ttxvn_db_backup_$now".gz
backupfolder="/tmp/ttxvn_db_backup_daily"
fullpathbackupfile="$backupfolder/$filename"
mkdir -p $backupfolder
logfile="$backupfolder/"backup_log_"$(date +'%Y_%m')".txt
echo "mysqldump started at $(date +'%d-%m-%Y %H:%M:%S')" >> "$logfile"
mysqldump --user=ttxuser --password=123456 --default-character-set=utf8 ttxvn | gzip > "$fullpathbackupfile"
echo "mysqldump finished at $(date +'%d-%m-%Y %H:%M:%S')" >> "$logfile"
#chown dev "$fullpathbackupfile"
#chown dev "$logfile"
echo "file permission changed" >> "$logfile"
find "$backupfolder" -name db_backup_* -mtime +3 -exec rm {} \;
echo "old files deleted" >> "$logfile"
echo "operation finished at $(date +'%d-%m-%Y %H:%M:%S')" >> "$logfile"
echo "*****************" >> "$logfile"
exit 0
