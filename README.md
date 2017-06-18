# javaTCPMailSniffer

Vorraussetzung:
---------------------------
- WinPcap Installation
- jNetPcap Ordner
- Java


Zum Starten der jar:
---------------------------------

jnetpcap Ordner herunterladen

java -jar run_sniffer.jar -Djava.library.path=<pfad_zum_jnetpcap_ordner>


Zum Starten in Eclipse:
--------------------------------

VM Argument
-Djava.library.path=$JNETPCAP_HOME
Die Var $JNETPCAP_HOME ist der Pfad zur jnetpcap.dll oder kompletten Pfad direkt anhängen.
