cli: build
	java -cp bin hk.edu.polyu.comp.comp2021.clevis.Application -txt log.txt -html log.html

gui: build
	java -cp bin hk.edu.polyu.comp.comp2021.clevis.Application -txt log.txt -html log.html -gui

build:
	javac -d bin -sourcepath src src/hk/edu/polyu/comp/comp2021/clevis/Application.java
