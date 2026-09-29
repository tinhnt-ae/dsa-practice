.PHONY: help
help:
	@echo "make create-patterns/<pattern>/<ProblemName>   scaffold a new problem"
	@echo "make run-patterns/<pattern>/<ProblemName>      compile + run its tests"

create-patterns/%:
	@dir="patterns/$*"; \
	name=$$(basename "$$dir"); \
	if [ -f "$$dir/Solution.java" ]; then \
		echo "Already exists, not touching: $$dir/Solution.java"; \
	else \
		mkdir -p "$$dir"; \
		printf 'public class Solution {\n    // TODO: implement %s\n}\n' "$$name" > "$$dir/Solution.java"; \
		printf 'public class %sTest {\n    public static void main(String[] args) {\n        Solution s = new Solution();\n\n        // TODO: add test cases, e.g.\n        // TestRunner.check(s.someMethod(...), expectedValue, "case description");\n\n        TestRunner.summary();\n    }\n}\n' "$$name" > "$$dir/$${name}Test.java"; \
		echo "Created $$dir/Solution.java and $$dir/$${name}Test.java"; \
	fi

run-patterns/%:
	@dir="patterns/$*"; \
	name=$$(basename "$$dir"); \
	mkdir -p "$$dir/out"; \
	javac -d "$$dir/out" util/TestRunner.java "$$dir"/*.java && \
	java -cp "$$dir/out" "$${name}Test"

%:
	@: