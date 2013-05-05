cd ..
set CLASSPATH=bin/
set JAVA_OPTS=-DLanguage=java
java -Xms32m -Xmx512m -XX:+UseConcMarkSweepGC -XX:NewRatio=2 -XX:SurvivorRatio=2 -verbose:gc -classpath %CLASSPATH% %JAVA_OPTS% fr.gouv.finances.cp.helios.tools.DeleteComment

rem java -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.port=8463 -XX:+UseConcMarkSweepGC -XX:NewRatio=2 -XX:SurvivorRatio=2 -classpath %CLASSPATH% %JAVA_OPTS% fr.gouv.finances.cp.helios.tools.DeleteComment

