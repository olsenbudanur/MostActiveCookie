# How To Use
1) Download the MostActiveCookie.jar file and move it to the same directory as the cookie log.
2) Navigate to the directory of the MostActiveCookie.jar file through your terminal
3) Type "java -jar MostActiveCookie.jar 'NAMEOFCOOKIELOGFILE' 'TIMEZONETAG' 'DATE'" to the terminal
4) The application will print the most active cookie(s) in your terminal

NAMEOFCOOKIELOGFILE : the name of the cookie log file (including the extension) <br />
TIMEZONETAG : currently, the application only supports the tag "-d" for this argument <br />
DATE : Date in the format YYYY-MM-DD <br />

Note : The source code is in the "src" directory, feel free to compile the .java files and run the jUnit tests. The "doc" directory stores the automatically generated JavaDoc by Eclipse IDE.

# Example Usage
Line: java -jar MostActiveCookie.jar cookie_log.csv -d 2018-12-08
![alt text](https://github.com/olsenbudanur/MostActiveCookie/blob/Screen Shot 2021-12-25 at 11.44.47 AM.png?raw=true)
