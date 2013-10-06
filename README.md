clojure-projects
===============

General repository for this fuctional programming thing. If anything gets particularly mind blowing I will make it its own rep.

steps I took and places I am finding useful 
	dowload clojure, and make a .bat for running clojure files straight up:
	-------------------------------------------------------------------------
	by the way I did this all on windows, more exhaustive instructions here
	 http://en.wikibooks.org/wiki/Clojure_Programming/Getting_Started
	-------------------------------------------------------------------------
	1) http://clojure.org/downloads (gives you a zip; extract it)
	2) add the location of the clojure .jar to path (google if you aren't familiar with changing your path)
	3) create a file in the same location (just for convenience) named clj.bat and put in it:

		set CLOJURE_DIR=C:\Users\Ben\Dropbox\clojure-1.5.1
	    set CLOJURE_JAR=%CLOJURE_DIR%\clojure-1.5.1.jar
	 
	    if (%1) == () (
	        :: Start REPL
	        java -cp .;%CLOJURE_JAR% clojure.main
	    ) else (
	        :: Start some_script.clj
	        java -cp .;%CLOJURE_JAR% clojure.main %1 -- %*
	    )

	    4) now running clj from anywhere will start her up. you can also run a repl by giving no args

	    5) while this is tons of fun people tend to recommend using leiningen (http://leiningen.org/)



	download and install leiningen -- http://leiningen-win-installer.djpowell.net/ (windows)
	-------------------------------------------------------------------------
	other instructions for other OS's are on leiningen.org landing page
	-------------------------------------------------------------------------


	now what to do?
	some neat resources on clojure:
			{resources:
			[http://clojure.org/documentation
			http://clojure.org/cheatsheet
			http://clojuredocs.org/		
			http://cognitect.com/clojure (clojure is written by them and they have neat tools for it)
			http://himera.herokuapp.com/index.html (clojurescript stuff, clojure can be js too!)]

			tutorials:
			[http://assets.en.oreilly.com/1/event/27/Clojure_%20Functional%20Concurrency%20for%20the%20JVM%20Presentation.pdf
			http://java.ociweb.com/mark/clojure/article.html]}
