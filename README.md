# DSA Practice

Repo Java tối giản để luyện bài theo từng pattern. Mỗi bài được tách riêng nên có
thể dùng cùng tên class `Solution` mà không xung đột với bài khác.

## Yêu cầu

- JDK 17 trở lên
- GNU Make

Kiểm tra môi trường:

```sh
java -version
javac -version
make --version
```

## Cấu trúc

```text
patterns/
  <pattern>/
    <ProblemName>/
      Solution.java
      SolutionTest.java
utils/
  TestRunner.java
Makefile
```

- `Solution.java`: viết thuật toán.
- `SolutionTest.java`: điền input, expected output và gọi `TestRunner.check(...)`.
- `utils/TestRunner.java`: in kết quả từng case và trả exit code lỗi nếu có case fail.
- `.build/`: file `.class` được sinh khi chạy, không commit vào Git.

## Tạo bài mới

Tên `PATTERN` dùng chữ thường, chữ số hoặc dấu gạch ngang. `PROBLEM` phải là tên
class Java hợp lệ.

```sh
make create PATTERN=sliding-window PROBLEM=LongestSubstring
```

Lệnh này tạo:

```text
patterns/sliding-window/LongestSubstring/Solution.java
patterns/sliding-window/LongestSubstring/SolutionTest.java
```

Sau khi tạo, thay method mẫu trong `Solution.java`, rồi sửa các case trong
`SolutionTest.java` để gọi method vừa viết.

## Chạy bài

Chạy một bài:

```sh
make run PATTERN=twopointers PROBLEM=TwoSumII
```

Chạy toàn bộ bài:

```sh
make run-all
```

Dọn file build:

```sh
make clean
```

Xem danh sách lệnh:

```sh
make help
```

Khi một case sai, output hiển thị expected và actual. `make run` hoặc
`make run-all` cũng trả mã lỗi khác 0, nên có thể dùng cùng Git hook hoặc CI.
