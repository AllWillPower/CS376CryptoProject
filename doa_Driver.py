# shift f10 to execute

import mysql.connector

db = mysql.connector.connect(
    user="root",
    passwd="CSUWEC",
    host="35.202.111.69",
    db="spamFilterData")

cursor = db.cursor()

query = ("SELECT * FROM baseData")

cursor.execute(query)

for (message) in cursor:
  print(message)



db.close()
