all: test

test:
	./sbt test

test-continuous:
	./sbt ~test

console:
	./sbt console

idea:
	./sbt gen-idea

eclipse:
	./sbt eclipse
