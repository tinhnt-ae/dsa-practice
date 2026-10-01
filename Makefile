SHELL := /bin/sh

BUILD_DIR := .build
PATTERNS_DIR := patterns
TEST_RUNNER := utils/TestRunner.java

.PHONY: help create run run-all clean

help:
	@echo "Commands:"
	@echo "  make create PATTERN=<pattern> PROBLEM=<ProblemName>"
	@echo "  make run PATTERN=<pattern> PROBLEM=<ProblemName>"
	@echo "  make run-all"
	@echo "  make clean"

create:
	@set -eu; \
	pattern='$(PATTERN)'; \
	problem='$(PROBLEM)'; \
	if [ -z "$$pattern" ] || [ -z "$$problem" ]; then \
		echo "Usage: make create PATTERN=<pattern> PROBLEM=<ProblemName>" >&2; \
		exit 2; \
	fi; \
	if ! printf '%s' "$$pattern" | grep -Eq '^[a-z0-9][a-z0-9-]*$$'; then \
		echo "Invalid PATTERN: use lowercase letters, numbers, or hyphens" >&2; \
		exit 2; \
	fi; \
	if ! printf '%s' "$$problem" | grep -Eq '^[A-Za-z][A-Za-z0-9]*$$'; then \
		echo "Invalid PROBLEM: use a valid Java class name" >&2; \
		exit 2; \
	fi; \
	dir='$(PATTERNS_DIR)'/"$$pattern"/"$$problem"; \
	if [ -e "$$dir/Solution.java" ] || [ -e "$$dir/SolutionTest.java" ]; then \
		echo "Problem already exists: $$dir" >&2; \
		exit 2; \
	fi; \
	mkdir -p "$$dir"; \
	printf '%s\n' \
		'public class Solution {' \
		'    public Object solve(Object input) {' \
		'        throw new UnsupportedOperationException("TODO: implement solve");' \
		'    }' \
		'}' > "$$dir/Solution.java"; \
	printf '%s\n' \
		'import util.TestRunner;' \
		'' \
		'public class SolutionTest {' \
		'    public static void main(String[] args) {' \
		'        Solution solution = new Solution();' \
		'' \
		'        // Replace the sample with calls to your solution method.' \
		'        TestRunner.check(solution.solve("sample input"), "expected output", "sample case");' \
		'' \
		'        TestRunner.summary();' \
		'    }' \
		'}' > "$$dir/SolutionTest.java"; \
	echo "Created $$dir"

run:
	@set -eu; \
	pattern='$(PATTERN)'; \
	problem='$(PROBLEM)'; \
	if [ -z "$$pattern" ] || [ -z "$$problem" ]; then \
		echo "Usage: make run PATTERN=<pattern> PROBLEM=<ProblemName>" >&2; \
		exit 2; \
	fi; \
	dir='$(PATTERNS_DIR)'/"$$pattern"/"$$problem"; \
	if [ ! -f "$$dir/Solution.java" ] || [ ! -f "$$dir/SolutionTest.java" ]; then \
		echo "Problem is incomplete or does not exist: $$dir" >&2; \
		exit 2; \
	fi; \
	out='$(BUILD_DIR)'/"$$pattern"/"$$problem"; \
	mkdir -p "$$out"; \
	find "$$out" -type f -name '*.class' -delete; \
	javac -d "$$out" '$(TEST_RUNNER)' "$$dir/Solution.java" "$$dir/SolutionTest.java"; \
	java -cp "$$out" SolutionTest

run-all:
	@set -eu; \
	problems=$$(find '$(PATTERNS_DIR)' -mindepth 3 -maxdepth 3 -name Solution.java -print | sort); \
	if [ -z "$$problems" ]; then \
		echo "No problems found under $(PATTERNS_DIR)"; \
		exit 0; \
	fi; \
	status=0; \
	for solution in $$problems; do \
		dir=$$(dirname "$$solution"); \
		problem=$$(basename "$$dir"); \
		pattern=$$(basename "$$(dirname "$$dir")"); \
		echo "==> $$pattern/$$problem"; \
		if ! $(MAKE) --no-print-directory run PATTERN="$$pattern" PROBLEM="$$problem"; then \
			status=1; \
		fi; \
	done; \
	exit "$$status"

clean:
	@find '$(BUILD_DIR)' -type f -name '*.class' -delete 2>/dev/null || true
	@find '$(BUILD_DIR)' -depth -type d -empty -delete 2>/dev/null || true
	@echo "Removed compiled files"
