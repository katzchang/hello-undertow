all: test

test:
	./sbt test

test-continuous:
	./sbt ~test

run:
	./sbt run

console:
	./sbt console

idea:
	./sbt gen-idea

eclipse:
	./sbt eclipse
